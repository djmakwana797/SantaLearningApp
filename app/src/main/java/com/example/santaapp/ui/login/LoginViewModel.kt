package com.example.santaapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    val countryCode = MutableLiveData<String>("+1")
    val phoneNumber = MutableLiveData<String>("")
}