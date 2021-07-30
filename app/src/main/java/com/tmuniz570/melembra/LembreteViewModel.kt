package com.tmuniz570.melembra

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tmuniz570.melembra.datasource.DataSourceRoom
import com.tmuniz570.melembra.model.IDataSource
import com.tmuniz570.melembra.room.LembreteEntity
import kotlinx.coroutines.launch

class LembreteViewModel(val dataSource: IDataSource) : ViewModel() {
    fun addItem(lembrete: String, data: String, hora: String, repetir: Boolean) {
        dataSource.addItem(lembrete, data, hora, repetir)
    }

    fun deleteItem(item: LembreteEntity) {
        viewModelScope.launch {
            dataSource.deleteItem(item)
        }
    }

    val itemsLiveData = dataSource.itemsLiveData
}

class LembreteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LembreteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LembreteViewModel(
                dataSource = DataSourceRoom.getDataSource(context)
//                dataSource = DataSourceFirebase.getDataSource(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}