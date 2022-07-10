package com.joydas1902.notes.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val dao = NotesDatabase.getNotesDatabaseInstance(application).getNotesDao()
    private val repository = NotesRepository(dao)

    fun getNotes(): LiveData<List<Notes>> = repository.getNotes()

    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()

    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()

    fun insertNotes(notes: Notes) { repository.insertNotes(notes) }

    fun deleteNotes(id:Int) { repository.deleteNotes(id) }

    fun updateNotes(notes: Notes) { repository.updateNotes(notes) }
}