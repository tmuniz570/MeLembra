package com.tmuniz570.melembra.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.tmuniz570.melembra.LembreteViewModel
import com.tmuniz570.melembra.LembreteViewModelFactory
import com.tmuniz570.melembra.databinding.ActivityMainBinding
import com.tmuniz570.melembra.datasource.DataSourceRoom
import com.tmuniz570.melembra.model.IDataSource
import com.tmuniz570.melembra.room.LembreteEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapterRoom by lazy { LembreteAdapterRoom(IDataSource.getItem()) }

    private val lembreteViewModel by viewModels<LembreteViewModel>{
        LembreteViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        adapterRoom = LembreteAdapterRoom(LembreteDS.getList())


        val recyclerView: RecyclerView = binding.rvLembretes
        recyclerView.adapter = adapterRoom


        lembreteViewModel.itemsLiveData.observe(this, {
            it?.let {
                adapterRoom.notifyDataSetChanged()
            }

        })

//        binding.btnStart.setOnClickListener {
//            itemListViewModel.addItem(binding.txtAddItemName.text.toString(),
//                binding.addItemPrice.text.toString())
//        }




        listeners()
    }

    private fun listeners() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun listar(){
        binding.rvLembretes.adapter = adapterRoom
        if (LembreteDS.getList().size > 0) {
            binding.rvLembretes.visibility = View.VISIBLE
            binding.tvNenhumLembrete.visibility = View.GONE
        }else{
            binding.rvLembretes.visibility = View.GONE
            binding.tvNenhumLembrete.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        listar()
    }

    private fun adapterOnClick(item: LembreteEntity) {
        // val intent = Intent(this, FileDetailActivity()::class.java)
        // intent.putExtra(FILE_ID, file)
        // startActivity(intent)
    }

    private fun adapterOnClickDelete(item: LembreteEntity) {
        lembreteViewModel.deleteItem(item)
    }

}