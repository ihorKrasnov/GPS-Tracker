package com.example.gpstracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var gpsSwitch: Switch
    private lateinit var locationManager: LocationManager
    private lateinit var gpsStatusReceiver: GpsStatusReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gpsSwitch = findViewById(R.id.gpsSwitch)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        gpsStatusReceiver = GpsStatusReceiver()

        gpsSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
                registerReceiver(gpsStatusReceiver, filter)
                Toast.makeText(this, "Слухання GPS увімкнено", Toast.LENGTH_SHORT).show()
            } else {
                unregisterReceiver(gpsStatusReceiver)
                Toast.makeText(this, "Слухання GPS вимкнено", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (gpsSwitch.isChecked) {
            unregisterReceiver(gpsStatusReceiver)
        }
    }
}
