package com.singularitycoder.androiddialogs1

import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog

// https://newbedev.com/align-alertdialog-buttons-to-center
fun AlertDialog.withCenteredButtons(): AlertDialog {
    fun disableSpacer(button: Button?) {
        (button?.parent as? LinearLayout).apply {
            this?.gravity = Gravity.CENTER_HORIZONTAL
            this?.getChildAt(1)?.visibility = View.GONE
        }
    }

    fun setDialogButton(btnType: Int) {
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            weight = 1f
            gravity = Gravity.CENTER    // Center buttons
        }
        this.getButton(btnType).apply {
            disableSpacer(button = this)
            this?.layoutParams = layoutParams
        }
    }

    setDialogButton(btnType = AlertDialog.BUTTON_POSITIVE)
    setDialogButton(btnType = AlertDialog.BUTTON_NEGATIVE)
    setDialogButton(btnType = AlertDialog.BUTTON_NEUTRAL)

    return this
}