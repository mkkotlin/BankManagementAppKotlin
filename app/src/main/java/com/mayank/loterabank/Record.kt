package com.mayank.loterabank

import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.mayank.loterabank.customerDB.Customer
import com.mayank.loterabank.customerDB.CustomerDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Record : AppCompatActivity() {
    lateinit var db:CustomerDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        title = "All record"




        db = Room.databaseBuilder(applicationContext,CustomerDB::class.java,"UserDB").build()

        GlobalScope.launch {
            displayAll()
        }


    }

    private suspend fun displayAll(){
        val user: List<Customer> = db.customerDao().getAllData()
        var displayAccNum=""
        var displayName=""
        var displayDOB=""
        var displayPhone=""
        var displayAccTyp=""
        var displayAmount=""
        for(user:Customer in user){
            displayAccNum += "${user.accNum}\n"
            displayName += "${user.name}\n"
            displayDOB += "${user.dob}\n"
            displayPhone += "${user.phone}\n"
            displayAccTyp += "${user.accTyp}\n"
            displayAmount += "${user.amount}\n"
        }

        val accNum:TextView = findViewById(R.id.AccNumFieldDis)
        val name:TextView = findViewById(R.id.NameFieldDis)
        val dob:TextView = findViewById(R.id.DOBFieldDis)
        val phone:TextView = findViewById(R.id.PhoneFieldDis)
        val accTyp:TextView = findViewById(R.id.AccTypFieldDis)
        val amount:TextView = findViewById(R.id.AmountFieldDis)
        accNum.text =displayAccNum
        name.text = displayName
        dob.text = displayDOB
        phone.text = displayPhone
        accTyp.text = displayAccTyp
        amount.text = displayAmount
    }

}