package com.mayank.loterabank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.room.Room
import com.mayank.loterabank.customerDB.CustomerDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class DeleteAccount : AppCompatActivity() {
    lateinit var db:CustomerDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        title="Close Account"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val accNumInput:EditText=findViewById(R.id.InputAccNum)

        val deleteAcc: Button =findViewById(R.id.Delete)
        val message:TextView=findViewById(R.id.msg)
        message.isVisible=false
        deleteAcc.setOnClickListener { message.isVisible=true }

        db = Room.databaseBuilder(applicationContext,CustomerDB::class.java,"UserDB").build()


        deleteAcc.setOnClickListener {
            GlobalScope.launch {
                db.customerDao().closeAccount(accNumInput.text.toString().toLong())
            }
            Toast.makeText(this,"done",Toast.LENGTH_SHORT).show()
            message.text = "One account deleted..!"
            message.isVisible = true
        }

    }
}