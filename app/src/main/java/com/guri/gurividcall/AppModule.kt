package com.guri.gurividcall

import com.guri.gurividcall.videos.VideoCallViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factory {
       val app = androidContext().applicationContext as GuriVidCallApplication
       app.client
    }
    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)
}