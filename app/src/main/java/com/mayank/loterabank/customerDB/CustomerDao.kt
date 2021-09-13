package com.mayank.loterabank.customerDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CustomerDao {
    @Insert
    fun insertAccount(customer:Customer)

    @Query("select * from CustomerTable")
    fun getAllData():List<Customer>

    @Query("delete from CustomerTable where accNum = :id")
    fun closeAccount(id: Long)

    @Query("select * from CustomerTable where accNum = :id")
    fun viewAccount(id:Long):List<Customer>

    @Query("update CustomerTable set amount =:amount where accNum = :id")
    fun amountUpdate(id: Long,amount:Double)
}