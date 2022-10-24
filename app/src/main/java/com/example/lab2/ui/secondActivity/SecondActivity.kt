package com.example.lab2.ui.secondActivity

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lab2.databinding.FragmentSecondBinding

class SecondActivity : Fragment() {
    private lateinit var secondViewModel: SecondViewModel
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondViewModel = ViewModelProvider(this)[SecondViewModel::class.java].apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        val navButton = binding.navButton
        secondViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            if (secondViewModel.index.value == 2) {
                navButton.visibility = View.VISIBLE
            }
            else {
                navButton.visibility = View.GONE
            }
        }
        navButton.setOnClickListener{goToDrawerActivity()}
        return root
    }

    private fun goToDrawerActivity(){
        /* Немає сенсу використовувати інтент,
         тому що під час запуску інтенту ми не повертаємося на попердню активність,
         а створюємо її копію і вона відкривається поверх попередніх. */
//        val intent = Intent (activity, MainActivity::class.java)
//        activity!!.startActivity(intent)
        requireActivity().finish()
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): SecondActivity {
            return SecondActivity().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}