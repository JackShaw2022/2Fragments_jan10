package com.example.a2fragments_jan10

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.a2fragments_jan10.databinding.FragmentFormBinding

// Import androidx fragment
class FormFragment : Fragment() {

    // ? after type means nullable
    private var _binding: FragmentFormBinding? = null

    // !! asserting non null, don't do too often can cause NPE(null pointer exception)
    private val binding: FragmentFormBinding get() = _binding!!

    // Instantiate the sign in fields.
    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            emailEt.editText?.addTextChangedListener { text ->
                Log.d(TAG, text.toString())

                nextBtn.isEnabled = text.toString().length > 8
            }

            nextBtn.setOnClickListener {
                Log.d(TAG, "nexBtn Clicked")

                email = emailEt.editText?.text.toString()
                password = passwordEt.editText?.text.toString()

                // Verify the user's credentials
                if(email != "john@mymail.com") {
                    Toast.makeText(context,"Email wrong: john@mymail.com \n Password wrong: 123456",Toast.LENGTH_SHORT).show()
                }
                else if (password != "123456"){
                    Toast.makeText(context, "Email wrong: john@mymail.com \n Password wrong: 123456", Toast.LENGTH_LONG).show()
                }
                else{
                    // Create bundle to pass data in fragment transaction.
                    val bundle = Bundle()
                    bundle.putString("Email", email)
                    bundle.putString("Password", password)

                    Log.d(TAG, "email is $email, password is $password")

                    // FragmentManger allows us to perform transaction.
                    // Use replace to switch between fragments.
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, InfoFragment::class.java, bundle)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    // Set binding to null to release resource for garbage collection.
    // Only in fragments.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = FormFragment::class.java.name
    }
}
