package com.ashish.esoftwarica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var etname: EditText
    private lateinit var etpass: EditText
    private lateinit var btnlogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etname = findViewById(R.id.etname)
        etpass = findViewById(R.id.etpass)
        btnlogin = findViewById(R.id.btnlogin)

        btnlogin.setOnClickListener {
            val name = etname.text.toString()
            val password = etpass.text.toString()

            if (name == "softwarica" && password == "coventry") {
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Username or password invalid",Toast.LENGTH_SHORT).show()
            }


        }
    }
}