package can.siempredelao.kotling

import android.app.Application
import can.siempredelao.kotling.dagger.AppComponent
import can.siempredelao.kotling.dagger.AppModule
import can.siempredelao.kotling.dagger.DaggerAppComponent

/**
 * Created by david on 13.12.16.
 */
class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}