package com.binar.rv_binar.fragment.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProvider
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.databinding.FragmentDialogEditBinding
import com.binar.rv_binar.viewmodel.NoteViewModel

class DialogEditFragment(update: Note) : AppCompatDialogFragment() {

    private var _binding: FragmentDialogEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private var noteUpdate: Note = update

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.run {
            noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
            _binding = FragmentDialogEditBinding.inflate(layoutInflater).apply {
                edtJudul.setText(noteUpdate.judul)
                edtNote.setText(noteUpdate.note)

                btnInput.setOnClickListener {
                    editNoteData(noteUpdate.id, edtJudul, edtNote)
                }
            }
            AlertDialog.Builder(this).apply {
                setView(binding.root)
            }.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun editNoteData(position : Int, judul : EditText, note : EditText){
        val txtJudul = judul.text.toString()
        val txtNote = note.text.toString()
        val noteData = Note(position, txtJudul, txtNote)
        noteViewModel.updateNote(noteData)
        Toast.makeText(requireContext(), "Data Berhasil diedit", Toast.LENGTH_LONG).show()
        dialog?.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}