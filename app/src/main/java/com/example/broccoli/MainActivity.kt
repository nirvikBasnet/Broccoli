package com.example.broccoli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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


        val repository = Repository()
        val viewModelFactory = FormViewModelFactory(repository,this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FormViewModel::class.java)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel

        //I am setting initial value of name,email and confirm email to empty string "", for validation purpose
        binding.name = ""
        binding.email = ""
        binding.confirmEmail = ""


        //observing myResponse to show success/error to user upon submiting
        viewModel.myResponse.observe(this, Observer { response ->
            if (response != null) {
                //handle response
                //for example:
                if (response.isSuccessful) {
                    val responseString = response.body()?.toString()
                    showPopUp("Congratulations!!","Invitation is sent to your email!",true)
                    viewModel.isVisible = false
                }else{
                    Toast.makeText(this,"Error" , Toast.LENGTH_SHORT).show()
                }
            }
        })


        //observing userCanceled to show canceled pop up
        //TODO: change this logic using shared preference (isInvited)
        viewModel.userCanceled.observe(
            this
        ) {
            if (it) {
                showPopUp("Invitation Canceled!!", "Invitation is canceled.",false)
            }
        }

        //checking shared pref if the user is invited to decide what to show initially
        viewModel.isInvited.observe(this){
            if(it){
                viewModel.isCancleButtonEnabled.set(true)
                viewModel.isInviteButtonEnabled.set(false)
            }else{
                viewModel.isCancleButtonEnabled.set(false)
                viewModel.isInviteButtonEnabled.set(true)
            }


        }


        //while getting data
        viewModel.isLoading.observe(this){
            if(it){
                binding.submitBtn.setText("Submitting...")
                binding.nameLayout.isEnabled = false
                binding.emailLayout.isEnabled = false
                binding.confirmEmailLayout.isEnabled = false
                binding.inviteBtn.isEnabled = false
            }else{
                binding.submitBtn.setText("Submit")
                binding.nameLayout.isEnabled = true
                binding.emailLayout.isEnabled = true
                binding.confirmEmailLayout.isEnabled = true
                binding.inviteBtn.isEnabled = true
            }
        }



    }

    //common function for popup
    //TODO: move it to util
    fun showPopUp(messageTitle: String, message :String, isInvited: Boolean){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(messageTitle)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            viewModel.isInvited.value = isInvited
            viewModel.editor.putBoolean(viewModel.invitedKey,isInvited)
            viewModel.editor.apply()
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}
