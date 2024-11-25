package com.ys.domain.usecase

import com.ys.core.error.Failure
import com.ys.core.functional.Either
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.domain.repository.EmailRepository
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException

/**
 * Kotest 적용
 *
 * 테스트 하기 위해 kotest Plugin 설치 필요
 */
class EmailDetailsUseCaseTest: ShouldSpec({
    val repository: EmailRepository = mockk()
    val useCase = EmailDetailsUseCase(repository)

    should("return NetworkError for IOException") {
        val exception = IOException("Network Error")
        coEvery { repository.getEmailDetails() } returns Either.Left(Failure.NetworkError(exception))

        val result = useCase.invoke()

        result shouldBe Either.Left(Failure.NetworkError(exception))
    }

    should("return ServerError for HttpException") {
        coEvery { repository.getEmailDetails() } returns Either.Left(Failure.ServerError(200, "server"))

        val result = useCase.invoke()

        result shouldBe Either.Left(Failure.ServerError(200, "server"))
    }

    should("return email details on success") {
        val emailDetail = mockk<EmailDetailsModel>()
        coEvery { repository.getEmailDetails() } returns Either.Right(emailDetail)

        val result = useCase.invoke()

        result shouldBe Either.Right(emailDetail)
    }
})