package com.tmuniz570.melembra.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmuniz570.melembra.databinding.ItemLembreteBinding
import com.tmuniz570.melembra.model.Lembrete

class LembreteAdapter(private var list: List<Lembrete>): RecyclerView.Adapter<LembreteAdapter.LembreteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LembreteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLembreteBinding.inflate(inflater, parent, false)
        return LembreteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LembreteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class LembreteViewHolder(private val binding: ItemLembreteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Lembrete) {
            binding.tvLembrete.text = item.lembrete
            binding.tvData.text = item.data
            binding.tvHora.text = item.hora
        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(update: List<Lembrete>){
        list = update
    }
}