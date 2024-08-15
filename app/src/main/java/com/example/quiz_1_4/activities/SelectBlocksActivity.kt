package com.example.quiz_1_4.activities

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz_1_4.R


class SelectBlocksActivity : AppCompatActivity() {

    private lateinit var arrBlocksToExam: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_blocks)

        // Определяем toolBar
        val tbSelectBlocks: Toolbar = findViewById(R.id.tbSelectBlocks)

        val chbVV: CheckBox = findViewById(R.id.chbVV)
        val chbVVP: CheckBox = findViewById(R.id.chbVVP)
        val chbMP: CheckBox = findViewById(R.id.chbMP)
        val chbMMS: CheckBox = findViewById(R.id.chbMMS)
        val chbGD: CheckBox = findViewById(R.id.chbGD)
        val chbMPS: CheckBox = findViewById(R.id.chbMPS)
        val chbС: CheckBox = findViewById(R.id.chbС)

        val tvStartExam: TextView = findViewById(R.id.tvStartExam)

        val arrayList = java.util.ArrayList<String>()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
         * Toolbar
         */
        setSupportActionBar(tbSelectBlocks)
        supportActionBar?.apply {
            title = "Выберите блок"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        // Меняем цвет
        tbSelectBlocks.navigationIcon?.apply {
            colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
        }
        /*
         */


        tvStartExam.setOnClickListener() {

            var examBlocks = ""

            if (chbVV.isChecked()) {
                examBlocks = examBlocks + "1"
            }
            if (chbVVP.isChecked()) {
                examBlocks = examBlocks + "2"
            }
            if (chbMP.isChecked()) {
                examBlocks = examBlocks + "3"
            }
            if (chbMMS.isChecked()) {
                examBlocks = examBlocks + "4"
            }
            if (chbGD.isChecked()) {
                examBlocks = examBlocks + "5"
            }
            if (chbMPS.isChecked()) {
                examBlocks = examBlocks + "6"
            }
            if (chbС.isChecked()) {
                examBlocks = examBlocks + "7"
            }

            val intent = Intent(this, ExamActivity::class.java)
            intent.putExtra("examBlocks", examBlocks)
            startActivity(intent)

        }

    }
}