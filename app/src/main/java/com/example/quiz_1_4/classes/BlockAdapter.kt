package com.example.quiz_1_4.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_1_4.R

class BlockAdapter(private val blockList:ArrayList<Block>): RecyclerView.Adapter<BlockAdapter.BlockViewHolder>() {

    var onItemClick : ((Block) -> Unit)? = null

    class BlockViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvBlockFirstName: TextView = itemView.findViewById(R.id.tvBlockFirstName)
        val tvBlockFullName: TextView = itemView.findViewById(R.id.tvBlockFullName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_chapter_item, parent, false)
        return BlockViewHolder(view)
    }

    override fun getItemCount(): Int {
        return blockList.size
    }

    override fun onBindViewHolder(holder: BlockViewHolder, position: Int) {
        val block = blockList[position]
        holder.tvBlockFirstName.text = block.blockName
        holder.tvBlockFullName.text = block.name

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(block)
        }
    }
}