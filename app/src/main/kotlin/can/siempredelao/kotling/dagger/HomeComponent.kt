package can.siempredelao.kotling.dagger

import can.siempredelao.kotling.HomeActivity
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by david on 13.12.16.
 */
@Singleton
@Subcomponent(modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(activity: HomeActivity)
}