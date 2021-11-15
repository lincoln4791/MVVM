package com.example.mymvvmstructure.example2.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mymvvmstructure.databinding.FragmentLoginBinding
import com.example.mymvvmstructure.example2.api.AuthApi
import com.example.mymvvmstructure.example2.repository.AuthRepository
import com.example.mymvvmstructure.example2.ui.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}