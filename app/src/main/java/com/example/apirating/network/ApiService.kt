package com.example.apirating.network

import com.example.apirating.model.cursos
import com.example.apirating.model.response.CursoResponse
import com.example.apirating.model.users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("cursos")
    fun getCursos(): Call<List<CursoResponse>> // Ajusta el modelo 'Curso' a tus datos reales

    @GET("users")
    fun getUsers(): Call<List<users>> // Ajusta el modelo 'User' a tus datos reales
}
