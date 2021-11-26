package com.example.mymvvmstructure.example2.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mymvvmstructure.databinding.FragmentLoginBinding
import com.example.mymvvmstructure.example2.api.AuthApi
import com.example.mymvvmstructure.example2.repository.AuthRepository
import com.example.mymvvmstructure.example2.resource.SealedResources
import com.example.mymvvmstructure.example2.ui.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is SealedResources.Success ->{
                    Toast.makeText(requireContext(),"Success-> ${it.data.success!!.token}",Toast.LENGTH_SHORT).show()
                }

                is SealedResources.Failed ->{
                    Toast.makeText(requireContext(),"Failed -> ${it.networkError}  ",Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val pass = binding.etPass.text.toString()
            viewModel.login(email,pass)
        }
    }

    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}