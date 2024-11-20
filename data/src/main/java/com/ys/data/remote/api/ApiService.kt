package com.ys.data.remote.api

import com.ys.data.dto.emaildetails.EmailDetailsDto
import com.ys.data.dto.emaillist.EmailListItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/emaillist")
    suspend fun getEmailList(): Response<List<EmailListItemDto>>

    @GET("api/v1/emaildetails")
    suspend fun getEmailDetail(): Response<List<EmailDetailsDto>>
}