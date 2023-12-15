package com.dgopadakak.smarthousev5.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dgopadakak.smarthousev5.data.MqttHelper
import com.dgopadakak.smarthousev5.ui.states.WaterTankUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WaterTankViewModel(context: Context) : ViewModel() {
    companion object {
        const val SUBSCRIBE_TOPIC = "from/home/water/#"
        const val FROM_PERCENT_TOPIC = "from/home/water/percent"    // from server to phone
        const val FROM_MODE_TOPIC = "from/home/water/mode"
        const val FROM_PUMP_TOPIC = "from/home/water/pump"
        const val FROM_TIME_TOPIC = "from/home/water/time"
        const val TO_PERCENT_TOPIC = "to/home/water/percent"        // to server from phone
        const val TO_MODE_TOPIC = "to/home/water/mode"
        const val TO_PUMP_TOPIC = "to/home/water/pump"
        const val TO_TIME_TOPIC = "to/home/water/time"
    }

    private val _waterTankUiState = MutableStateFlow(WaterTankUiState())
    val waterTankUiState: StateFlow<WaterTankUiState> = _waterTankUiState.asStateFlow()

    private val mqttHelper = MqttHelper(context = context, topic = SUBSCRIBE_TOPIC)
    private lateinit var listenerConnectableObservable: ConnectableObservable<Pair<String, String>>
    private val disposeBag = CompositeDisposable()

    private var isPercentsReady = false
    private var isModeReady = false
    private var isPumpReady = false
    private var isTimeReady = false

    init {
        startConnection()
    }

    fun startConnection() {
        _waterTankUiState.value = _waterTankUiState.value.copy(ready = false)
        disposeBag.add(
            mqttHelper.connect()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                    {
                        startSubscription()
                    },
                    {
                        /*TODO*/
                    }
                )
        )
    }

    private fun startSubscription() {
        disposeBag.add(
            mqttHelper.subscribeTopic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                    {
                        startListening()
                        createPercentSubscriber()
                        createModeSubscriber()
                        createPumpSubscriber()
                        createTimeSubscriber()
                    },
                    {
                        /*TODO*/
                    }
                )
        )
    }

    fun startListening() {
        listenerConnectableObservable = mqttHelper.receiveMessages()
        listenerConnectableObservable.connect()
    }

    private fun createPercentSubscriber() {
        disposeBag.add(
            listenerConnectableObservable
                .filter { it.first == FROM_PERCENT_TOPIC }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _waterTankUiState.value = _waterTankUiState.value.copy(
                            percent = it.second.toInt()
                        )
                        isPercentsReady = true
                        checkIsReady()
                    },
                    {
                        /*TODO*/
                    }
                )
        )
    }

    private fun createModeSubscriber() {
        disposeBag.add(
            listenerConnectableObservable
                .filter { it.first == FROM_MODE_TOPIC }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _waterTankUiState.value = _waterTankUiState.value.copy(
                            mode = it.second.toInt(),
                            modeReady = true
                        )
                        isModeReady = true
                        checkIsReady()
                    },
                    {
                        /*TODO*/
                    }
                )
        )
    }

    private fun createPumpSubscriber() {
        disposeBag.add(
            listenerConnectableObservable
                .filter { it.first == FROM_PUMP_TOPIC }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _waterTankUiState.value = _waterTankUiState.value.copy(
                            isPumpOn = it.second == "true",
                            pumpReady = true
                        )
                        isPumpReady = true
                        checkIsReady()
                    },
                    {
                        /*TODO*/
                    }
                )
        )
    }

    private fun createTimeSubscriber() {
        disposeBag.add(
            listenerConnectableObservable
                .filter { it.first == FROM_TIME_TOPIC }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _waterTankUiState.value = _waterTankUiState.value.copy(
                            time = it.second.toInt()
                        )
                        isTimeReady = true
                        checkIsReady()
                    },
                    {
                        /*TODO*/
                    }
                )
        )
    }

    private fun checkIsReady() {
        if (isPercentsReady && isModeReady && isPumpReady && isTimeReady) {
            _waterTankUiState.value = _waterTankUiState.value.copy(ready = true)
        }
    }
}
