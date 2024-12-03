package com.ys.presentation.emaillist

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ys.core.error.Failure
import com.ys.domain.model.emaillist.EmailListItemModel
import com.ys.presentation.emaillist.mvi.EmailListContract

class EmailListScreenTest {

    @Preview
    @Composable
    fun EmailListLoadingScreenPreview() {
        EmailListScreen(
            state = EmailListContract.EmailListState.Loading,
            effect = null,
            dispatch = {},
            onItemClick = {}
        )
    }

    @Preview
    @Composable
    fun EmailListErrorScreenPreview() {
        EmailListScreen(
            state = EmailListContract.EmailListState.Error(
                Failure.ServerError(500, "Error")
            ),
            effect = null,
            dispatch = {},
            onItemClick = {}
        )
    }

    @Preview
    @Composable
    fun EmailListScreenPreview() {
        EmailListScreen(
            state = EmailListContract.EmailListState.Success(
                listOf(
                    EmailListItemModel(
                        id = "2",
                        from = "test@example.com",
                        subject = "Test Subject 2",
                        snippet = "Test Snippet",
                        isStarred = false,
                        date = "o",
                        profileImage = "",
                        isPromotional = false,
                        isImportant = true
                    )
                )
            ),
            effect = null,
            dispatch = {},
            onItemClick = {}
        )
    }
}