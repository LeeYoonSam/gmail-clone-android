package com.ys.presentation.emaildetails.mvi

import app.cash.turbine.test
import com.ys.core.error.Failure
import com.ys.core.functional.Either
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.domain.usecase.EmailDetailsUseCase
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class EmailDetailsViewModelTest : DescribeSpec({
    val emailDetailsUseCase: EmailDetailsUseCase = mockk()
    val viewModel = EmailDetailsViewModel(emailDetailsUseCase)

    val testDispatcher = StandardTestDispatcher()

    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    afterTest {
        Dispatchers.resetMain()
    }

    describe("EmailDetailsViewModel") {
        it("should update state to Success when use case returns data") {
            val emailDetails = mockk<EmailDetailsModel>()
            coEvery { emailDetailsUseCase() } returns Either.Right(emailDetails)

            runTest {
                viewModel.state.test {
                    viewModel.event(EmailDetailsContract.EmailDetailsEvent.LoadEmailDetails)

                    awaitItem() shouldBe EmailDetailsContract.EmailDetailsState(isLoading = true)
                    awaitItem() shouldBe EmailDetailsContract.EmailDetailsState(
                        details = emailDetails,
                        isLoading = false
                    )
                }
            }
        }

        it("should update state to Error when use case returns failure") {
            val failure = Failure.ServerError(500, "Server Error")
            coEvery { emailDetailsUseCase() } returns Either.Left(failure)

            runTest {
                viewModel.state.test {
                    viewModel.event(EmailDetailsContract.EmailDetailsEvent.LoadEmailDetails)

                    awaitItem() shouldBe EmailDetailsContract.EmailDetailsState(isLoading = true)
                    awaitItem() shouldBe EmailDetailsContract.EmailDetailsState(
                        details = null,
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }
})