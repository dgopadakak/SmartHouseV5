package com.dgopadakak.smarthousev5.data

import android.content.Context
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

class MqttHelper(context: Context, private val topic: String) {
    companion object {
        const val SERVER_URI = "ssl://a71be432bdd2446aa7021c159691067d.s1.eu.hivemq.cloud:8883"
        const val USERNAME = "sh_dgopadakak"
        const val PASSWD = "Rp2T4eCx@_!"
        const val CLIENT_ID = "phone"
        const val QOS = 2
    }

    private var mqttAndroidClient: MqttAndroidClient = MqttAndroidClient(
        context, SERVER_URI, CLIENT_ID
    )

    fun connect(): Completable {
        return Completable.create { subscriber ->
            try {
                val mqttConnectOptions = MqttConnectOptions()
                mqttConnectOptions.userName = USERNAME
                mqttConnectOptions.password = PASSWD.toCharArray()

                val token = mqttAndroidClient.connect(mqttConnectOptions)
                token.actionCallback = object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken) {
                        subscriber.onComplete()
                    }

                    override fun onFailure(asyncActionToken: IMqttToken, e: Throwable) {
                        subscriber.onError(e)
                    }
                }
            } catch (e: MqttException) {
                subscriber.onError(e)
            }
        }
    }

    fun subscribeTopic(): Completable {
        return Completable.create { subscriber ->
            try {
                mqttAndroidClient.subscribe(topic, QOS, null, object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken) {
                        subscriber.onComplete()
                    }

                    override fun onFailure(asyncActionToken: IMqttToken, e: Throwable) {
                        subscriber.onError(e)
                    }
                })
            } catch (e: MqttException) {
                subscriber.onError(e)
            }
        }
    }

    fun receiveMessages(): ConnectableObservable<Pair<String, String>> {
        return Observable.create { subscriber ->
            mqttAndroidClient.setCallback(object : MqttCallback {
                override fun connectionLost(e: Throwable) {
                    subscriber.onError(e)
                }

                override fun messageArrived(topic: String, message: MqttMessage) {
                    try {
                        val data = String(message.payload, charset("UTF-8"))
                        subscriber.onNext(Pair(topic, data))
                    } catch (e: Exception) {
                        subscriber.onError(e)
                    }
                }

                override fun deliveryComplete(token: IMqttDeliveryToken) {
                    // Тут мы получаем подтверждение, что сообщение, отправленное
                    // методом publishMessages (следующий метод), доставлено
                }
            })
        }.publish()
    }

    fun publishMessages(topic: String, data: String): Completable {
        return Completable.create { subscriber ->
            val encodedPayload: ByteArray
            try {
                encodedPayload = data.toByteArray(charset("UTF-8"))
                val message = MqttMessage(encodedPayload)
                message.qos = QOS
                message.isRetained = false
                mqttAndroidClient.publish(topic, message)
                subscriber.onComplete()
            } catch (e: Exception) {
                subscriber.onError(e)
            } catch (e: MqttException) {
                subscriber.onError(e)
            }
        }
    }

    fun disconnect() {
        mqttAndroidClient.disconnect()
    }
}
