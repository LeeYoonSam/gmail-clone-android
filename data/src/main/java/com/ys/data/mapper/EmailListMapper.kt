package com.ys.data.mapper

import com.ys.core.functional.orDefault
import com.ys.core.mapper.ResultMapper
import com.ys.data.dto.emaillist.EmailListItemDto
import com.ys.domain.model.emaillist.EmailListItemModel
import javax.inject.Inject

class EmailListMapper @Inject constructor(): ResultMapper<List<EmailListItemDto>, List<EmailListItemModel>> {
    override fun map(input: List<EmailListItemDto>): List<EmailListItemModel> = input.filter {
        it.id != null && it.payload.from != null
    }.map {
        it.toModel()
    }.sortedByDescending {
        it.date
    }

    private fun EmailListItemDto.toModel() = EmailListItemModel(
        id = id!!,
        from = payload.from!!,
        subject = payload.subject.orEmpty(),
        profileImage = payload.profileImage,
        snippet = snippet.orEmpty(),
        date = payload.date.orEmpty(),
        isImportant = isImportant.orDefault(),
        isStarred = isImportant.orDefault(),
        isPromotional = isPromotional.orDefault()
    )
}