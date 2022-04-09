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
            val strEmail = binding.edtEmail.text.toString()
            val strPass = binding.edtPass.text.toString()
            val emailId = sharedPreferences.getString("email", "defaultEmail")
            val password = sharedPreferences.getString("pass", "defaultPass")

            checkLogin(emailId, password, strEmail, strPass)
        }

        binding.textRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun checkLogin(
        emailSP: String?,
        passSP: String?,
        emailInput: String,
        passInput: String
    ) {

        if (emailInput.isNotBlank() || passInput.isNotBlank()) {

            if (emailSP.equals(emailInput) && passSP.equals(passInput)) {

                val editor: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE).edit()
                editor.putInt("login", 1).apply()
                val intent = Intent(activity, HomeActivity::class.java)
                activity?.startActivity(intent)

            } else if (emailSP.equals(emailInput) && !passSP.equals(passInput)) {
                Toast.makeText(activity, "Password Salah!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(activity, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(activity, "Mohon Isi Semua", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}