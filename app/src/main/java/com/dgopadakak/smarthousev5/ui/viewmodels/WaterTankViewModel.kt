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
import kotlinx.coroutines.flow.update

class WaterTankViewModel(context: Context) : ViewModel() {
    companion object {
        const val SUBSCRIBE_TOPIC = "from/home/water/#"
        const val FROM_PERCENT_TOPIC = "from/home/water/percent"    // from server to phone
        const val FROM_MODE_TOPIC = "from/home/water/mode"
        const val FROM_PUMP_TOPIC = "from/home/water/pump"
        const val FROM_TIME_TOPIC = "from/home/water/time"
        const val TO_MODE_TOPIC = "to/home/water/mode"              // to server from phone
        const val TO_PUMP_TOPIC = "to/home/water/pump"
        const val TO_REQUEST_TOPIC = "to/home/water/request"
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

    private fun startConnection() {
        _waterTankUiState.update { currentState ->
            currentState.copy(
                ready = false,
                connectionStatus = "Подключение..."
            )
        }
        disposeBag.add(
            mqttHelper.connect()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                    {
                        startSubscription()
                    },
                    {
                        reconnect()
                    }
                )
        )
    }

    private fun startSubscription() {
        _waterTankUiState.update { currentState ->
            currentState.copy(connectionStatus = "Подписка...")
        }
        disposeBag.add(
            mqttHelper.subscribeTopic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                    {
                        startListening()
                        createSubscribers()
                        makeRequests()
                    },
                    {
                        reconnect()
                    }
                )
        )
    }

    private fun startListening() {
        listenerConnectableObservable = mqttHelper.receiveMessages()
        listenerConnectableObservable.connect()
    }

    private fun createSubscribers() {
        _waterTankUiState.update { currentState ->
            currentState.copy(connectionStatus = "Создание слушателей...")
        }
        disposeBag.add(
            listenerConnectableObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        when (it.first) {
                            FROM_PERCENT_TOPIC -> {
                                _waterTankUiState.update { currentState ->
                                    currentState.copy(
                                        percent = it.second.toInt()
                                    )
                                }
                                isPercentsReady = true
                            }
                            FROM_MODE_TOPIC -> {
                                _waterTankUiState.update { currentState ->
                                    currentState.copy(
                                        mode = it.second.toInt(),
                                        modeReady = true
                                    )
                                }
                                isModeReady = true
                            }
                            FROM_PUMP_TOPIC -> {
                                _waterTankUiState.update { currentState ->
                                    currentState.copy(
                                        isPumpOn = it.second == "true",
                                        pumpReady = true
                                    )
                                }
                                isPumpReady = true
                            }
                            FROM_TIME_TOPIC -> {
                                _waterTankUiState.update { currentState ->
                                    currentState.copy(
                                        time = it.second.toInt()
                                    )
                                }
                                isTimeReady = true
                            }
                        }
                        checkIsReady()
                    },
                    {
                        reconnect()
                    }
                )
        )
    }

    private fun checkIsReady() {
        if (isPercentsReady && isModeReady && isPumpReady && isTimeReady) {
            _waterTankUiState.update { currentState ->
                currentState.copy(
                    ready = true,
                    connectionStatus = ""
                )
            }
        }
    }

    private fun makeRequests() {
        _waterTankUiState.update { currentState ->
            currentState.copy(connectionStatus = "Выполнение запросов...")
        }
        TODO()
    }

    private fun reconnect() {
        disposeBag.dispose()
        startConnection()
    }
}
