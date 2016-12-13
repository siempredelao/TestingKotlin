package can.siempredelao.kotling

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import can.siempredelao.kotling.dagger.HomeModule

class HomeActivity : AppCompatActivity() {

    val component by lazy { app.component.plus(HomeModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        component.inject(this)
    }
}
