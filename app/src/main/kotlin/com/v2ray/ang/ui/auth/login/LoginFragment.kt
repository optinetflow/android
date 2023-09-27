package com.v2ray.ang.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.v2ray.ang.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeLoginState()
    }

    private fun initView() {
        binding.loginButton.setOnClickListener { onLoginBtnClick() }
    }

    private fun observeLoginState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginState.collect {
                    when (it) {
                        is LoginState.Loading -> {
                            println("Loading")
                        }
                        is LoginState.Success -> {
                            println("Success ${it.accessToken}")
                        }
                        is LoginState.Error -> {
                            println("Error ${it.message}")
                        }
                    }
                }
            }
        }
    }

    private fun onLoginBtnClick() {
        val phone = binding.phone.text.toString()
        val password = binding.password.text.toString()

        if (phone.isEmpty()) println("Phone is empty")
        if (password.isEmpty()) println("Password is empty")

        if (phone.isNotEmpty() && password.isNotEmpty()) {
            loginViewModel.login(phone, password)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}