package com.joydas1902.notes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.joydas1902.notes.R
import com.joydas1902.notes.databinding.FragmentHomeBinding
import com.joydas1902.notes.mvvm.Notes
import com.joydas1902.notes.mvvm.NotesViewModel
import com.joydas1902.notes.ui.adapter.NotesAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.recyclerViewHome.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        // get all notes
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(notesList)
            binding.recyclerViewHome.adapter = adapter
        }

        // filter high priority notes
        binding.high.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.recyclerViewHome.adapter = adapter
            }
        }

        // filter medium priority notes
        binding.medium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.recyclerViewHome.adapter = adapter
            }
        }

        // filter low priority notes
        binding.low.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.recyclerViewHome.adapter = adapter
            }
        }

        // get all notes
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.recyclerViewHome.adapter = adapter
            }
        }

        binding.addNoteButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                notesFiltering(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltering(newText)
                return true
            }
        })
        return binding.root
    }

    private fun notesFiltering(newText: String?) {
        val filteredList = arrayListOf<Notes>()
        for(i in oldNotes)
            if(i.title!!.lowercase().contains(newText!!) or i.notes!!.lowercase().contains(newText))
                filteredList.add(i)
        adapter.filtering(filteredList)
    }
}