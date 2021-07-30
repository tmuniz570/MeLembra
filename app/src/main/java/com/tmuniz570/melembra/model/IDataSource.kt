package com.tmuniz570.melembra.model

import androidx.lifecycle.LiveData
import com.tmuniz570.melembra.room.LembreteEntity

interface IDataSource {
    val itemsLiveData: LiveData<List<LembreteEntity>>
    fun addItem(lembrete: String, data: String, hora: String, repetir: Boolean)
    fun deleteItem(item: LembreteEntity)
    fun getItem() = itemsLiveData
}