package com.mobitant.threebuttonfragmentmodule.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

class SecondFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder =  AlertDialog.Builder(activity)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("DialogFragment")
        .setMessage("DialogFragment 내용이 잘 안보이지요?")
        .setPositiveButton("OK", null)
        return builder.create()
    }
}