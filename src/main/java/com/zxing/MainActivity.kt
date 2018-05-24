package com.zxing

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var isPermission = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.act_main_toolbar)
        setSupportActionBar(toolbar)

        val startScanTv = findViewById<Button>(R.id.start_scan)

        startScanTv.setOnClickListener {
            if (checkPermissions()) {
                toScanActivity()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intents.FLAG_NEW_DOC)
        when (item.itemId) {
            R.id.menu_settings -> {
                intent.setClassName(this, PreferencesActivity::class.java.name)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun toScanActivity() {
        startActivity(Intent(application, CaptureActivity::class.java))
    }

    private fun checkPermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionResult = ActivityCompat.checkSelfPermission(application, Manifest.permission.CAMERA)
            if (PackageManager.PERMISSION_GRANTED != permissionResult) {
                val permissions = arrayOf(Manifest.permission.CAMERA)
                requestPermissions(permissions, REQ_PERMISSION_CODE)
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (REQ_PERMISSION_CODE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isPermission = true
                toScanActivity()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val REQ_PERMISSION_CODE = 1000
    }
}
