package com.codemave.mobilecomputing.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.codemave.mobilecomputing.data.entity.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ReminderDao {



    @Query("SELECT * FROM reminders LIMIT 15")
    abstract fun reminders(): Flow<List<Reminder>>


    @Query("""SELECT * FROM reminders WHERE id = :reminderId""")
    abstract fun reminder(reminderId: Long): Reminder?



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Reminder): Long



    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: Reminder)

    @Delete
    abstract suspend fun delete(entity: Reminder): Int

    @Query("DELETE FROM reminders WHERE id = :reminderId")
    abstract suspend fun deleteReminderWithId(reminderId: Long)
}