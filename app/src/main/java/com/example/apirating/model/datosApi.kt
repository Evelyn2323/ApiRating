package com.example.apirating.model

class datosApi{

    data class cursos(val id: Int, val nombre: String,val descripcion:String, val created_at: String, val updated_at: String) // Ajusta según la estructura de tus datos de cursos

    data class users(val idPersona: Int, val name: String,val email:String, val email_verified_at: String, val Avatar: String, val created_at: String, val updated_at: String) // Ajusta según la estructura de tus datos de cursos
}
