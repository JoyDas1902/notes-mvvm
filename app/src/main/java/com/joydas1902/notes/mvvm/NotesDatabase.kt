package com.joydas1902.notes.mvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase?=null

        fun getNotesDatabaseInstance(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance = Room.databaseBuilder(
                        context,
                        NotesDatabase::class.java,
                        "Notes"
                    ).allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }
    }
}