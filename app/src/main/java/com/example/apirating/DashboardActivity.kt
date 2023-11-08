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

    private fun getUsers() {
        val apiService = ApiClient.getApiService()

        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val response = apiService.getUsers().execute()

                    if (response.isSuccessful) {
                        val users = response.body()
                        users?.let {
                            Log.e("USUARIOS", "$users")
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