package com.codemave.mobilecomputing.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "reminders"
)

data class Reminder(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val reminderId : Long = 0,
    @ColumnInfo(name = "reminder_message") val reminderMessage : String,
    @ColumnInfo(name = "location_x") val location_x : String,
    @ColumnInfo(name = "location_y") val location_y : String,
    @ColumnInfo(name = "reminder_time") val reminder_time : String,
    @ColumnInfo(name = "creation_time") val creation_time : String,
    @ColumnInfo(name = "creator_id") val creator_id : Int,
    @ColumnInfo(name = "reminder_seen") val reminder_seen : Long,
    @ColumnInfo(name = "reminder_icon") val reminder_icon : String
)
