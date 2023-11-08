package com.example.apirating.model.response

data class CursoResponse(
    val data: List<Course>
)

data class Course(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val created_at: String,
    val updated_at: String
)

