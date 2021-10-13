package com.example.rssfeedpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVaddapter(var questions: MutableList<Question>):
    RecyclerView.Adapter<RVaddapter.MessageViewHolder>() {
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val question = questions[position]

        holder.itemView.apply {
            tvQuestion.text = question.title+"\n"+question.author
        }
    }

    override fun getItemCount()= questions.size

    fun update(questions: MutableList<Question>){
        this.questions = questions

        notifyDataSetChanged()
    }


}