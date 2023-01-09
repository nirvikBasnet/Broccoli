package com.example.broccoli.presentation

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.broccoli.domain.model.User
import com.example.broccoli.domain.model.UserService
import com.example.broccoli.utils.EmailRule
import com.example.broccoli.utils.LengthRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FormViewModel : ViewModel() {

    //to hide and show the form
    var isVisible: Boolean = false
        set(value) {
            field = value
            visible.set(value)
        }

    val visible = ObservableField<Boolean>()

    fun toggleVisibility() {
        isVisible = !isVisible
    }

    //Field Validation
    private val nameRule = LengthRule(3)
    private val emailRule = EmailRule()

    val nameError = ObservableField<String>()
    val emailError = ObservableField<String>()
    val confirmEmailError = ObservableField<String>()


    // Method to validate the form
    fun validate(name: String?,email: String?, confirmEmail:String?): Boolean {

        var isValid = true

        nameError.set(name?.let { nameRule.validate(it) })
        if (nameError.get() != null) {
            isValid = false
        }

        emailError.set(email?.let { emailRule.validate(it) })
        if (emailError.get() != null) {
            emailError.set("Email format do not match")
            isValid = false
        }

        if (confirmEmail != email) {
            confirmEmailError.set("Emails do not match")
            isValid = false
        }

        return isValid
    }

    // In your ViewModel or other appropriate place in your app
    val retrofit = Retrofit.Builder()
        .baseUrl("https://us-central1-blinkapp-684c1.cloudfunctions.net")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val userService = retrofit.create(UserService::class.java)

    val scope = CoroutineScope(Dispatchers.IO)



    fun sendUserData(name: String?, email: String?, confirmEmail: String?) {
        if(validate(name, email, confirmEmail)){
            scope.launch {
                try {
                    val user = name?.let {
                        if (email != null) {
                            User(it, email)
                        }
                    }
                    val response = userService.createUser(user)
                    if (response.isSuccessful) {
                        Log.d("SERVER" ,response.message())
                    } else {
                        // There was an error sending the user data
                    }
                } catch (e: Exception) {
                    // There was an error sending the user data
                }
            }
        }

    }




}
