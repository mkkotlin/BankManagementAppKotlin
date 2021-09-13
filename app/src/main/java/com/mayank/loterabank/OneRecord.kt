package com.mayank.loterabank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.room.Room
import com.mayank.loterabank.customerDB.Customer
import com.mayank.loterabank.customerDB.CustomerDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OneRecord : AppCompatActivity() {
    lateinit var db:CustomerDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_record)

        title = "View Account"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        hide()

        db = Room.databaseBuilder(applicationContext,CustomerDB::class.java,"UserDB").build()
        val buttonView:Button=findViewById(R.id.View)
        buttonView.setOnClickListener {
            visible()
            GlobalScope.launch {
                viewAccount()
            }
        }

    }

    private fun hide(){
        val name:TextView=findViewById(R.id.name)
        val customerName:TextView=findViewById(R.id.CustomerName)
        val dob:TextView=findViewById(R.id.DOB)
        val customerDOB:TextView=findViewById(R.id.CustomerDOB)
        val phoneNum:TextView=findViewById(R.id.Phone)
        val customerPhone:TextView=findViewById(R.id.CustomerPhone)
        val accType:TextView=findViewById(R.id.AccTyp)
        val customerAccTyp:TextView=findViewById(R.id.CustomerAccTyp)
        val accNumber:TextView=findViewById(R.id.AccNum)
        val customerAccNum:TextView=findViewById(R.id.CustomerAccNum)
        val balance:TextView=findViewById(R.id.NetBalance)
        val customerBalance:TextView=findViewById(R.id.CustomerNetBal)
        val bool=false
        name.isVisible=bool
        customerName.isVisible=bool
        dob.isVisible=bool
        customerDOB.isVisible=bool
        phoneNum.isVisible=bool
        customerPhone.isVisible=bool
        accType.isVisible=bool
        customerAccTyp.isVisible=bool
        accNumber.isVisible=bool
        customerAccNum.isVisible=bool
        balance.isVisible=bool
        customerBalance.isVisible=bool
    }
    private fun visible(){
        val name:TextView=findViewById(R.id.name)
        val customerName:TextView=findViewById(R.id.CustomerName)
        val dob:TextView=findViewById(R.id.DOB)
        val customerDOB:TextView=findViewById(R.id.CustomerDOB)
        val phoneNum:TextView=findViewById(R.id.Phone)
        val customerPhone:TextView=findViewById(R.id.CustomerPhone)
        val accType:TextView=findViewById(R.id.AccTyp)
        val customerAccTyp:TextView=findViewById(R.id.CustomerAccTyp)
        val accNumber:TextView=findViewById(R.id.AccNum)
        val customerAccNum:TextView=findViewById(R.id.CustomerAccNum)
        val balance:TextView=findViewById(R.id.NetBalance)
        val customerBalance:TextView=findViewById(R.id.CustomerNetBal)
        val bool=true
        name.isVisible=bool
        customerName.isVisible=bool
        dob.isVisible=bool
        customerDOB.isVisible=bool
        phoneNum.isVisible=bool
        customerPhone.isVisible=bool
        accType.isVisible=bool
        customerAccTyp.isVisible=bool
        accNumber.isVisible=bool
        customerAccNum.isVisible=bool
        balance.isVisible=bool
        customerBalance.isVisible=bool
    }
    private fun viewAccount(){
        val InputAccNum:EditText = findViewById(R.id.InputAccNum)
        val user:List<Customer> = db.customerDao().viewAccount(InputAccNum.text.toString().toLong())
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
        val accNum:TextView = findViewById(R.id.CustomerAccNum)
        val name:TextView = findViewById(R.id.CustomerName)
        val dob:TextView = findViewById(R.id.CustomerDOB)
        val phone:TextView = findViewById(R.id.CustomerPhone)
        val accTyp:TextView = findViewById(R.id.CustomerAccTyp)
        val amount:TextView = findViewById(R.id.CustomerNetBal)
        accNum.text =displayAccNum
        name.text = displayName
        dob.text = displayDOB
        phone.text = displayPhone
        accTyp.text = displayAccTyp
        amount.text = displayAmount
    }
}
