package com.tmuniz570.melembra.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.tmuniz570.melembra.R
import com.tmuniz570.melembra.databinding.ItemLembreteBinding
import com.tmuniz570.melembra.model.Lembrete

class LembreteAdapter(private var list: List<Lembrete>): RecyclerView.Adapter<LembreteAdapter.LembreteViewHolder>() {

    var listenerEditar: (Lembrete) -> Unit = {}
    var listenerDeletar: (Lembrete) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LembreteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLembreteBinding.inflate(inflater, parent, false)
        return LembreteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LembreteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class LembreteViewHolder(private val binding: ItemLembreteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Lembrete) {
            binding.tvLembrete.text = item.lembrete
            binding.tvData.text = item.data
            binding.tvHora.text = item.hora
            binding.acivMais.setOnClickListener {
                showPopup(item)
            }
        }

        private fun showPopup(item: Lembrete) {
            val acivMais = binding.acivMais
            val popupMenu = PopupMenu(acivMais.context, acivMais)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_editar -> listenerEditar(item)
                    R.id.menu_deletar -> listenerDeletar(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(update: List<Lembrete>){
        list = update
    }
}