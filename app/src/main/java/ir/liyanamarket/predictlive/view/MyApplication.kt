package ir.liyanamarket.predictlive.view

import android.app.Application
import ir.liyanamarket.predictlive.appmadules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()



        startKoin {

            androidContext(this@MyApplication)
           modules(listOf(appmadules))
        }




    }

}