package com.gandan.android.gandanbus


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gandan.android.gandanbus.Util.RetrofitInit
import com.gandan.android.gandanbus.Util.SERVICE_KEY
import com.gandan.android.gandanbus.model.BusLocationList
import com.gandan.android.gandanbus.model.Response
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    var retrofitInit = RetrofitInit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var locationList : Observable<Response> = retrofitInit.service.getLiveBus(SERVICE_KEY, 200000085)
        locationList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            next -> Log.e("List", next.msgBody.busLocationList.get(0).plateNo)
        }
    }


}
