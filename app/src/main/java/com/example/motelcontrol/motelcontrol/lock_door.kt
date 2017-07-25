package com.example.motelcontrol.motelcontrol

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Date
import android.widget.ProgressBar
import com.example.motelcontrol.motelcontrol.rest.RestCalls


class lock_door : AppCompatActivity() {
    private val rest = RestCalls()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_door)
        val pb = findViewById<ProgressBar>(R.id.indeterminateBar)
        pb.visibility = ProgressBar.INVISIBLE
        rest.fillProducts()
        val openButton = findViewById<Button>(R.id.open_button)
        var oo = 0
        openButton.setOnClickListener {
            view: View? ->
                var a = mutableListOf<String>()
                a.add("si111")
                a.add("neeeeo22")
                rest.delete(2)
        }
    }

    fun openDoor(some: String) {
        val message = findViewById<TextView>(R.id.open_message)
        val date = Date()
        message.setText(some)
    }


}
