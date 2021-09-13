package com.mayank.loterabank

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val button1:Button=findViewById(R.id.OpenAccount)
        button1.setOnClickListener {
            val intent=Intent(this,OpenAccount1::class.java)
            startActivity(intent)
             }

        val button2:Button=findViewById(R.id.CloseAccount)
        button2.setOnClickListener {
            val intent=Intent(this,DeleteAccount::class.java)
            startActivity(intent)
        }

        val button3:Button=findViewById(R.id.Withdraw)
        button3.setOnClickListener { val intent = Intent(this,Withdraw::class.java)
        startActivity(intent)}

        val button4:Button=findViewById(R.id.Deposit)
        button4.setOnClickListener { val intent = Intent(this,Deposit::class.java)
            startActivity(intent) }

        val button5:Button=findViewById(R.id.Show_All)
        button5.setOnClickListener { Toast.makeText(this,"clicked button5",10000).show()
        startActivity(Intent(this,Record::class.java))}

        val button6:Button=findViewById(R.id.View_One)
        button6.text="Account Detail"
        button6.setOnClickListener { val intent = Intent(this,OneRecord::class.java)
        startActivity(intent)}
    }
}