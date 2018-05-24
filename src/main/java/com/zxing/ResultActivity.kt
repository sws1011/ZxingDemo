package com.zxing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView

/**
 * Created by shiwenshui 2018/5/23 13:47
 */
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val toolbar = findViewById<Toolbar>(R.id.act_result_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val text = findViewById<TextView>(R.id.act_result_tv)
        val result = intent.getStringExtra(EXTRA_SCAN_RESULT)
        val format = intent.getStringExtra(EXTRA_SCAN_RESULT_FORMAT)
        val str = "result =$result\n\nFormat=$format"
        text.text = str
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)  {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    companion object {
        const val EXTRA_SCAN_RESULT = "SCAN_RESULT"
        const val EXTRA_SCAN_RESULT_FORMAT = "SCAN_RESULT_FORMAT"
    }
}
