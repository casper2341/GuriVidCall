package com.guri.gurividcall

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.guri.gurividcall.firebase.FirebaseConfig
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GuriVidCallApplication : Application() {
    private var currentName: String? = null
    var client: StreamVideo? = null

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        with(FirebaseConfig.getInstance()) {
            fetchRemoteConfigs()
        }
        startKoin {
            androidContext(this@GuriVidCallApplication)
            modules(appModule)
        }
    }

    fun initVideoClient(userName: String) {
        if (client == null || userName != currentName) {
            StreamVideo.removeClient()
            currentName = userName

            client = StreamVideoBuilder(
                context = this,
                apiKey = "",
                user = User(
                    id = userName,
                    name = userName,
                    type = UserType.Guest
                )
            ).build()
        }
    }
}