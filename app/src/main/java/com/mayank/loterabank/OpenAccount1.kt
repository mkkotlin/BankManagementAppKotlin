package com.mayank.loterabank

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.mayank.loterabank.customerDB.Customer
import com.mayank.loterabank.customerDB.CustomerDB
import com.mayank.loterabank.customerDB.CustomerDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import kotlin.random.Random

class OpenAccount1 : AppCompatActivity() {
    lateinit var db:CustomerDB
    @SuppressLint("ResourceType", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_account)

        title= "AccountOpener"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        accNum()

        val calendar= Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val date:Button=findViewById(R.id.InputDOB)
        date.setOnClickListener {

            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
            { view, year, monthOfYear, dayOfMonth -> date.text = "" + dayOfMonth + " - " + (monthOfYear + 1) + " - " + year }, year, month, day)
            datePickerDialog.show()
        }

        val accNum:TextView=findViewById(R.id.InputAccNum)
        val name:EditText=findViewById(R.id.InputName)
        val dob:Button=findViewById(R.id.InputDOB)
        val phone:EditText=findViewById(R.id.InputPhone)
        val accTyp:EditText=findViewById(R.id.InputType)
        val amount:EditText=findViewById(R.id.InputAmount)


        val buttonC:Button=findViewById(R.id.CreateAccount)
        buttonC.setOnClickListener {
            db = Room.databaseBuilder(applicationContext,CustomerDB::class.java,"UserDB").build()
            val values= Customer(accNum.text.toString(),
            name.text.toString(),dob.text.toString(),phone.text.toString(),accTyp.text.toString(),
            amount.text.toString().toDouble())

            GlobalScope.launch {
                db.customerDao().insertAccount(values)
            }
            Toast.makeText(this,"successfull",Toast.LENGTH_SHORT).show()

        }
    }

    private fun accNum(){
        val a= List(2){Random.nextInt(1000000, 99999999)}
        val c = a.elementAt(0).toString()+a.elementAt(1).toString()
        val accNumber:TextView = findViewById(R.id.InputAccNum)
        accNumber.text= c}

}