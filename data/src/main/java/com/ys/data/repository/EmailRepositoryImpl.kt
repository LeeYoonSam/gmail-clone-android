package com.ys.data.repository

import com.ys.core.error.Failure
import com.ys.core.functional.Either
import com.ys.domain.model.emaildetails.EmailDetailsModel
import com.ys.domain.model.emaillist.EmailListItemModel
import com.ys.domain.repository.EmailRepository

class EmailRepositoryImpl: EmailRepository {
    override suspend fun getEmailList(): Either<Failure, List<EmailListItemModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmailDetails(): Either<Failure, EmailDetailsModel> {
        TODO("Not yet implemented")
    }
}