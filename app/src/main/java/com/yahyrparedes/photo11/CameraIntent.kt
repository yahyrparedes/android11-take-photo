package com.yahyrparedes.photo11

import android.content.Context
import android.content.Intent


private val CAMERA_CANDIDATES = listOf(
    "net.sourceforge.opencamera"
)

fun enhanceCameraIntent(
    context: Context,
    baseIntent: Intent,
    title: String
): Intent {
    val pm = context.packageManager

    val cameraIntents =
        CAMERA_CANDIDATES.map { Intent(baseIntent).setPackage(it) }
            .filter { pm.queryIntentActivities(it, 0).isNotEmpty() }
            .toTypedArray()

    return if (cameraIntents.isEmpty()) {
        baseIntent
    } else {
        Intent
            .createChooser(baseIntent, title)
            .putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents)
    }
}
