package com.mayank.loterabank.customerDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CustomerTable")
data class Customer(
    @PrimaryKey() val accNum:String,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "dob") val dob:String,
    @ColumnInfo(name = "phone") val phone:String,
    @ColumnInfo(name = "accTyp") val accTyp:String,
    @ColumnInfo(name = "amount") val amount:Double
)