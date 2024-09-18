package com.example.hiltgradleimplementation.views

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val sharedPreference: SharedPreferences
) : ViewModel() {

    private val counter = MutableStateFlow(0)
    val _counter: StateFlow<Int> = counter.asStateFlow()

    init {
        val savedCounter = sharedPreference.getInt("counter", 0)
        counter.value = savedCounter
    }

    fun increment() {
        var currentCounter = counter.value
        currentCounter++
        counter.value = currentCounter
        setCounter(currentCounter)
    }

    private fun setCounter(value: Int) {
        sharedPreference.edit().putInt("counter", value).apply()
    }

}