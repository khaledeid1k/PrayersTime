package com.example.prayerstime.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prayerstime.domain.model.PrayError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    private val _hasError: MutableSharedFlow<PrayError> = MutableSharedFlow()
    val hasError: SharedFlow<PrayError> = _hasError.asSharedFlow()

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
       _hasError.emit(PrayError(exception.message?:"",true))
            }
    }
}