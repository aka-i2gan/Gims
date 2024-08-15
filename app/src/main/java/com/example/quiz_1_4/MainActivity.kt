package com.example.quiz_1_4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz_1_4.activities.ChaptersActivity
import com.example.quiz_1_4.activities.SelectBlocksActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tvToChapters: TextView = findViewById(R.id.tvToChapters)
        val tvToExam: TextView = findViewById(R.id.tvToExam)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvToChapters.setOnClickListener {
            val intent = Intent(this, ChaptersActivity::class.java)
            startActivity(intent)
        }

        tvToExam.setOnClickListener {
            val intent = Intent(this, SelectBlocksActivity::class.java)
            startActivity(intent)
        }
    }
}