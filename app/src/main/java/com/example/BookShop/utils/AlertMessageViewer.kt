package com.example.BookShop.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly

object AlertMessageViewer {

    fun showAlertDialogMessage(context: Context, message: String){

        var newMessage = message
        if(message.isDigitsOnly()){
            newMessage = context.getString(message.toInt())
        }

        AlertDialog.Builder(context)
            .setMessage(newMessage)
            .setCancelable(false)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.cancel()
            }
            .show()

    }

}