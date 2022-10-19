package com.company.rodeon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company.rodeon.databinding.ActivityDescriptionBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class Description : AppCompatActivity() {
    lateinit var binding: ActivityDescriptionBinding
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item= intent.getSerializableExtra("car") as Car
        Picasso.get().load(item.link).fit().into(binding.descImage)

        binding.descName.setText(item.name)
        binding.descDesc.setText(item.desc)


        binding.descPrice.setText(item.price)

        binding.descAdd.setOnClickListener {

            val shopCar=ShopCars(Buyer.currentuser?.phone,item.id,item.name,item.price)
            database.child("ShopCar").addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.child(Buyer.currentuser?.phone.toString()).child(item.id.toString()).exists()){
                        Toast.makeText(this@Description,"Эта машина уже заказана", Toast.LENGTH_LONG).show()
                    }
                    else{
                        database.child("ShopCar").child(Buyer.currentuser?.phone.toString()).child(item.id.toString()).setValue(shopCar)
                        Toast.makeText(this@Description,"Вы заказали машину", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
    }

}