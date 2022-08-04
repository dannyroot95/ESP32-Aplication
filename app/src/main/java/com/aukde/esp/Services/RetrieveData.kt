package com.aukde.esp.Services

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.aukde.esp.Models.Parameters
import com.aukde.esp.Providers.Network
import com.aukde.esp.R
import com.aukde.esp.databinding.ActivityRetrieveDataBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class RetrieveData : AppCompatActivity() {

    private lateinit var binding : ActivityRetrieveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrieveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }

    private fun getData(){
        Network().database().addValueEventListener(object : ValueEventListener{
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val data = snapshot.getValue(Parameters::class.java)
                    binding.loader.visibility = View.GONE
                    binding.lnData.visibility = View.VISIBLE
                    binding.hum.text = data!!.humidity.toString()+"%"
                    binding.temp.text = data.temperature.toString()+"C°"
                }else{
                    binding.loader.text = "No se encontraron datos"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                binding.loader.text = "Error 404!"
                Toast.makeText(this@RetrieveData,"Error to access server",Toast.LENGTH_SHORT).show()
            }
        })
    }

}