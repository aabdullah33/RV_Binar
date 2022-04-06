package com.binar.rv_binar.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.binar.rv_binar.HomeActivity
import com.binar.rv_binar.R
import com.binar.rv_binar.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val str_email = binding.edtEmail.text.toString()
            val str_pass = binding.edtPass.text.toString()

            if(str_email.isNullOrEmpty() || str_pass.isNullOrEmpty()){
                Toast.makeText(activity,"Please Enter Details.",Toast.LENGTH_SHORT).show()
            }else {
                val email_id = sharedPreferences.getString("email", "defaultEmail")
                val password = sharedPreferences.getString("pass", "defaultPass")

                if(email_id.equals(str_email) && password.equals(str_pass)){
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putInt("login", 1)
                    editor.apply()
                    val intent = Intent(activity, HomeActivity::class.java)
                    activity?.startActivity(intent)
                }else {
                    Toast.makeText(activity,"Data Kosong",Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.textRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}