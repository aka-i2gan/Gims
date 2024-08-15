package com.example.quiz_1_4.activities

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_1_4.R
import com.example.quiz_1_4.classes.Block
import com.example.quiz_1_4.classes.BlockAdapter

class ChaptersActivity : AppCompatActivity() {

    private lateinit var rvBlock1: RecyclerView
    private lateinit var rvBlock2: RecyclerView
    private lateinit var block1List: ArrayList<Block>
    private lateinit var block2List: ArrayList<Block>
    private lateinit var blockAdapter1: BlockAdapter
    private lateinit var blockAdapter2: BlockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chapters)

        // Определяем Toolbar
        val tbChapters: Toolbar = findViewById(R.id.tbChapters)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
         * Toolbar
         */
        setSupportActionBar(tbChapters)
        supportActionBar?.apply {
            title = "Выберите блок"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        // Меняем цвет
        tbChapters.navigationIcon?.apply {
            colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
        }
        /*
         */

        rvBlock1 = findViewById(R.id.rvBlock1)
        rvBlock1.setHasFixedSize(true)
        rvBlock1.layoutManager = LinearLayoutManager(this)

        rvBlock2 = findViewById(R.id.rvBlock2)
        rvBlock2.setHasFixedSize(true)
        rvBlock2.layoutManager = LinearLayoutManager(this)

        block1List = ArrayList()
        block2List = ArrayList()

        block1List.add(Block(resources.getString(R.string.block_vvp), resources.getString(R.string.block_full_vvp)))
        block1List.add(Block(resources.getString(R.string.block_vv), resources.getString(R.string.block_full_vv)))
        block1List.add(Block(resources.getString(R.string.block_mp), getResources().getString(R.string.block_full_mp)))

        block2List.add(Block(resources.getString(R.string.block_mms), resources.getString(R.string.block_full_mms)))
        block2List.add(Block(resources.getString(R.string.block_gd), resources.getString(R.string.block_full_gd)))
        block2List.add(Block(resources.getString(R.string.block_mps), resources.getString(R.string.block_full_mps)))
        block2List.add(Block(resources.getString(R.string.block_ok), resources.getString(R.string.block_full_ok)))

        // Запрет прокрутки 1-го RecyclerView
        val myLinearLayoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rvBlock1.layoutManager = myLinearLayoutManager
        // Запрет прокрутки 2-го RecyclerView
        val myLinearLayoutManager2 = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rvBlock2.layoutManager = myLinearLayoutManager2



        blockAdapter1 = BlockAdapter(block1List)
        rvBlock1.adapter = blockAdapter1

        blockAdapter2 = BlockAdapter(block2List)
        rvBlock2.adapter = blockAdapter2

        blockAdapter1.onItemClick = {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(resources.getString(R.string.extrax_to_cats), it)
            startActivity(intent)
        }
        blockAdapter2.onItemClick = {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(resources.getString(R.string.extrax_to_cats), it)
            startActivity(intent)
        }
    }
}