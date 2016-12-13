package can.siempredelao.kotling.dagger

import can.siempredelao.kotling.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by david on 13.12.16.
 */
@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app
}