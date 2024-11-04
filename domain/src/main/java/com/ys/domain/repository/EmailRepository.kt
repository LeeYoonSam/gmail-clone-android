package com.ys.domain.repository

import com.ys.core.error.Failure
import com.ys.core.functional.Either
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.domain.model.emaillist.EmailListItemModel

interface EmailRepository {
    suspend fun getEmailList(): Either<Failure, List<EmailListItemModel>>
    suspend fun getEmailDetails(): Either<Failure, EmailDetailsModel>
}