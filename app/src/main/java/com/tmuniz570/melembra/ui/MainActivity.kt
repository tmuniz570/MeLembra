package com.tmuniz570.melembra.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tmuniz570.melembra.databinding.ActivityMainBinding
import com.tmuniz570.melembra.room.LembreteDao
import com.tmuniz570.melembra.room.LembreteDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var dao: LembreteDao
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { LembreteAdapter(dao.getAll()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = LembreteDatabase.getInstance(this).lembreteDao()

        val recyclerView: RecyclerView = binding.rvLembretes
        recyclerView.adapter = adapter

        listeners()
    }

    override fun onResume() {
        super.onResume()
        listar()
    }

    private fun listeners() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditActivity::class.java))
        }

        adapter.listenerEditar = {
            val intent = Intent(this, AddEditActivity::class.java)
                .putExtra(AddEditActivity.LEMBRETE_ID, it.id)

            startActivity(intent)
        }

        adapter.listenerDeletar = {
            dao.delete(it)
            listar()
        }
    }

    private fun listar(){
        adapter.setList(dao.getAll())
        binding.rvLembretes.adapter = adapter
        if (dao.getAll().isNotEmpty()) {
            binding.rvLembretes.visibility = View.VISIBLE
            binding.tvNenhumLembrete.visibility = View.GONE
        }else{
            binding.rvLembretes.visibility = View.GONE
            binding.tvNenhumLembrete.visibility = View.VISIBLE
        }
    }

}