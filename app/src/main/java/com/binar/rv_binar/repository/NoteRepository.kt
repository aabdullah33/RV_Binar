package com.binar.rv_binar.repository

import androidx.lifecycle.LiveData
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

}