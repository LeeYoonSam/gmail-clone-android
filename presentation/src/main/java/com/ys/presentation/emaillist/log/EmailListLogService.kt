package com.ys.presentation.emaillist.log

import android.util.Log
import com.ys.log.AbsLogService
import com.ys.presentation.emaillist.mvi.EmailListContract
import javax.inject.Inject

class EmailListLogService @Inject constructor() : AbsLogService<EmailListContract.EmailListEvent>() {

    override fun logClick(event: EmailListContract.EmailListEvent) {
        when (event) {
            is EmailListContract.EmailListEvent.EmailClicked -> {
                logEmailClicked(event.model.id)
            }

            EmailListContract.EmailListEvent.LoadEmailList -> {

            }
        }
    }

    private fun logEmailClicked(id: String) {
        // Send log
        Log.d("logEmailClicked","Click log Test id: $id")
    }
}