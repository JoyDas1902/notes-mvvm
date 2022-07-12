package com.joydas1902.notes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.joydas1902.notes.R
import com.joydas1902.notes.databinding.ItemNotesBinding
import com.joydas1902.notes.mvvm.Notes
import com.joydas1902.notes.ui.fragments.HomeFragmentDirections

class NotesAdapter(var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun filtering(filteredList: ArrayList<Notes>) {
        notesList = filteredList
        notifyDataSetChanged()
    }

    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes = notesList.get(position)
        holder.binding.notesTitle.text = notes.title
        holder.binding.notesItem.text = notes.notes
        holder.binding.notesDate.text = notes.date

        when (notes.priority) {
            "1" -> holder.binding.notesPriority.setBackgroundResource(R.drawable.green_dot)
            "2" -> holder.binding.notesPriority.setBackgroundResource(R.drawable.yellow_dot)
            "3" -> holder.binding.notesPriority.setBackgroundResource(R.drawable.red_dot)
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(notes)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notesList.size
}