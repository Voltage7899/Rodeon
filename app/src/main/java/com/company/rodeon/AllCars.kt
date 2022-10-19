package com.company.rodeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.rodeon.databinding.ActivityAllCarsBinding
import com.google.firebase.database.FirebaseDatabase

class AllCars : AppCompatActivity() ,AdapterCars.ClickListener {
    lateinit var binding: ActivityAllCarsBinding
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ListAdapter:AdapterCars?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAllCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navLeftMenuUser.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.yourCars->{
                    startActivity(Intent(this,YourCars::class.java))
                }

            }
            binding.drawer2.closeDrawer(GravityCompat.START)
            true
        }

        binding.recUser.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        ListAdapter = AdapterCars(this)
        binding.recUser.adapter=ListAdapter
        ListAdapter?.loadListToAdapter(getData())
    }

    fun getData():ArrayList<Car>{



        val List=ArrayList<Car>()
        database.getReference("Cars").get().addOnSuccessListener {
            for (i in it.children){
                var party=i.getValue(Car::class.java)
                if(party!=null){
                    List.add(party)
                    ListAdapter?.loadListToAdapter(List)
                }

            }
        }
        return List
    }
    override fun onClick(car: Car) {
        startActivity(Intent(this, Description::class.java).apply {
            putExtra("car",car)

        })
    }
    override fun onStart() {
        super.onStart()
        ListAdapter?.loadListToAdapter(getData())
    }
}