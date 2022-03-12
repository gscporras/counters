package com.counter.android.network.compose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.counter.android.R

class NetworkUrlPreviewProvider : PreviewParameterProvider<Int> {
    override val count: Int
        get() = super.count
    override val values: Sequence<Int>
        get() = sequenceOf(R.drawable.arrow_balloon_library_skydoves)
}