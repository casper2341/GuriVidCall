package com.guri.gurividcall.videos

import io.getstream.video.android.core.Call

data class VCState(
    val call: Call,
    val callState: CallState? = null,
    val error: String? = null
)

enum class CallState {
    JOINING,
    ACTIVE,
    ENDED
}