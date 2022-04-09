package com.binar.rv_binar.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.binar.rv_binar.HomeActivity
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.data.NoteDatabase
import com.binar.rv_binar.databinding.RvNoteBinding
import com.binar.rv_binar.fragment.dialog.DialogEditFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(val binding: RvNoteBinding) : RecyclerView.ViewHolder(binding.root)

    private var listNote = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = NoteDatabase.getDatabase(holder.itemView.context)
        val currentItem = listNote[position]
        holder.binding.txtJudul.text = currentItem.judul
        holder.binding.txtNote.text = currentItem.note

        holder.binding.btnEdit.setOnClickListener {
            DialogEditFragment(currentItem).show(
                (it.context as HomeActivity).supportFragmentManager,
                null
            )
        }

        holder.binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(it.context).setPositiveButton("Ya") { _, _ ->
                GlobalScope.async {
                    data.noteDao().deleteNote(currentItem)
                    (holder.itemView.context as HomeActivity).runOnUiThread {
                        Toast.makeText(
                            it.context,
                            "Data ${currentItem.judul} berhasil dihapus",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }.setNegativeButton(
                "Tidak"
            ) { p0, _ ->
                p0.dismiss()
            }
                .setMessage("Apakah Anda Yakin ingin menghapus data ${currentItem.judul}")
                .setTitle("Konfirmasi Hapus").create().show()
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setData(note: List<Note>) {
        this.listNote = note
        notifyDataSetChanged()
    }
}