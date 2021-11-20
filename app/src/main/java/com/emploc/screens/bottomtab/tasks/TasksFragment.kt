package com.emploc.screens.bottomtab.tasks

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.emploc.R
import com.emploc.adapter.OverDueAdapter
import com.emploc.model.Response
import com.emploc.screens.tasksinfo.TasksInformationActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.bottomtab_task.*
import kotlinx.android.synthetic.main.fragment_attendance.view.*
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TasksFragment : DaggerFragment(), OnMapReadyCallback, OverDueAdapter.ItemCLickLister {

    var behaviorTasks: BottomSheetBehavior<View>? = null
    private lateinit var rootView: View
    private var mMap: GoogleMap? = null
    private var adapter: OverDueAdapter? = null
    private var list: ArrayList<Response>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.fragment_tasks, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mapFragment =
            childFragmentManager.findFragmentById(R.id.tasksMap) as SupportMapFragment?

        mapFragment?.getMapAsync(this)



        behaviorTasks = BottomSheetBehavior.from(rootView.taskBottomTab)
        behaviorTasks?.state = BottomSheetBehavior.STATE_DRAGGING
//        behaviorTasks?.isHideable=false
        behaviorTasks?.peekHeight = 500

        tabfunctionality()
        recyclerViewFunctionality()


        if (mMap != null) {
            onMapReady(mMap)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun tabfunctionality() {
        assignedTome.setOnClickListener {
            assignedTome.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.blue_rectangle_corners_oval)
            assignedTome.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            allTask.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_corners)
            allTask.setTextColor(ContextCompat.getColor(requireContext(), R.color.hintTextColor))
        }
        allTask.setOnClickListener {
            allTask.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.blue_rectangle_corners_oval)
            allTask.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            assignedTome.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_corners)
            assignedTome.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.hintTextColor)
            )
        }
    }

    private fun recyclerViewFunctionality() {
        list = ArrayList()
        list?.add(Response("Dwarka"))
        list?.add(Response("Gurgaon"))
        list?.add(Response("Rohini"))

        println("ITEM: $list")

        val gridLayout = GridLayoutManager(requireContext(), 1)
        overdueTaskRecyclerView.layoutManager = gridLayout

        adapter = OverDueAdapter(list!!, requireContext(), this)
        overdueTaskRecyclerView.adapter = adapter
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0;
    }

    override fun onItemClick(item: Response) {
        startActivity(
            Intent(
                requireContext(),
                TasksInformationActivity::class.java
            ).putExtra("location", item.name)
        )
    }


}