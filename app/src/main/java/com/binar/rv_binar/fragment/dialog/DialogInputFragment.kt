package com.binar.rv_binar.fragment.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProvider
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.databinding.FragmentDialogInputBinding
import com.binar.rv_binar.viewmodel.NoteViewModel

class DialogInputFragment : AppCompatDialogFragment() {

    private var _binding: FragmentDialogInputBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.run {
            noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
            _binding = FragmentDialogInputBinding.inflate(layoutInflater).apply {
                btnInput.setOnClickListener {
                    inputData(0, edtJudul, edtNote)
                }
            }
            AlertDialog.Builder(this).apply {
                setView(binding.root)
            }.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun inputData(position : Int, judul : EditText, note : EditText){
        val txtJudul = judul.text.toString()
        val txtNote = note.text.toString()
        val noteData = Note(position, txtJudul, txtNote)
        noteViewModel.insertNote(noteData)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
        dialog?.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}