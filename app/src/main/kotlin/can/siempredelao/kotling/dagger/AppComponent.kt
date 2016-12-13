package can.siempredelao.kotling.dagger

import can.siempredelao.kotling.App
import dagger.Component
import javax.inject.Singleton

/**
 * Created by david on 13.12.16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun plus(homeModule: HomeModule): HomeComponent
}