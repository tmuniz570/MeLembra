package com.tmuniz570.melembra.datasource

import com.tmuniz570.melembra.model.Lembrete
import com.tmuniz570.melembra.room.LembreteEntity

object LembreteDS{
    private val list = arrayListOf<LembreteEntity>()

    fun getList() = list

    fun addList (lembrete: LembreteEntity){
        list.add(lembrete)
    }
}