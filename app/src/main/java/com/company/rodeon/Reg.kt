package com.company.rodeon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company.rodeon.databinding.ActivityMainBinding
import com.company.rodeon.databinding.ActivityRegBinding
import com.google.firebase.database.*

class Reg : AppCompatActivity() {
    lateinit var binding: ActivityRegBinding


    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registration.setOnClickListener {
            val user=Buyer(binding.nameReg.text.toString(),binding.phoneReg.text.toString(),binding.passReg.text.toString())

            database.child("User").addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.child(binding.phoneReg.text.toString()).exists()) {
                        database.child("User").child(binding.phoneReg.text.toString()).setValue(user)
                        Toast.makeText(this@Reg, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                        finish()

                    } else {
                        Toast.makeText(this@Reg, "Пользователь с такими данными уже есть", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }

    }
}