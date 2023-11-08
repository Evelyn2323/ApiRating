package com.example.apirating

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apirating.model.datosApi
import com.example.apirating.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardActivity : AppCompatActivity() {
    private lateinit var listViewCourses: ListView
    private lateinit var listViewUsers: ListView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        listViewCourses = findViewById(R.id.listViewCourses)
        listViewUsers = findViewById(R.id.listViewUsers)

        // Configuración de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.80.36:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Realizar la llamada a la API de cursos
        apiService.getCursos().enqueue(object : Callback<List<datosApi.cursos>> {
            override fun onResponse(call: Call<List<datosApi.cursos>>, response: Response<List<datosApi.cursos>>) {
                if (response.isSuccessful) {
                    val courses = response.body() ?: emptyList()
                    val coursesAdapter = ArrayAdapter(this@DashboardActivity, android.R.layout.simple_list_item_1, courses.map { it.nombre })
                    listViewCourses.adapter = coursesAdapter
                } else {
                    Toast.makeText(this@DashboardActivity, "Error al cargar cursos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<datosApi.cursos>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@DashboardActivity, "Error al cargar cursos", Toast.LENGTH_SHORT).show()
            }
        })

        // Realizar la llamada a la API de usuarios
        apiService.getUsers().enqueue(object : Callback<List<datosApi.users>> {
            override fun onResponse(call: Call<List<datosApi.users>>, response: Response<List<datosApi.users>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    val usersAdapter = ArrayAdapter(this@DashboardActivity, android.R.layout.simple_list_item_2, users.map { it.name })
                    listViewUsers.adapter = usersAdapter
                } else {
                    Toast.makeText(this@DashboardActivity, "Error al cargar usuarios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<datosApi.users>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@DashboardActivity, "Error al cargar usuarios", Toast.LENGTH_SHORT).show()
            }
        })
    }
}