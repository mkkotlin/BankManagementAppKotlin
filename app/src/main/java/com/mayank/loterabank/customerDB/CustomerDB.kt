package com.mayank.loterabank.customerDB

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Customer::class],version = 1)
abstract class CustomerDB:RoomDatabase() {
    abstract fun customerDao():CustomerDao
}