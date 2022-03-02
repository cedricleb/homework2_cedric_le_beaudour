package com.codemave.mobilecomputing

import android.content.Context
import androidx.room.Room
import com.codemave.mobilecomputing.data.repository.ReminderRepository
import com.codemave.mobilecomputing.data.room.MobileComputingDatabase


//A simple singleton dependency graph
//(For real apps, use Koin/Dagger/Hilt instead)

object Graph {
    lateinit var database: MobileComputingDatabase

    val reminderRepository by lazy {
        ReminderRepository(
            reminderDao = database.reminderDao()
        )
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, MobileComputingDatabase::class.java, "mcData.db")
            .fallbackToDestructiveMigration() // don't use this in production app
            .allowMainThreadQueries()
            .build()
    }
}