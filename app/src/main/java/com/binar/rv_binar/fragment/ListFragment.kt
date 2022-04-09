package com.binar.rv_binar.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.rv_binar.HomeActivity
import com.binar.rv_binar.adapter.NoteAdapter
import com.binar.rv_binar.databinding.FragmentListBinding
import com.binar.rv_binar.fragment.dialog.DialogInputFragment
import com.binar.rv_binar.viewmodel.NoteViewModel

class ListFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel
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

        // NoteViewModel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        binding.floatingActionButton.setOnClickListener {
            DialogInputFragment().show((it.context as HomeActivity).supportFragmentManager, null)
        }

        return binding.root
    }

}