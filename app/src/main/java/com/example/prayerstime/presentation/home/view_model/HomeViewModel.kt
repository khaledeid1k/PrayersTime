package com.example.prayerstime.presentation.home.view_model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prayerstime.domain.model.Pray
import com.example.prayerstime.domain.use_case.GetAllTimesPrayUseCase
import com.example.prayerstime.domain.use_case.GetNextPray
import com.example.prayerstime.presentation.home.ui.HomeEvents
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
    val getNextPray: GetNextPray,

    ) : ViewModel(), HomeEvents {
    private val _homeState: MutableStateFlow<Pray> = MutableStateFlow(Pray())
    val homeState: StateFlow<Pray> = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllTimesPrayUseCase(30.8024, 26.8206, 4)
                ?.apply {
                    val nextPray = getNextPray(this.prayItems, this.date)
                    _homeState.update {
                            it.copy(
                                date = this.date,
                                location = this.location,
                                nextPray =nextPray.first?:"",
                                remainTimeHour =nextPray.second,
                                remainTimeMinute = nextPray.third,
                                prayItems = this.prayItems
                            )

                        }
                    }
            }

        }


    override  fun updateLeftTime(){
            val nextPray = getNextPray(_homeState.value.prayItems, _homeState.value.date)
            _homeState.update {
                it.copy(
                    nextPray = nextPray.first ?: "",
                    remainTimeHour = nextPray.second,
                    remainTimeMinute = nextPray.third,
                )


        }
    }


    override fun showQibla() {


    }

    override fun getSelectedPrayersDate(count: Long) {
        viewModelScope.launch {
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