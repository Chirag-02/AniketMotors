package com.emploc.screens.bottomtab.attendance

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Bundle
import android.provider.Settings
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.emploc.R
import com.emploc.util.FingerprintHelper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.bottomtab_attendance.*
import kotlinx.android.synthetic.main.fragment_attendance.view.*
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import java.util.concurrent.Executor
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey


@Suppress("UNREACHABLE_CODE")
class AttendanceFragment : DaggerFragment(), OnMapReadyCallback {
    var behaviorAttendance: BottomSheetBehavior<View>? = null
    private lateinit var rootView: View
    private var mMap: GoogleMap? = null
    var adb = 0

    private lateinit var fingerprintManager: FingerprintManager
    private lateinit var keyguardManager: KeyguardManager

    private lateinit var keyStore: KeyStore
    private lateinit var keyGenerator: KeyGenerator
    private val KEY_NAME = "my_key"

    private lateinit var cipher: Cipher
    private lateinit var cryptoObject: FingerprintManager.CryptoObject



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_attendance, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mapFragment =
            childFragmentManager.findFragmentById(R.id.attendanceMap) as SupportMapFragment?

        mapFragment?.getMapAsync(this)


        behaviorAttendance = BottomSheetBehavior.from(rootView.attendanceTab)
        behaviorAttendance?.state = BottomSheetBehavior.STATE_EXPANDED
        behaviorAttendance?.peekHeight = 500

        check_in.setOnClickListener {
            if (checkLockScreen()) {
                generateKey()
                if (initCipher()) {
                    cipher.let {
                        cryptoObject = FingerprintManager.CryptoObject(it)
                    }
                    val helper = FingerprintHelper(requireContext())

                    if (fingerprintManager != null && cryptoObject != null) {
                        helper.startAuth(fingerprintManager, cryptoObject)
                    }
                }
            }
        }

        if (mMap != null) {
            onMapReady(mMap)
        }

//        checkDevLoperModOpen()
    }


    private fun checkLockScreen(): Boolean {
        keyguardManager = activity?.getSystemService(Context.KEYGUARD_SERVICE)
                as KeyguardManager
        fingerprintManager = activity?.getSystemService(Context.FINGERPRINT_SERVICE)
                as FingerprintManager
        if (!keyguardManager.isKeyguardSecure) {

            Toast.makeText(requireContext(),
                "Lock screen security not enabled",
                Toast.LENGTH_LONG).show()
            return false
        }

        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.USE_FINGERPRINT) !=
            PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(),
                "Permission not enabled (Fingerprint)",
                Toast.LENGTH_LONG).show()

            return false
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(requireContext(),
                "No fingerprint registered, please register",
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                "AndroidKeyStore")
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(
                "Failed to get KeyGenerator instance", e)
        } catch (e: NoSuchProviderException) {
            throw RuntimeException("Failed to get KeyGenerator instance", e)
        }

        try {
            keyStore.load(null)
            keyGenerator.init(
                KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                        KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build())
            keyGenerator.generateKey()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        } catch (e: InvalidAlgorithmParameterException) {
            throw RuntimeException(e)
        } catch (e: CertificateException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }


    private fun initCipher(): Boolean {
        try {
            cipher = Cipher.getInstance(
                KeyProperties.KEY_ALGORITHM_AES + "/"
                        + KeyProperties.BLOCK_MODE_CBC + "/"
                        + KeyProperties.ENCRYPTION_PADDING_PKCS7)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: NoSuchPaddingException) {
            throw RuntimeException("Failed to get Cipher", e)
        }

        try {
            keyStore.load(null)
            val key = keyStore.getKey(KEY_NAME, null) as SecretKey
            cipher.init(Cipher.ENCRYPT_MODE, key)
            return true
        } catch (e: KeyPermanentlyInvalidatedException) {
            return false
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: CertificateException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: UnrecoverableKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        }
    }



    private fun checkDevLoperModOpen() {
        adb = Settings.Secure.getInt(
            requireActivity().contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
        )
        println("ADB $adb")

        if (adb == 0) {
            checkAdbisOn.visibility = View.GONE
            attedanceLayout.visibility = View.VISIBLE
        } else {
            checkAdbisOn.visibility = View.VISIBLE
            attedanceLayout.visibility = View.GONE
        }
        openSetting.setOnClickListener {
            startActivity(Intent(Settings.ACTION_SETTINGS))

        }
    }

    fun isMockSettingsON(context: Context): Boolean {
        // returns true if mock location enabled, false if not enabled.
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ALLOW_MOCK_LOCATION
        ) != "0"

//        val isMock =
//            Settings.Secure.getString(context.contentResolver, Settings.Secure.ALLOW_MOCK_LOCATION) != "0";
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
    }


}