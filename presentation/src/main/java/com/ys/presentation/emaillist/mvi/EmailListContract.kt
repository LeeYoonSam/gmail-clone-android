package com.ys.presentation.emaillist.mvi

import com.ys.core.error.Failure
import com.ys.coreui.mvi.MVIContract
import com.ys.domain.model.emaillist.EmailListItemModel
import com.ys.log.BaseEvent

interface EmailListContract :
    MVIContract<EmailListContract.EmailListState, EmailListContract.EmailListEffect, EmailListContract.EmailListEvent> {

    sealed class EmailListEvent: BaseEvent {
        data object LoadEmailList : EmailListEvent()
        data class EmailClicked(val model: EmailListItemModel) : EmailListEvent()
    }

    sealed class EmailListState {
        data object Loading : EmailListState()
        data class Success(val emailList: List<EmailListItemModel>) : EmailListState()
        data class Error(val error: Failure) : EmailListState()
    }

    sealed class EmailListEffect {
        data class NavigateToEmailDetails(val model: EmailListItemModel) : EmailListEffect()
    }
}