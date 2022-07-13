package com.joydas1902.notes.mvvm

import androidx.lifecycle.LiveData

class NotesRepository(private val dao: NotesDao) {

    fun getNotes(): LiveData<List<Notes>> = dao.getNotes()

    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()

    fun insertNotes(notes: Notes) { dao.insertNotes(notes) }
    fun deleteNotes(notes: Notes) { dao.deleteNotes(notes) }
    fun updateNotes(notes: Notes) { dao.updateNotes(notes) }
}