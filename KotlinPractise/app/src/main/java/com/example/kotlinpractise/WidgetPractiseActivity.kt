package com.example.kotlinpractise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_widget_practise.*
import org.jetbrains.anko.alert

class WidgetPractiseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_practise);

        alert("显示的Message") {
            positiveButton("positive button Message"){tv_alert.text = "positive click"}
            negativeButton("negative button Message"){tv_alert.text = "negative click"}
        }.show()
    }
}