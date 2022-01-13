package com.example.a2fragments_jan10
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.a2fragments_jan10.databinding.FragmentEmailFormBinding


// Import androidx fragment
class EmailFragment: Fragment() {

    // Let the binding be nullable
    private var _binding: FragmentEmailFormBinding? = null

    // !! Assert the binding is non-null for certain, but be careful about an NPE (null pointer exception)
    private val binding: FragmentEmailFormBinding get() = _binding!!

    private var firstName = ""
    private var lastName = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmailFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            emailEt.editText?.addTextChangedListener { text ->
                Log.d(EmailFragment.TAG, text.toString())
                firstName = (arguments?.get("First") ?: "First").toString()
                lastName = (arguments?.get("Last") ?: "Last").toString()

                nextBtn.isEnabled = text.toString().length > 4
            }

            nextBtn.setOnClickListener {
                Log.d(EmailFragment.TAG, "nexBtn Clicked")
                // Create bundle to pass data in fragment transaction.
                val email = emailEt.editText?.text.toString()
                val bundle = Bundle()
                bundle.putString("First", firstName)
                bundle.putString("Last", lastName)
                bundle.putString("Email", email)

                Log.d(EmailFragment.TAG, "email is $email")

                // FragmentManger allows us to perform transaction.
                // Use replace to switch between fragments.
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, PasswordFragment::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            }s
        }
    }
        // Set binding to null to release resource for garbage collection.
        // Only in fragments.
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        companion object {
            val TAG = EmailFragment::class.java.name
        }
}
