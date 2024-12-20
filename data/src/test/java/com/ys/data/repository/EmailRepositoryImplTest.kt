package com.ys.data.repository

import com.ys.core.error.Failure
import com.ys.core.functional.Either
import com.ys.data.dto.emaildetails.EmailDetailsDto
import com.ys.data.dto.emaillist.EmailListItemDto
import com.ys.data.mapper.EmailDetailsMapper
import com.ys.data.mapper.EmailListMapper
import com.ys.data.remote.api.ApiService
import com.ys.domain.model.emaildetails.EmailDetailsModel
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import retrofit2.Response
import java.io.IOException

class EmailRepositoryImplTest : FunSpec() {
    private val apiService: ApiService = mockk()
    private val emailListMapper: EmailListMapper = mockk()
    private val emailDetailsMapper: EmailDetailsMapper = mockk()

    private val emailRepository = EmailRepositoryImpl(apiService, emailListMapper, emailDetailsMapper)

    init {
        test("getEmailList should return NetworkError for IOException") {
            val exp = IOException("Network Error")
            coEvery { apiService.getEmailList() } throws exp

            val result = emailRepository.getEmailList()
            result shouldBe Either.Left(Failure.NetworkError(exp))
        }

        test("getEmailList should return mapped data on success") {
            val mockResponse = Response.success(listOf<EmailListItemDto>())
            coEvery { apiService.getEmailList() } returns mockResponse
            coEvery { emailListMapper.map(any()) } returns listOf()

            val result = emailRepository.getEmailList()
            result shouldBe Either.Right(listOf())
        }

        test("getEmailDetails should return NetworkError for IOException") {
            val exp = IOException("Network Error")
            coEvery { apiService.getEmailDetail() } throws exp

            val result = emailRepository.getEmailDetails()
            result shouldBe Either.Left(Failure.NetworkError(exp))
        }

        test("getEmailDetails should return mapped data on success") {
            val mockResponse = Response.success(listOf<EmailDetailsDto>())
            val mockKEmailDetailsModel = mockk<EmailDetailsModel>()
            coEvery { apiService.getEmailDetail() } returns mockResponse
            coEvery { emailDetailsMapper.map(any()) } returns mockKEmailDetailsModel

            val result = emailRepository.getEmailDetails()
            result shouldBe Either.Right(mockKEmailDetailsModel)
        }
    }
}