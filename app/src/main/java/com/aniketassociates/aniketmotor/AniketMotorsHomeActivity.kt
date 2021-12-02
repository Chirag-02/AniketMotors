package com.aniketassociates.aniketmotor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.aniketassociates.R
import com.aniketassociates.adapter.AniketMotorsListAdapter
import com.aniketassociates.aniketmotor.login.AniketMotorsLoginActivity
import com.aniketassociates.databinding.ActivityAniketMotorsHomeBinding
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsListResponse
import com.aniketassociates.room.PickDatabase
import com.aniketassociates.room.ResultDao
import com.aniketassociates.util.Constant
import com.aniketassociates.util.EmplocPreferences
import com.aniketassociates.util.LoaderDialog
import com.aniketassociates.util.OptionDialog
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*
import javax.inject.Inject

class AniketMotorsHomeActivity : DaggerAppCompatActivity(),
    AniketMotorsListAdapter.ItemCLickLister {
    private var adapter: AniketMotorsListAdapter? = null
    private var carNum = ""
    private var carCity = ""
    var resultDao: ResultDao? = null
    var loader: LoaderDialog? = null
    var userName = ""
    var phoneNo = ""
    var id = ""
    var localDbList: List<AniketMotorsListResponse.Data>? = null

    @Inject
    lateinit var empLocPreferences: EmplocPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val aniketMotorsHomeViewModel: AniketMotorsHomeViewModel by viewModels { viewModelFactory }

    var binding: ActivityAniketMotorsHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAniketMotorsHomeBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)
        loader = LoaderDialog(R.string.pleasewait)

        id = empLocPreferences.getString(Constant.ID).toString()
        userName = empLocPreferences.getString(Constant.USERNAME).toString()
        phoneNo = empLocPreferences.getString(Constant.MOBILE).toString()

        val db = Room.databaseBuilder(this, PickDatabase::class.java, "PickDatabase").build()
        resultDao = db.recentDao()

        lifecycleScope.launch(IO) {
            if (resultDao?.getAllHundredRecords()!!.isNotEmpty()) {
                localDbList = resultDao?.getAllHundredRecords()!!
                withContext(Main) {
                    showLocalData()
                }
                println("LOCAL DATA SIZE:  ${resultDao?.getAllHundredRecords()?.size}")
            } else {
                withContext(Main) {
                    getCarsList()

                }
            }
        }

        binding?.downloadLocalData?.setOnClickListener {
            val dialog = OptionDialog(
                this,
                R.drawable.bottom_tab_account,
                R.string.app_name,
                "Are you sure want to logout?",
                positiveBtnText = R.string.yes,
                negativeBtnText = R.string.no,
                positiveClick = {
                    empLocPreferences.clearData()
                    startActivity(Intent(this, AniketMotorsLoginActivity::class.java))
                    finish()
                },
                negativeClick = {

                })
            dialog.show()
        }
        binding?.fabButton?.setOnClickListener {
            getCarsList()

        }

    }

    private fun filterCars() {
        binding?.carNumber?.addTextChangedListener {
            carNum = it.toString()
            if (binding?.carNumber?.length() == 4) {
                binding?.carNumber?.setSelectAllOnFocus(true)
                binding?.carNumber?.selectAll()
                applyFilter(carCity, carNum)
                binding?.carNumber?.setText("")
            }
        }
    }

    private fun filterByCity() {
        binding?.carCity?.addTextChangedListener {
            carCity = it.toString()
            if (binding?.carCity?.text?.length == 2) {
                applyFilter(carCity, carNum)
            } else {
                applyFilter("", "")
            }

        }
    }

    private fun applyFilter(city: String, code: String) {

        if (city.isEmpty() && code.isEmpty()) {
            lifecycleScope.launch {
                binding?.carsRecyclerView?.adapter =
                    AniketMotorsListAdapter(
                        localDbList!!,
                        applicationContext,
                        this@AniketMotorsHomeActivity
                    )
            }

        } else {
            lifecycleScope.launch {
                filteredList(city, code)
            }
        }
    }


    private fun getCarsList() {
        loader?.show(supportFragmentManager, null)
        aniketMotorsHomeViewModel.getCarsData()
        aniketMotorsHomeViewModel.success.observe(this, { it ->
            lifecycleScope.launch {
                val data = it.data.map {
                    val a = it.copy(
                        regNo = lastFour(it.registration_no),
                        regCity = firstTwo(it.registration_no)
                    )
                    a
                }
                resultDao?.insert(data)
                localDbList = it.data
                showLocalData()
                loader?.onDestroyView()
            }

        })

        aniketMotorsHomeViewModel.error.observe(this, {
            println("ERROR->  $it")
            loader?.onDestroyView()
        })
    }

    private fun showLocalData() {
        lifecycleScope.launch {
            val gridLayout = GridLayoutManager(applicationContext, 2)
            binding?.carsRecyclerView?.layoutManager = gridLayout
            adapter = AniketMotorsListAdapter(
                localDbList!!,
                applicationContext,
                this@AniketMotorsHomeActivity
            )
            binding?.carsRecyclerView?.adapter = adapter
            filterByCity()
            filterCars()
            binding?.searchBox?.visibility = View.VISIBLE
        }


    }

    override fun onItemClick(item: AniketMotorsListResponse.Data) {

        val url =
            "http://www.erp.aniketmotors.com/usermaster.aspx?registrationno=${item.registration_no}&entryid=${item.entry_id}&userid=${id}&name=$userName&phoneno=$phoneNo"
        println("ITEM CLICK $url")
        startActivity(Intent(this, AniketMotorsWebViewActivity::class.java).putExtra("url", url))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filteredList(text: String, number: String) {
        val filteredlist: MutableList<AniketMotorsListResponse.Data> = ArrayList()
        lifecycleScope.launch(IO) {
            val localDbList = resultDao?.getAllRecords(text, number)
            println("searching for city $text and number $number")
            withContext(Main) {
                if (localDbList != null && localDbList.isNotEmpty()) {
                    println("ENTER IN IF")
                    for (item in localDbList) {
                        filteredlist.add(item)
                    }

                    if (filteredlist.isEmpty()) {
                        binding?.carsRecyclerView?.visibility = View.GONE
                    } else {
                        if (adapter != null) {
                            binding?.carsRecyclerView?.adapter =
                                AniketMotorsListAdapter(
                                    filteredlist,
                                    applicationContext,
                                    this@AniketMotorsHomeActivity
                                )
                            binding?.carsRecyclerView?.visibility = View.VISIBLE
                        } else {
                            println("ADAPTER NULL")
                        }
                    }

                }

            }
        }

    }

    private fun firstTwo(str: String): String {
        return if (str.length < 2) str else str.substring(0, 2)
    }

    private fun lastFour(str: String): String {
        return if (str.length < 4) str else str.substring(str.length - 4)
    }

}