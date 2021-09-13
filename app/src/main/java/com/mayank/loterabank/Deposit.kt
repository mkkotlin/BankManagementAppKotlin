package com.mayank.loterabank

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class Deposit : AppCompatActivity() {
    lateinit var db:CustomerDB
    var money:Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)

        val next:Button = findViewById(R.id.Next)
        title = "Deposit Money"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val netAmount:TextView=findViewById(R.id.NetAmount)
        val netAmountDisplay:TextView=findViewById(R.id.Balance)
        val amountWithdraw:TextView=findViewById(R.id.WithdrawAmount)
        val amountWithdrawInput:TextView=findViewById(R.id.WithdrawInput)
        val withDrawMoney:Button=findViewById(R.id.WithdrawMoney)
        val message:TextView=findViewById(R.id.msg)
        netAmount.isVisible=false
        netAmountDisplay.isVisible=false
        amountWithdraw.isVisible=false
        amountWithdrawInput.isVisible=false
        withDrawMoney.isVisible=false
        message.isVisible=false

//       val accInputAccount:EditText = findViewById(R.id.InputAccNum)

        db = Room.databaseBuilder(applicationContext,CustomerDB::class.java,"UserDB").build()

        next.setOnClickListener {
            netAmount.isVisible=true
            netAmountDisplay.isVisible=true
            amountWithdraw.isVisible=true
            amountWithdrawInput.isVisible=true
            withDrawMoney.isVisible=true
            message.isVisible=true

            GlobalScope.launch { setValue()
                setToUpdate()}

        }

    }

    private fun setValue():Double{
        val accInputAccount:EditText = findViewById(R.id.InputAccNum)
        val user: List<Customer> = db.customerDao().viewAccount(accInputAccount.text.toString().toLong())
        var displayAmount=""
        for(user:Customer in user){
            displayAmount = "${user.amount}"
        }
        val amount:TextView = findViewById(R.id.Balance)
        amount.text = displayAmount
        money = displayAmount.toDouble()
        return money
    }
    @SuppressLint("SetTextI18n")
    private fun setToUpdate(){
        val accInputAccount:EditText = findViewById(R.id.InputAccNum)
        val message: TextView = findViewById(R.id.msg)
        val b:EditText = findViewById(R.id.WithdrawInput)
        val withdrawMoney:Button = findViewById(R.id.WithdrawMoney)
        withdrawMoney.setOnClickListener {
            val newAmount = money + (b.text).toString().toDouble()
            message.text= "Your Balance is: $newAmount"
            GlobalScope.launch { db.customerDao().amountUpdate(accInputAccount.text.toString().toLong(),newAmount) }
        }//5097903935154182
    }
}