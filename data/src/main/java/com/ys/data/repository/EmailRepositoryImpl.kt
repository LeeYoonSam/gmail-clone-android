package com.ys.data.repository

import com.ys.core.error.Failure
import com.ys.core.functional.Either
import com.ys.data.mapper.EmailDetailsMapper
import com.ys.data.mapper.EmailListMapper
import com.ys.data.remote.api.ApiService
import com.ys.data.remote.handler.safeApiCall
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.domain.model.emaillist.EmailListItemModel
import com.ys.domain.repository.EmailRepository
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val emailListMapper: EmailListMapper,
    private val emailDetailsMapper: EmailDetailsMapper
): EmailRepository {
    override suspend fun getEmailList(): Either<Failure, List<EmailListItemModel>> = safeApiCall(
        apiCall = { apiService.getEmailList() },
        mapper = { emailListMapper.map(it) }
    )

    override suspend fun getEmailDetails(): Either<Failure, EmailDetailsModel> = safeApiCall(
        apiCall = { apiService.getEmailDetail() },
        mapper = { emailDetailsMapper.map(it) }
    )
}

//fun main() {
//    runBlocking {
//        // Create mock instances
//        val apiService = MockApiService()
//        val emailListMapper = EmailListMapper()
//        val emailDetailsMapper = EmailDetailsMapper()
//
//        // Initialize EmailRepositoryImpl with mock dependencies
//        val emailRepository = EmailRepositoryImpl(apiService, emailListMapper, emailDetailsMapper)
//
//        // Test getEmailList
//        val emailListResult = emailRepository.getEmailList()
//        println("Email List Result: $emailListResult")
//
//        // Test getEmailDetails
//        val emailDetailsResult = emailRepository.getEmailDetails()
//        println("Email Details Result: $emailDetailsResult")
//    }
//}
//
//class MockApiService : ApiService {
//    override suspend fun getEmailList(): Response<List<EmailListItemDto>> {
//        val mockEmailList = arrayListOf(
//            EmailListItemDto(
//                hasAttachments = false,
//                id = "1",
//                isImportant = true,
//                isPromotional = false,
//                payload = Payload("", "", "Coroutine lab", "", ""),
//                snippet = "Mock snippet",
//                subject = "Mock subject",
//                threadId = "thread-1",
//                timestamp = 1
//            )
//        )
//        return Response.success(mockEmailList)
//    }
//
//    override suspend fun getEmailDetail(): Response<EmailDetailsDto> {
//        val mockEmailDetails = EmailDetailsDto(
//            id = "1",
//            body = Body("", ""),
//            timestamp = 2,
//            isPromotional = false,
//            hasAttachments = false,
//            isImportant = false,
//            labels = emptyList(),
//            snippet = "",
//            threadId = "",
//            payload = com.ys.data.dto.emaildetails.Payload(
//                date = "",
//                attachments = emptyList(),
//                subject = "",
//                cc = emptyList(),
//                to = emptyList(),
//                bcc = emptyList(),
//                senderInfo = SenderInfo(
//                    profileImage = "",
//                    email = "coroutinelab@test.com",
//                    name = "coroutinelab"
//                )
//            )
//        )
//
//        return Response.success(mockEmailDetails)
//    }
//}