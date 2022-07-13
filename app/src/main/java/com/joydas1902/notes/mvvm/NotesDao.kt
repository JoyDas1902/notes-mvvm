package com.joydas1902.notes.mvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Query("select * from Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("select * from Notes where priority = 1")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("select * from Notes where priority = 2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("select * from Notes where priority = 3")
    fun getLowNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNotes(notes:Notes)

    @Delete
    fun deleteNotes(notes: Notes)

    @Update
    fun updateNotes(notes: Notes)
}