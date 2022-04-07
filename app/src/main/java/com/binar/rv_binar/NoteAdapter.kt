package com.binar.rv_binar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.databinding.RvNoteBinding

class NoteAdapter(val listNote : List<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder (val binding: RvNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtJudul.text = listNote[position].judul
        holder.binding.txtNote.text = listNote[position].note
    }

    override fun getItemCount(): Int {
        return listNote.size
    }
}