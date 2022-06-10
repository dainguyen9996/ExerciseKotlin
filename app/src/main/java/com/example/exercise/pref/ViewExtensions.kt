package com.example.exercise.pref

import android.app.Activity
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Patterns
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.exercise.MyApplication

/**
 * @author HungHN on 3/15/2019.
 */

val AppContext = MyApplication.get()

fun ViewGroup.inflate(@LayoutRes resource: Int): View =
    LayoutInflater.from(context).inflate(resource, this, false)

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, duration).show()

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()


fun CharSequence.validateEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun CharSequence.validatePassword(): Boolean {
    return this.length >= 6
}

fun Context.getColorRes(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun String.toSpan(
    start: Int = 0,
    end: Int = -1,
    styleSpan: StyleSpan? = null,
    foregroundColorSpan: ForegroundColorSpan? = null,
    hasUnderline: Boolean = false,
    relativeSizeSpan: RelativeSizeSpan? = null
): Spannable {
    val span = SpannableString(this)
    if (isEmpty()) return span
    span.setSpan(
        styleSpan,
        start,
        if (end > 0) end else this.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    span.setSpan(
        foregroundColorSpan,
        start,
        if (end > 0) end else this.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    span.setSpan(
        relativeSizeSpan,
        start,
        if (end > 0) end else this.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    if (hasUnderline) {
        span.setSpan(UnderlineSpan(), start, this.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    return span
}
