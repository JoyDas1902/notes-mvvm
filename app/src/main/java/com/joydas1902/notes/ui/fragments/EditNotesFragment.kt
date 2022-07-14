package com.joydas1902.notes.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.joydas1902.notes.R
import com.joydas1902.notes.databinding.FragmentEditNotesBinding
import com.joydas1902.notes.mvvm.Notes
import com.joydas1902.notes.mvvm.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditNotesFragment : Fragment() {

    private val oldNotes by navArgs<EditNotesFragmentArgs>()
    private lateinit var binding: FragmentEditNotesBinding
    private var priority = "3"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        binding.title.setText(oldNotes.data.title)
        binding.subTitle.setText(oldNotes.data.subTitle)
        binding.notes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.yellowDot.setImageResource(0)
                binding.redDot.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.yellowDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.greenDot.setImageResource(0)
                binding.redDot.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.redDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.greenDot.setImageResource(0)
                binding.yellowDot.setImageResource(0)
            }
        }

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

        binding.saveNoteButton.setOnClickListener { updateNote(it) }

        binding.deleteIcon.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            dialog.setContentView(R.layout.delete_dialog)
            dialog.show()

            val yes = dialog.findViewById<TextView>(R.id.yes)
            val no = dialog.findViewById<TextView>(R.id.no)

            yes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data)
                dialog.dismiss()
            }
            no?.setOnClickListener { dialog.dismiss() }
        }
        return binding.root
    }

    private fun updateNote(it: View?) {
        val title = binding.title.text.toString()
        val subTitle = binding.subTitle.text.toString()
        val notes = binding.notes.text.toString()
        val date = SimpleDateFormat().format(Date()).toString()

        val data = Notes(oldNotes.data.id, title, subTitle, notes, date, priority)
        viewModel.updateNotes(data)

        Navigation.findNavController(it!!).navigate((R.id.action_editNotesFragment_to_homeFragment))
    }
}