package com.example.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSignInBinding
import com.example.todoapp.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding : FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun init(view: View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents(){

        binding.textViewAuth.setOnClickListener {
            navControl.navigate(R.id.action_signInFragment_to_signUpFragment)
        }


        binding.signIn.setOnClickListener {
            val email = binding.emailText.text.toString().trim()
            val pass = binding.passwordText.text.toString().trim()


            if(email.isNotEmpty() && pass.isNotEmpty()){
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful ){
                                Toast.makeText(context, "Logado com sucesso!", Toast.LENGTH_SHORT).show()
                                navControl.navigate(R.id.action_signInFragment_to_homeFragment)
                            }else{
                                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        })

            }

        }
    }

}