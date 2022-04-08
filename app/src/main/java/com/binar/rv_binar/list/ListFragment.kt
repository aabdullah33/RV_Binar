package com.binar.rv_binar.list

import android.icu.text.IDNA
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.rv_binar.NoteAdapter
import com.binar.rv_binar.R
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.databinding.FragmentListBinding
import com.binar.rv_binar.viewmodel.NoteViewModel

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: NoteViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        // Recyclerview
        val adapter = NoteAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_dialogFragment)
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Note>("key")
            ?.observe(viewLifecycleOwner) { result ->
                val judul = result.judul
                val note = result.note
                insertDataToDatabase(judul,note)
            }

        return binding.root
    }

    private fun insertDataToDatabase(judul : String, note : String) {
        val user = Note(
            0,
            judul,
            note,
        )
        mUserViewModel.insertNote(user)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
    }

}