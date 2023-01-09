package com.example.broccoli.presentation

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.broccoli.utils.EmailRule
import com.example.broccoli.utils.LengthRule

class FormViewModel : ViewModel() {

    var name: String = ""
    var email: String = ""
    var confirmEmail: String = ""

    //Validation
    private val nameRule = LengthRule(3)
    private val emailRule = EmailRule()

    val nameError = ObservableField<String>()
    val emailError = ObservableField<String>()
    val confirmEmailError = ObservableField<String>()


    // Method to validate the form
    fun validate(): Boolean {
        var isValid = true

        nameError.set(nameRule.validate(name))
        if (nameError.get() != null) {
            isValid = false
        }

        emailError.set(emailRule.validate(email))
        if (emailError.get() != null) {
            isValid = false
        }

        if (confirmEmail != email) {
            confirmEmailError.set("Emails do not match")
            isValid = false
        }

        return isValid
    }

}
