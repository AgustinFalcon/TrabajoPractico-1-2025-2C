package com.ifts4.primertrabajo2doc.fragments.third

import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThirdViewModel : ViewModel() {

    //var viewState = MutableLiveData<ThirdEnums>()
    var viewState = MutableLiveData<ThirdStates>()

    private var email: String = ""
    private var password: String = ""


    fun validateEmail(email: String) {
        this.email = email
        if (email.isNotBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.value = ThirdStates.SuccessEmail

        } else {
            viewState.value = ThirdStates.ErrorEmail
        }

        validateButton()
    }

    fun validatePassword(password: String) {
        this.password = password
        if (password.isNotBlank() && password.length > 3) {
            viewState.value = ThirdStates.SuccessPassword
        } else {
            viewState.value = ThirdStates.ErrorPassword(password = password)
        }

        validateButton()
    }

    fun validateButton() {
        if ((email.isNotBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches())
            && password.isNotBlank() && password.length > 3) {

            viewState.value = ThirdStates.SuccessButton

        } else {
            viewState.value = ThirdStates.ErrorButton
        }
    }


}

sealed class ThirdStates() {
    object SuccessEmail: ThirdStates()
    object ErrorEmail: ThirdStates()
    object SuccessPassword: ThirdStates()
    data class ErrorPassword(val password: String): ThirdStates()
    object SuccessButton: ThirdStates()
    object ErrorButton: ThirdStates()
}
