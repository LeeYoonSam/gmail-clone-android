package com.ys.domain.usecase

import com.ys.core.error.Failure
import com.ys.core.functional.Either
import com.ys.domain.model.emaillist.EmailListItemModel
import com.ys.domain.repository.EmailRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException

class EmailListUseCaseTest {

    private val repository: EmailRepository = mockk()
    private lateinit var useCase: EmailListUseCase

    @BeforeEach
    fun setUp() {
        useCase = EmailListUseCase(repository)
    }

    @Test
    fun `GIVEN ioexception occurs WHEN use case is invoked THEN NetworkError returned`() = runTest {
        val exception = IOException("Network Error")

        coEvery { repository.getEmailList() } returns Either.Left(Failure.NetworkError(exception))

        assert(useCase.invoke() == Either.Left(Failure.NetworkError(exception)))
    }

    @Test
    fun `GIVEN HttpException occurs WHEN use case is invoked THEN ServerError returned`() = runTest {
        coEvery { repository.getEmailList() } returns Either.Left(Failure.ServerError(200, "server"))

        assert(useCase.invoke() == Either.Left(Failure.ServerError(200, "server")))
    }

    @Test
    fun `GIVEN  WHEN use case is invoked THEN list of EmailListItemModel returned`() = runTest {
        val emailList = mockk<EmailListItemModel>()

        coEvery { repository.getEmailList() } returns Either.Right(listOf(emailList))

        assert(useCase.invoke() == Either.Right(listOf(emailList)))
    }
}