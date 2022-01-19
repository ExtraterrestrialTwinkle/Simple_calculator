package com.siuzannasmolianinova.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.siuzannasmolianinova.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CalculatorFragment())
            .commit()
    }
}