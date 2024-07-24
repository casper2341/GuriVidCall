package com.guri.gurividcall.videos

sealed interface VideoCallAction {
    data object onDisconnect: VideoCallAction
    data object JoinCallClick: VideoCallAction
}