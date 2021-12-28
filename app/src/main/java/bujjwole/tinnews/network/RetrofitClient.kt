package bujjwole.tinnews.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private val API_KEY: String = "YOUR_API_KEY"
        private val BASE_URL = "https://newsapi.org/v2/"

        fun newInstance(context: Context): Retrofit{
            val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
        }
    }

    private class HeaderInterceptor: Interceptor{

        override fun intercept(chain: Interceptor.Chain): Response {
           val request: Request = chain.request().newBuilder().header("X-Api-Key", API_KEY).build()
           return chain.proceed(request)
        }

    }

}