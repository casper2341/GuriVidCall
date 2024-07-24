package com.guri.gurividcall.firebase

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.guri.gurividcall.R

class FirebaseConfig private constructor(private var firebaseRemoteConfig: FirebaseRemoteConfig) {

    fun fetchRemoteConfigs() {
        firebaseRemoteConfig.fetch(0)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseRemoteConfig.fetchAndActivate()
                }
            }
    }

    fun getBoolean(key: String): Boolean {
        return firebaseRemoteConfig.getBoolean(key)
    }

    fun getString(key: String): String {
        return firebaseRemoteConfig.getString(key)
    }

//    fun <T> getObject(key: String, clazz: Class<T>): T {
//        val jsonString = firebaseRemoteConfig.getString(key)
//        return if (jsonString.isNotBlank()) {
//            //Gson().fromJson(jsonString, clazz)
//        } else {
//           // null
//        }
//    }

    companion object {

        lateinit var remoteConfig: FirebaseConfig
        fun getInstance(): FirebaseConfig {
            if (!this::remoteConfig.isInitialized) {
                val firebaseRemoteConfig = Firebase.remoteConfig.apply {
                    setConfigSettingsAsync(
                        remoteConfigSettings {
                            minimumFetchIntervalInSeconds = 0
                        }.toBuilder().build()
                    )
                    setDefaultsAsync(R.xml.remote_config_defaults)
                }
                remoteConfig = FirebaseConfig(firebaseRemoteConfig).also {
                    it.fetchRemoteConfigs()
                }
            }
            return remoteConfig
        }
    }
}