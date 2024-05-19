package com.example.news.api

import com.example.news.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

        private val retrofit by lazy {
            // 創建 HttpLoggingInterceptor 實例，用於日誌攔截器
            val logging = HttpLoggingInterceptor()
            // 設置日誌級別為 BODY，這樣可以在日誌中看到請求和響應的完整內容
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            // 創建 OkHttpClient 實例，並將日誌攔截器添加到客戶端中
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            // 創建並配置 Retrofit 實例
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // 用於將 JSON 轉換為對象
                .client(client)// 設置 OkHttp 客戶端
                .build()
        }
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}