package com.joydas1902.notes.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val dao = NotesDatabase.getNotesDatabase(application).getNotesDao()
    private val repository = NotesRepository(dao)

    fun getNotes(): LiveData<List<Notes>> = repository.getNotes()

    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()
    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()

    fun insertNotes(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNotes(note)
    }
    fun deleteNotes(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNotes(note)
    }
    fun updateNotes(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNotes(note)
    }
}