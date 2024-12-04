package com.ys.presentation.emaildetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.domain.model.emaildetails.RecipientModel
import com.ys.domain.model.emaildetails.SenderInfoModel
import com.ys.presentation.emaildetails.mvi.EmailDetailsContract

class EmailDetailsScreenTest {

    @Preview
    @Composable
    fun EmailDetailsLoadingScreenTest() {
        EmailDetailsScreen(
            from = "from",
            profileImage = "profileImage",
            subject = "subject",
            isPromotional = true,
            state = EmailDetailsContract.EmailDetailsState()
        )
    }

    @Preview
    @Composable
    fun EmailDetailsErrorScreenTest() {
        EmailDetailsScreen(
            from = "from",
            profileImage = "profileImage",
            subject = "subject",
            isPromotional = true,
            state = EmailDetailsContract.EmailDetailsState(isLoading = false, isError = true)
        )
    }

    @Preview
    @Composable
    fun EmailDetailsSuccessScreenTest() {
        EmailDetailsScreen(
            from = "from",
            profileImage = "profileImage",
            subject = "subject",
            isPromotional = true,
            state = EmailDetailsContract.EmailDetailsState(isLoading = false, isError = false, details = EmailDetailsModel(
                id = "id",
                from = SenderInfoModel(
                    name = "from name",
                    email = "from@email.com",
                    profileImage = "profileImage"
                ),
                to = listOf(
                    RecipientModel(
                        name = "test name to",
                        email = "to@email.com"
                    )
                ),
                cc = listOf(
                    RecipientModel(
                        name = "test name cc",
                        email = "cc@email.com"
                    )
                ),
                bcc = listOf(
                    RecipientModel(
                        name = "test name bcc",
                        email = "bcc@email.com"
                    )
                ),
                subject = "subject",
                htmlBody = "htmlBody",
                date = "date",
                isImportant = true,
                isStarred = true,
                isPromotional = true,
                fileInfo = emptyList(),
                labels = listOf("labels")
            ))
        )
    }
}