package com.ys.domain.usecase

import com.ys.domain.repository.EmailRepository
import javax.inject.Inject

class EmailListUseCase @Inject constructor(
    private val repository: EmailRepository
) {
    suspend operator fun invoke() = repository.getEmailList()
}