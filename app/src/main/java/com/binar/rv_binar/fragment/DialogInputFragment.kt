package com.binar.rv_binar.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.navigation.fragment.findNavController
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.databinding.FragmentDialogInputBinding

class DialogInputFragment : AppCompatDialogFragment() {

    private var _binding: FragmentDialogInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.run {
            _binding = FragmentDialogInputBinding.inflate(layoutInflater).apply {
                btnInput.setOnClickListener {
                    val judul = edtJudul.text.toString()
                    val note = edtNote.text.toString()
                    val addNote = Note(0, judul, note)
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("key", addNote)
                }
            }
            AlertDialog.Builder(this).apply {
                setView(binding.root)
            }.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}