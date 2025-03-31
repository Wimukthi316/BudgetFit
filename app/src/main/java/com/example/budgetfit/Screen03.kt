package com.example.budgetfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Screen03 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen03)

        var button3 = findViewById<Button>(R.id.button3)

        button3.setOnClickListener{
            val intent = Intent(this, Screen04::class.java)
            startActivity(intent)
            finish()

        }
        val image25 = findViewById<ImageView>(R.id.imageView25)

        image25.setOnClickListener { navigateToScreen02("Screen02") }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun navigateToScreen02(s: String) {

        val intent = Intent(this, Screen02::class.java)
        startActivity(intent)

    }
}