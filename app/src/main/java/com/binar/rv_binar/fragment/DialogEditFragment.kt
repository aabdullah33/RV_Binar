package com.binar.rv_binar.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.navigation.fragment.findNavController
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.databinding.FragmentDialogEditBinding

class DialogEditFragment : AppCompatDialogFragment() {

    private var _binding: FragmentDialogEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.run {
            _binding = FragmentDialogEditBinding.inflate(layoutInflater).apply {
                btnInput.setOnClickListener {
                    val judul = edtJudul.text.toString()
                    val note = edtNote.text.toString()
                    val editNote = Note(0, judul, note)
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("key", editNote)
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