package com.binar.rv_binar

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.data.NoteDatabase
import com.binar.rv_binar.databinding.RvNoteBinding
import com.binar.rv_binar.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder (val binding: RvNoteBinding) : RecyclerView.ViewHolder(binding.root)

    private var listNote = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = NoteDatabase.getDatabase(holder.itemView.context)
        holder.binding.txtJudul.text = listNote[position].judul
        holder.binding.txtNote.text = listNote[position].note

        holder.binding.btnEdit.setOnClickListener{
        }

        holder.binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(it.context).setPositiveButton("Ya") { p0, p1 ->
                GlobalScope.async {
                     data.noteDao().deleteNote(listNote[position])
                    (holder.itemView.context as HomeActivity).runOnUiThread {
                        Toast.makeText(it.context,"Data ${listNote[position].judul} berhasil dihapus",Toast.LENGTH_LONG).show()
                    }
                }
            }.setNegativeButton("Tidak"
            ) { p0, p1 ->
                p0.dismiss()
            }
                .setMessage("Apakah Anda Yakin ingin menghapus data ${listNote[position].judul}").setTitle("Konfirmasi Hapus").create().show()
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setData(note: List<Note>){
        this.listNote = note
        notifyDataSetChanged()
    }
}