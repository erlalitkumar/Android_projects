package com.lkb.baseandroidproject

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

import android.widget.Toast


open class WebAppInterface internal constructor(c: Context) {
    var mContext: Context = c

    /**
     * Show Toast Message
     * @param toast
     */
    fun showToast(toast: String?) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    /**
     * Show Dialog
     * @param dialogMsg
     */
    fun showDialog(dialogMsg: String?) {
        val alertDialog: AlertDialog = AlertDialog.Builder(mContext).create()

        // Setting Dialog Title
        alertDialog.setTitle("JS triggered Dialog")

        // Setting Dialog Message
        alertDialog.setMessage(dialogMsg)

        // Setting alert dialog icon
        //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(
                    mContext,
                    "Dialog dismissed!",
                    Toast.LENGTH_SHORT
                ).show()
            })

        // Showing Alert Message
        alertDialog.show()
    }

    /**
     * Intent - Move to next screen
     */
    fun moveToNextScreen() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(mContext)
        // Setting Dialog Title
        alertDialog.setTitle("Alert")
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to leave to next screen?")
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
            DialogInterface.OnClickListener { dialog, which ->
                //Move to Next screen
            })
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
            DialogInterface.OnClickListener { dialog, which -> // Cancel Dialog
                dialog.cancel()
            })
        // Showing Alert Message
        alertDialog.show()
    }

}