package com.joydas1902.notes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.joydas1902.notes.R
import com.joydas1902.notes.databinding.FragmentCreateNotesBinding
import com.joydas1902.notes.mvvm.Notes
import com.joydas1902.notes.mvvm.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateNotesFragment : Fragment() {
    private lateinit var binding: FragmentCreateNotesBinding
    private var priority = "3"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.redDot.setImageResource(R.drawable.ic_baseline_done_24)

        binding.greenDot.setOnClickListener {
            priority = "1"
            binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding.yellowDot.setImageResource(0)
            binding.redDot.setImageResource(0)
        }

        binding.yellowDot.setOnClickListener {
            priority = "2"
            binding.yellowDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding.greenDot.setImageResource(0)
            binding.redDot.setImageResource(0)
        }
        
        binding.redDot.setOnClickListener {
            priority = "3"
            binding.redDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding.greenDot.setImageResource(0)
            binding.yellowDot.setImageResource(0)
        }

        binding.saveNoteButton.setOnClickListener { saveNotes(it) }
        return binding.root
    }

    private fun saveNotes(it: View?) {
        val title = binding.title.text.toString()
        val subTitle = binding.subTitle.text.toString()
        val notes = binding.notes.text.toString()
        val date = SimpleDateFormat().format(Date()).toString()

        val data = Notes(null, title, subTitle, notes, date, priority)
        viewModel.insertNotes(data)

        Navigation.findNavController(it!!).navigate((R.id.action_createNotesFragment_to_homeFragment))
    }
}