package com.example.myapplication.api

import com.example.myapplication.dto.TestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.lang.reflect.Member

interface ApiService {

    @POST("/v1/findMember")
    fun findMember(@Body testDto: TestDto): Call<TestDto>
}