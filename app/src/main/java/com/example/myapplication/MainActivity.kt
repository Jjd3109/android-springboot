package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.api.ApiService
import com.example.myapplication.dto.TestDto

import okhttp3.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonGet = findViewById<Button>(R.id.bt_get)
        val textViewResult = findViewById<TextView>(R.id.tv_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.21.192.1:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        buttonGet.setOnClickListener {
            val title = findViewById<EditText>(R.id.et_title).text.toString()
            val content = findViewById<EditText>(R.id.et_content).text.toString()
            val author = findViewById<EditText>(R.id.et_author).text.toString()

            val testDto = TestDto(title, content, author)

            apiService.findMember(testDto).enqueue(object : retrofit2.Callback<TestDto> {
                override fun onResponse(call: retrofit2.Call<TestDto>, response: Response<TestDto>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        textViewResult.text = "Title: ${result?.title}, Content: ${result?.content}, Author: ${result?.author}"

                    } else {
                        // 실패 처리
                    }
                }

                override fun onFailure(call: retrofit2.Call<TestDto>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

    }
}


