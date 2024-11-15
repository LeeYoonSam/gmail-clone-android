package com.ys.presentation.emaildetails

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.presentation.emaildetails.mvi.EmailDetailsViewModel

@Composable
fun EmailDetailsScreen(
    viewModel: EmailDetailsViewModel = hiltViewModel(),
    from: String,
    profileImage: String?,
    subject: String,
    isPromotional: Boolean
) {

}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun EmailDetailsUi(
    from: String,
    profileImage: String?,
    subject: String,
    isPromotional: Boolean,
    model: EmailDetailsModel
) {

}