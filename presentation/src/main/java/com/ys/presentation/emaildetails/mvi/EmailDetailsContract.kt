package com.ys.presentation.emaildetails.mvi

import com.ys.coreui.mvi.MVIContract
import com.ys.domain.model.emaildetails.EmailDetailsModel

interface EmailDetailsContract : MVIContract<
        EmailDetailsContract.EmailDetailsState,
        EmailDetailsContract.EmailDetailsEffect,
        EmailDetailsContract.EmailDetailsEvent
        > {
    data class EmailDetailsState(
        val details: EmailDetailsModel? = null,
        val isLoading: Boolean = true,
        val isError: Boolean = false
    )

    sealed class EmailDetailsEffect {
        data object NavigateToDraftEmail : EmailDetailsEffect()
    }

    sealed class EmailDetailsEvent {
        data object LoadEmailDetails : EmailDetailsEvent()
        data object DeleteEmail : EmailDetailsEvent()
        data object ArchiveEmail : EmailDetailsEvent()
        data object NewEmail : EmailDetailsEvent()
        data object OnTopAppBarEmailMenu : EmailDetailsEvent()
        data object OnMarkAsImportant : EmailDetailsEvent()
        data object OnSendEmojiReply : EmailDetailsEvent()
        data object OnSendReply : EmailDetailsEvent()
        data object OnEmailMenu : EmailDetailsEvent()
        data object OnAttachFile : EmailDetailsEvent()
        data object OnReplyDropDown : EmailDetailsEvent()
        data object OnBottomBarEmojiReply : EmailDetailsEvent()
        data object OnBottomBarReply : EmailDetailsEvent()
    }
}