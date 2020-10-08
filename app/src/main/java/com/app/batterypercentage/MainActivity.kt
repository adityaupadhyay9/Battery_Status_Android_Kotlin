package com.app.batterypercentage

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.BatteryManager.BATTERY_STATUS_CHARGING
import android.os.BatteryManager.BATTERY_STATUS_FULL
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    lateinit var textView: TextView
    lateinit var progress: ProgressBar

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        progress = findViewById(R.id.progressBar)
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = registerReceiver(null, filter)

        var context: Context = applicationContext
        var status = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

        var str: String
        var bm: BatteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager;
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            var percentage = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            textView.setText(percentage.toString() + " %");


            when (status) {

                BATTERY_STATUS_CHARGING -> {
                    str = "Charging Connected"
                    if (percentage == 100) {

                        Toast.makeText(applicationContext,
                            str + " Phone is Fully Charged",
                            Toast.LENGTH_SHORT).show()
                    } else {

                        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()

                    }


                }
                else -> {
                    str = "Charging not Connected"
                    if (percentage == 100) {
                        Toast.makeText(applicationContext,
                            str + " Phone is Fully Charged",
                            Toast.LENGTH_SHORT).show()
                        progress.visibility = View.GONE

                    } else {

                        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
                        progress.visibility = View.GONE
                    }


                }


            }

        }

    }
}











