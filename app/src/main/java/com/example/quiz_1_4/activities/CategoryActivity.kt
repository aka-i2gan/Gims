package com.example.quiz_1_4.activities

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz_1_4.R
import com.example.quiz_1_4.classes.Block

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category)

        // Определяем toolBar
        val tbCategories: Toolbar = findViewById(R.id.tbCategories)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
         * Toolbar
         */
        setSupportActionBar(tbCategories)
        supportActionBar?.apply {
            title = "Выберите категорию"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        // Меняем цвет
        tbCategories.navigationIcon?.apply {
            colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
        }
        /*
         */

        val block = intent.getParcelableExtra<Block>(resources.getString(R.string.extrax_to_cats))
        if (block != null) {
            val tvCat1 : TextView = findViewById(R.id.tvCat1)
            val tvCat2 : TextView = findViewById(R.id.tvCat2)

            tvCat1.text = block.blockName
            tvCat2.text = block.name
        }
    }
}