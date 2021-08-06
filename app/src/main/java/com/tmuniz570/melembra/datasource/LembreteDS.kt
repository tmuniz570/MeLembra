package com.tmuniz570.melembra.datasource

import com.tmuniz570.melembra.model.Lembrete

object LembreteDSdasfdsgf{
    private val list = arrayListOf<Lembrete>()

    fun getList() = list

    fun addList (lembrete: Lembrete){
        list.add(lembrete)
    }
}