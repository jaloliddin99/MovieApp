package uz.promo.movieapp.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.O)
fun vibratePhone(context: Context, milliseconds: Long = 200L) {
    val vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)
    vibrator?.vibrate(
        VibrationEffect.createOneShot(
            milliseconds,
            VibrationEffect.DEFAULT_AMPLITUDE
        )
    )
}