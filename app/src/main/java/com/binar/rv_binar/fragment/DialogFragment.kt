package com.binar.rv_binar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.data.NoteDatabase
import com.binar.rv_binar.databinding.FragmentDialogBinding
import com.binar.rv_binar.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DialogFragment : DialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNoteViewModel: NoteViewModel
    private val args by navArgs<DialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.btnInput.setOnClickListener {
            mNoteViewModel.deleteNote(args.currentNote)
        }
    }
}