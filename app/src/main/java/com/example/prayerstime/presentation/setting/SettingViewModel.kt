package com.example.prayerstime.presentation.setting

import com.example.prayerstime.core.base.BaseViewModel
import com.example.prayerstime.domain.model.Setting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(): BaseViewModel(), SettingEvent {
    private val _settingState: MutableStateFlow<Setting> = MutableStateFlow(Setting())
    val settingState: StateFlow<Setting> = _settingState.asStateFlow()
    override fun  isPermeationGranted(isGranted:Boolean,latitude:Double,longitude:Double){
        _settingState.update {
            it.copy(
                latitude = latitude,
                longitude = longitude,

            )
        }

    }

    override fun selectedMethod(method: Int) {
        _settingState.update {
            it.copy(
                method=method
            )
        }

    }

    override fun navigateToHome() {

    }
}
