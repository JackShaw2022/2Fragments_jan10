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
            // Data in fragment manager is arguments instead of intent.
            firstNameTv.text = arguments?.getString("First") ?: "First"
            lastNameTv.text = arguments?.getString("Last") ?: "Last"
            emailTv.text = arguments?.getString("Email") ?: "Email"
            passwordTv.text = arguments?.getString("Password") ?: "Passwords"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}