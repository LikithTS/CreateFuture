package com.createfuture.takehome.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.createfuture.takehome.R
import androidx.compose.ui.platform.LocalContext
import com.createfuture.takehome.domain.util.NetworkApiErrors

@Composable
fun HandleErrorView(networkError: NetworkApiErrors) {

    val context = LocalContext.current

    Box {
        when (networkError) {
            NetworkApiErrors.NOT_FOUND -> {
                Toast.makeText(context, context.getText(R.string.api_not_found), Toast.LENGTH_SHORT)
                    .show()
            }

            NetworkApiErrors.UN_AUTHORISED -> {
                //You can handle in 2 different ways.
                //1. If you have refresh token, then do a silent api call to get new token and do not show this error.
                //2. If you don't have refresh token, then show error message and move to login page.
                Toast.makeText(context, context.getText(R.string.un_authorised), Toast.LENGTH_SHORT)
                    .show()

            }

            NetworkApiErrors.FAILURE -> {
                Toast.makeText(context, context.getText(R.string.failure), Toast.LENGTH_SHORT)
                    .show()
            }

            is NetworkApiErrors.GenericException -> {
                Toast.makeText(context, networkError.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}