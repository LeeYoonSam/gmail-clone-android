package com.ys.presentation.emaildetails

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.domain.model.emaildetails.SenderInfoModel
import com.ys.presentation.emaildetails.mvi.EmailDetailsContract
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testErrorState() {
        val state = EmailDetailsContract.EmailDetailsState(
            details = null,
            isLoading = false,
            isError = true
        )

        composeTestRule.setContent {
            EmailDetailsScreen(
                from = "from",
                profileImage = "profileImage",
                subject = "Test Subject",
                isPromotional = false,
                state = state,
            )
        }

        composeTestRule.onNodeWithText("Something went wrong").assertExists()
    }

    @Test
    fun testLoadingState() {
        val state = EmailDetailsContract.EmailDetailsState(
            details = null,
            isLoading = true,
            isError = false
        )

        composeTestRule.setContent {
            EmailDetailsScreen(
                from = "from",
                profileImage = "profileImage",
                subject = "Test Subject",
                isPromotional = false,
                state = state,
            )
        }

        composeTestRule.onNodeWithContentDescription("Loading").assertExists()
    }

    @Test
    fun testSuccessWithNonPromotionalState() {
        val state = EmailDetailsContract.EmailDetailsState(
            details = EmailDetailsModel(
                from = SenderInfoModel("test sender", ""),
                subject = "subject",
                date = "date",
                isStarred = false,
                isImportant = false,
                isPromotional = true,
                htmlBody = "htmlBody",
                cc = emptyList(),
                bcc = emptyList(),
                id = "id",
                to = emptyList(),
                labels = emptyList(),
                fileInfo = emptyList()
            ),
            isLoading = false,
            isError = false
        )

        composeTestRule.setContent {
            EmailDetailsScreen(
                from = "from",
                profileImage = "profileImage",
                subject = "Test Subject",
                isPromotional = false,
                state = state,
            )
        }

        with(composeTestRule) {
            onNodeWithText("from").assertExists()
            onNodeWithContentDescription("Star").assertExists()
            onNodeWithContentDescription("React with emoji").assertExists()
            onNodeWithText("Unsubscribe").assertDoesNotExist()
        }
    }

    @Test
    fun testSuccessWithPromotionalState() {
        val state = EmailDetailsContract.EmailDetailsState(
            details = EmailDetailsModel(
                from = SenderInfoModel("test sender", ""),
                subject = "subject",
                date = "date",
                isStarred = false,
                isImportant = false,
                isPromotional = true,
                htmlBody = "htmlBody",
                cc = emptyList(),
                bcc = emptyList(),
                id = "id",
                to = emptyList(),
                labels = emptyList(),
                fileInfo = emptyList()
            ),
            isLoading = false,
            isError = false
        )

        composeTestRule.setContent {
            EmailDetailsScreen(
                from = "from",
                profileImage = "profileImage",
                subject = "Test Subject",
                isPromotional = true,
                state = state,
            )
        }

        with(composeTestRule) {
            onNodeWithText("from").assertExists()
            onNodeWithContentDescription("Star").assertExists()
            onNodeWithContentDescription("React with emoji").assertDoesNotExist()
            onNodeWithText("Unsubscribe").assertExists()
        }
    }
}