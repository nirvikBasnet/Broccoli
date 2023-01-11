package com.example.broccoli.presentation
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.broccoli.domain.model.User
import com.example.broccoli.repository.Repository
import com.example.broccoli.utils.EmailRule
import com.example.broccoli.utils.LengthRule
import kotlinx.coroutines.launch
import retrofit2.Response


class FormViewModel(private val repository: Repository) : ViewModel() {

    var myResponse: MutableLiveData<Response<String>> = MutableLiveData()


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
    private fun validate(name: String?,email: String?, confirmEmail:String?): Boolean {

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


    private fun pushPost(user: User) {

            viewModelScope.launch {
                val response = repository.sendUser(user)
                myResponse.value = response
            }

        if (myResponse.value?.isSuccessful == true){

        }

    }

    fun submitButtonClicked(name: String?,email: String?,confirmEmail: String?){

        var valid = validate(name,email,confirmEmail)

        if(valid){
            val user = User(name!!, email!!)
            pushPost(user)
        }


    }




}
