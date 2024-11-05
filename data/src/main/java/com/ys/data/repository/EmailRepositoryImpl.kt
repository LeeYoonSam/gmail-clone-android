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