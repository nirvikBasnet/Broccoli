package com.example.broccoli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.broccoli.databinding.ActivityMainBinding
import com.example.broccoli.presentation.FormViewModel
import com.example.broccoli.presentation.FormViewModelFactory
import com.example.broccoli.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val repository = Repository()
        val viewModelFactory = FormViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FormViewModel::class.java)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel

        //I am setting initial value of name,email and confirm email to empty string "", for validation purpose
        binding.name = ""
        binding.email = ""
        binding.confirmEmail = ""


    }
}