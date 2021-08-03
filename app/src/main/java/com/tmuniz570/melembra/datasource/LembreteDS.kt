package com.tmuniz570.melembra.datasource

import com.tmuniz570.melembra.model.Lembrete
import com.tmuniz570.melembra.room.LembreteEntity

object LembreteDS{
    private val list = arrayListOf<Lembrete>()

    fun getList() = list

    fun addList (lembrete: Lembrete){
        list.add(lembrete)
    }
}