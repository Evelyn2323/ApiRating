package com.example.apirating

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apirating.client.ApiClient
import com.example.apirating.databinding.ActivityDashboardBinding
import com.example.apirating.model.cursos
import com.example.apirating.model.response.CursoResponse
import com.example.apirating.model.users
import com.example.apirating.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardActivity : AppCompatActivity() {
    private lateinit var listViewCourses: ListView
    private lateinit var listViewUsers: ListView

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listViewCourses = findViewById(R.id.listViewCourses)
        listViewUsers = findViewById(R.id.listViewUsers)

        // Configuración de Retrofit
        /*val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.80.36:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Realizar la llamada a la API de cursos
        apiService.getCursos().enqueue(object : Callback<List<cursos>> {
            override fun onResponse(call: Call<List<cursos>>, response: Response<List<cursos>>) {
                if (response.isSuccessful) {
                    val courses = response.body() ?: emptyList()
                    val coursesAdapter = ArrayAdapter(this@DashboardActivity, android.R.layout.simple_list_item_1, courses.map { it.nombre })
                    listViewCourses.adapter = coursesAdapter
                } else {
                    Toast.makeText(this@DashboardActivity, "Error al cargar cursos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<cursos>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@DashboardActivity, "Error al cargar cursos", Toast.LENGTH_SHORT).show()
            }
        })






        // Realizar la llamada a la API de usuarios
        apiService.getUsers().enqueue(object : Callback<List<users>> {
            override fun onResponse(call: Call<List<users>>, response: Response<List<users>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    val usersAdapter = ArrayAdapter(this@DashboardActivity, android.R.layout.simple_list_item_1, users.map { it.name })
                    listViewUsers.adapter = usersAdapter
                } else {
                    Toast.makeText(this@DashboardActivity, "Error al cargar usuarios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<users>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@DashboardActivity, "Error al cargar usuarios", Toast.LENGTH_SHORT).show()
            }
        })*/


        getCursos()

    } // fin oncreate


    private fun getCursos() {
        val apiService = ApiClient.getApiService()

        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val response = apiService.getCursos().execute()

                    if (response.isSuccessful) {
                        val cursos = response.body()
                        cursos?.let {
                            Log.e("CURSOS", "$cursos")
                        }
                    } else {
                        // Handle error
                        Log.e("API Error", response.message())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }




}