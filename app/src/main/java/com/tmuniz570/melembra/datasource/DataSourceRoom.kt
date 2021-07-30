package com.tmuniz570.melembra.datasource

import android.content.Context
import android.os.AsyncTask
import com.tmuniz570.melembra.model.IDataSource
import com.tmuniz570.melembra.room.LembreteDao
import com.tmuniz570.melembra.room.LembreteDatabase
import com.tmuniz570.melembra.room.LembreteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSourceRoom(val context: Context) : IDataSource {

    val itemDao: LembreteDao = LembreteDatabase.getInstance(context).lembreteDao

    override val itemsLiveData =  itemDao.getAll()

    override fun addItem(lembrete: String, data: String, hora: String, repetir: Boolean) {
        val item = LembreteEntity(0, lembrete,data, hora, repetir)
//        itemDao.insert(item)
        InsertAsyncTask(itemDao).execute(item)
    }

    override fun deleteItem(item: LembreteEntity) {
        itemDao.deleteItem(item)
    }

    companion object {
        private var INSTANCE: DataSourceRoom? = null

        fun getDataSource(context: Context): DataSourceRoom {
            return synchronized(DataSourceRoom::class) {
                val newInstance = INSTANCE ?: DataSourceRoom(context)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
    class InsertAsyncTask(val itemDao: LembreteDao): AsyncTask<LembreteEntity, Unit, Unit>() {
        override fun doInBackground(vararg p0: LembreteEntity?){
            p0[0]?.let { itemDao.insert(it) }
        }
    }

}