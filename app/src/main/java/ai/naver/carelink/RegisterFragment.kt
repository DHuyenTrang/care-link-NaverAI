package ai.naver.carelink

import ai.naver.carelink.databinding.FragmentRegisterBinding
import ai.naver.carelink.ui.auth.AuthUiState
import ai.naver.carelink.viewmodel.AuthViewModel
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActions()
        observeData()
    }

    private fun setUpActions() {
        binding.btnBack.setOnClickListener { navigateBack() }

        binding.btnRegister.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (validateInputs(fullName, email, password)) {
                register(fullName, email, password)
            }
        }

        binding.tvLoginLink.setOnClickListener { navigateBack() }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiRegisterState.collect { state ->
                when (state) {
                    is AuthUiState.Idle -> {
                        binding.progressBar.isVisible = false
                        binding.btnRegister.isEnabled = true
                    }

                    is AuthUiState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.btnRegister.isEnabled = false
                    }

                    is AuthUiState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.btnRegister.isEnabled = true

                        showToast(state.message)
                        navigateBack()
                    }

                    is AuthUiState.Error -> {
                        binding.progressBar.isVisible = false
                        binding.btnRegister.isEnabled = true
                        showToast(state.message)
                    }
                }
            }
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun register(fullName: String, email: String, password: String) {
        viewModel.register(email, password, fullName)
    }

    private fun validateInputs(fullName: String, email: String, password: String): Boolean {
        if (fullName.isEmpty()) {
            binding.etFullName.error = "Vui lòng nhập họ tên"
            binding.etFullName.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            binding.etEmail.error = "Vui lòng nhập Email"
            binding.etEmail.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Email không hợp lệ"
            binding.etEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Vui lòng nhập mật khẩu"
            binding.etPassword.requestFocus()
            return false
        }
        if (password.length < 6) {
            binding.etPassword.error = "Mật khẩu phải có ít nhất 6 ký tự"
            binding.etPassword.requestFocus()
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}