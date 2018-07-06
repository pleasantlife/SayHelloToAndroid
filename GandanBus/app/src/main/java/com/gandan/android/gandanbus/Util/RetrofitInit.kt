package com.gandan.android.gandanbus.Util

import com.gandan.android.gandanbus.model.Service
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

const val SERVER_URL = "http://openapi.gbis.go.kr/ws/rest/"
const val SERVICE_KEY = "0WsFS6QJRLa99LZJSCNP1RrAMA5qREUR32%2FTE8xm74m3UbOcabnwh4vYiukMJ9Sd%2FQ6oE69E%2B66sWm%2BTpXRVEg%3D%3D"

class RetrofitInit {

    var retrofitInit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(SimpleXmlConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    var service = retrofitInit.create(Service::class.java)

}