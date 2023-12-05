package com.dgopadakak.smarthousev5

import android.content.Context
import com.dgopadakak.smarthousev5.model.Model
import com.dgopadakak.smarthousev5.model.MqttHelper
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

@Module
class AppModule {
    @Provides
    fun provideModel(mqttHelper: MqttHelper): Model {
        return Model(mqttHelper)
    }

    @Provides
    fun provideMqttHelper(context: Context): MqttHelper {
        return MqttHelper(context)
    }
}
