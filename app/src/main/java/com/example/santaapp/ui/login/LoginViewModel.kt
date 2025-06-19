package com.example.santaapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.santaapp.R

class LoginViewModel: ViewModel() {
    val countryCode = MutableLiveData<String>("+1")
    val phoneNumber = MutableLiveData<String>("")
}