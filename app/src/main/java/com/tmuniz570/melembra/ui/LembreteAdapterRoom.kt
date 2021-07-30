package com.tmuniz570.melembra.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmuniz570.melembra.R
import com.tmuniz570.melembra.room.LembreteEntity

class LembreteAdapterRoom(private val list: List<LembreteEntity>): RecyclerView.Adapter<LembreteAdapterRoom.LembreteViewHolder>() {

    class LembreteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private val lembreteTextView: TextView = itemView.findViewById(R.id.tv_lembrete)
        private val dataTextView: TextView = itemView.findViewById(R.id.tv_data)
        private val horaTextView: TextView = itemView.findViewById(R.id.tv_hora)
        private var currentItem: LembreteEntity? = null

        fun bind(item: LembreteEntity) {

            currentItem = item
            lembreteTextView.text = item.lembrete
            dataTextView.text = item.data
            horaTextView.text = item.hora
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LembreteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lembrete, parent, false)
        return LembreteViewHolder(view)
    }

    override fun onBindViewHolder(holder: LembreteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

//    override fun areContentsTheSame(oldItem: LembreteEntity, newItem: LembreteEntity): Boolean {
//        return oldItem.name.equals(newItem.name) && oldItem.price.equals(newItem.price)
//    }
}