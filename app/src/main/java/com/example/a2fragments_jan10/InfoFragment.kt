package com.example.a2fragments_jan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2fragments_jan10.databinding.FragmentInfoBinding

class InfoFragment: Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding: FragmentInfoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            // Put the user data back in the EditText
            val bundle = Bundle()
            bundle.putString("Email", arguments?.getString("Email") ?: "Email")
            bundle.putString("Password", arguments?.getString("Password") ?: "Password")

            // Data in fragment manager is arguments instead of intent.
            doneBtn.setOnClickListener{
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, FormFragment::class.java, bundle)
                    .addToBackStack(null)
                    .commit()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}