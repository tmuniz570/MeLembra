package com.tmuniz570.melembra.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.RecyclerView
import com.tmuniz570.melembra.R
import com.tmuniz570.melembra.databinding.ActivityMainBinding
import com.tmuniz570.melembra.model.Lembrete
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

//        val time = Calendar.getInstance().time
//        val timeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
//        binding.tvTitle.text = timeFormat.format(time)

        listeners()
    }

    private fun listeners() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
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

    override fun onResume() {
        super.onResume()
        listar()
    }

    private fun notificar(lembrete: Lembrete){
        val name = "ChannelMeLembra"
        val description = "Lembrete de atividade no MeLembra"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(lembrete.lembrete, name, importance).apply{
            this.description = description
            enableLights(true)
            lightColor = Color.BLUE
            enableVibration(true)
            setShowBadge(true)
        }

        val builder = NotificationCompat.Builder(this, lembrete.lembrete)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Lembre de :")
            .setContentText(lembrete.lembrete)
            .setStyle(NotificationCompat.BigTextStyle().bigText(lembrete.lembrete))
            .setAutoCancel(true)

//        NotificationManagerCompat.from(this).notify(lembrete.lembrete.length, builder.build())

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
        manager.notify(lembrete.lembrete.length, builder.build())
    }

}