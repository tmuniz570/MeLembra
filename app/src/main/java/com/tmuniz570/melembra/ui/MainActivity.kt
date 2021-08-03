package com.tmuniz570.melembra.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tmuniz570.melembra.databinding.ActivityMainBinding
import com.tmuniz570.melembra.datasource.LembreteDS

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { LembreteAdapter(LembreteDS.getList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.rvLembretes
        recyclerView.adapter = adapter

        listeners()
    }

    private fun listeners() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun listar(){
        binding.rvLembretes.adapter = adapter
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

}