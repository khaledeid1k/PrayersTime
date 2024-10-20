package com.example.prayerstime.presentation.home.view_model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.example.prayerstime.core.base.BaseViewModel
import com.example.prayerstime.domain.model.Pray
import com.example.prayerstime.domain.model.PrayError
import com.example.prayerstime.domain.use_case.GetAllTimesPrayUseCase
import com.example.prayerstime.domain.use_case.GetNextPrayUseCase
import com.example.prayerstime.domain.use_case.GetSavedPrayersUseCase
import com.example.prayerstime.domain.use_case.SavePrayersDataUseCase
import com.example.prayerstime.presentation.home.ui.HomeEvents
import com.example.prayerstime.utils.convertDate
import com.example.prayerstime.utils.dateDay
import com.example.prayerstime.utils.getSelectedDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    val getAllTimesPrayUseCase: GetAllTimesPrayUseCase,
    val getNextPrayUseCase: GetNextPrayUseCase,
    val savePrayersDataUseCase: SavePrayersDataUseCase,
    val getSavedPrayersUseCase: GetSavedPrayersUseCase,


    ) : BaseViewModel(), HomeEvents {
    private val _homeState: MutableStateFlow<Pray> = MutableStateFlow(Pray())
    val homeState: StateFlow<Pray> = _homeState.asStateFlow()


    init { getAllPray() }

    override fun getAllPray() {
        viewModelScope.launch(exceptionHandler) {
            checkError()
            getAllTimesPrayUseCase(30.8024, 26.8206, 4)?.apply {
                val nextPray = getNextPrayUseCase(this.prayItems, this.date)
                savePrayersDataUseCase(this)
                _homeState.update {
                    it.copy(
                        date = this.date,
                        location = this.location,
                        nextPray = nextPray.first ?: "",
                        remainTimeHour = nextPray.second,
                        remainTimeMinute = nextPray.third,
                        prayItems = this.prayItems,
                        error = PrayError("", false)
                    )

                }
            }
        }
    }

    private fun checkError() {
        viewModelScope.launch {
            hasError.collect { error ->
                _homeState.update {
                    it.copy(
                        error = PrayError(error.message, error.errorState)
                    )
                }
                getSavedPrayers()

            }
       }
    }

    private fun getSavedPrayers(){
        viewModelScope.launch {
            val prayers = getSavedPrayersUseCase(dateDay().convertDate(), 30.8024, 26.8206, 4)
            prayers?.apply {
                val nextPray = getNextPrayUseCase(prayers.prayItems, prayers.date)
                _homeState.update {
                    it.copy(
                        date = prayers.date,
                        location = prayers.location,
                        nextPray = nextPray.first ?: "",
                        remainTimeHour = nextPray.second,
                        remainTimeMinute = nextPray.third,
                        prayItems = prayers.prayItems,
                        error = PrayError(error.message, error.errorState)
                    )
                }
            }
        }
    }

    override fun updateLeftTime() {
        val nextPray = getNextPrayUseCase(_homeState.value.prayItems, _homeState.value.date)
        _homeState.update {
            it.copy(
                nextPray = nextPray.first ?: "",
                remainTimeHour = nextPray.second,
                remainTimeMinute = nextPray.third,
                error = PrayError("", false)
            )
        }
    }


    override fun showQibla() {


    }

    override fun getSelectedPrayersDate(count: Long) {
        checkError()
        viewModelScope.launch(exceptionHandler) {
            getAllTimesPrayUseCase(30.8024, 26.8206, 4, date = getSelectedDate(count))
                ?.apply {
                    _homeState.update {
                        it.copy(
                            date = this.date,
                            location = this.location,
                            prayItems = this.prayItems
                        )

                    }
                }
        }
    }


}