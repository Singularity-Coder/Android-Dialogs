package com.singularitycoder.androiddialogs1

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class CustomDialogFragment(
    val dialogIsCancelable: Boolean = false,
    val icon: Int? = null,
    val dialogView: View? = null,
    val layout: Int? = null,
    val title: String? = "NA",
    val message: String? = "NA",
    val listItemsArray: Array<String>? = null,
    val singleSelectArray: Array<String>? = null,
    val multiSelectArray: Array<String>? = null,
    val itemAction: ((selectedItem: String) -> Unit)? = null,
    val positiveBtnText: String? = "NA",
    val negativeBtnText: String? = "NA",
    val neutralBtnText: String? = "NA",
    val positiveAction: ((selectedList: List<Any>?) -> Unit)? = null,
    val negativeAction: (() -> Unit)? = null,
    val neutralAction: (() -> Unit)? = null
) : DialogFragment() {

    private lateinit var myContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val list = ArrayList<String>()
        val builder = AlertDialog.Builder(myContext).apply {
            setCancelable(dialogIsCancelable)
            if ("NA" != title) setTitle(title)
            if ("NA" != message) setMessage(message)
            if ("NA" != positiveBtnText) setPositiveButton(positiveBtnText) { dialog, id -> if (null == positiveAction) dialog.cancel() else positiveAction.invoke(list) }
            if ("NA" != negativeBtnText) setNegativeButton(negativeBtnText) { dialog, id -> if (null == negativeAction) dialog.cancel() else negativeAction.invoke() }
            if ("NA" != neutralBtnText) setNeutralButton(neutralBtnText) { dialog, id -> if (null == neutralAction) dialog.cancel() else neutralAction.invoke() }
            if (null != icon) setIcon(icon)
            if (null != dialogView) setView(dialogView)
            if (null != layout) setView(layout)
            if (null != listItemsArray) setItems(listItemsArray) { dialog, which ->
                itemAction?.invoke(listItemsArray[which])
            }
            if (null != singleSelectArray) setSingleChoiceItems(singleSelectArray, -1 /* default checked item in list */) { dialog, which ->
                list.clear()
                list.add(singleSelectArray[which])
            }
            if (null != multiSelectArray) setMultiChoiceItems(multiSelectArray, null) { dialog, which, isChecked ->
                if (isChecked) list.add(multiSelectArray[which])
                else list.remove(multiSelectArray[which])
            }
        }
        return builder.create()
    }
}