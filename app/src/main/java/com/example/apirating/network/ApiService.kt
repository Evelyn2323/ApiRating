package com.example.apirating.network

import com.example.apirating.model.datosApi
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("cursos")
    fun getCursos(): Call<List<datosApi.cursos>> // Ajusta el modelo 'Curso' a tus datos reales

    @GET("users")
    fun getUsers(): Call<List<datosApi.users>> // Ajusta el modelo 'User' a tus datos reales
}
