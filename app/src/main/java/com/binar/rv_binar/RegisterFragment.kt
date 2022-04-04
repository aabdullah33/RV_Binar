package com.binar.rv_binar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.binar.rv_binar.databinding.FragmentLoginBinding
import com.binar.rv_binar.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        binding.btnDaftar.setOnClickListener {
            val uname: String = binding.edtUname.text.toString()
            val email: String = binding.edtREmail.text.toString()
            val pass: String = binding.edtPass.text.toString()
            val cPass: String = binding.edtPassConfirm.text.toString()
            if (uname.isNullOrEmpty() || email.isNullOrEmpty() || pass.isNullOrEmpty() || cPass.isNullOrEmpty()) {
                Toast.makeText(activity, "FILL IN THE BLANKS!!", Toast.LENGTH_SHORT).show()
            } else {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                if (pass == cPass) {
                    editor.putString("uname", uname)
                    editor.putString("email", email)
                    editor.putString("pass", pass)
                    editor.apply()
                    Toast.makeText(activity, "Register Sucessful", Toast.LENGTH_SHORT).show()
                    it.findNavController().navigateUp()
                } else {
                    Toast.makeText(activity, "Password not match, Try again", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}