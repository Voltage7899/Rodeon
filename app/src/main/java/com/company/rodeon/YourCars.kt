package com.company.rodeon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.rodeon.databinding.ActivityRegBinding
import com.company.rodeon.databinding.ActivityYourCarsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class YourCars : AppCompatActivity() {
    lateinit var binding: ActivityYourCarsBinding

    var ListAdapter:YourCarsAdapter?=null
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityYourCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recYourCars.layoutManager= LinearLayoutManager(this)
        ListAdapter = YourCarsAdapter()
        binding.recYourCars.adapter=ListAdapter
        ListAdapter?.loadListToAdapter(getData())
    }
    fun getData():ArrayList<ShopCars>{



        val List=ArrayList<ShopCars>()
        database.child("ShopCar").child(Buyer.currentuser?.phone.toString()).get().addOnSuccessListener {
            for (i in it.children){
                var car=i.getValue(ShopCars::class.java)
                if(car!=null){
                    List.add(car)
                    ListAdapter?.loadListToAdapter(List)
                }

            }
        }
        return List
    }

    override fun onStart() {
        super.onStart()
        ListAdapter?.loadListToAdapter(getData())
    }
}