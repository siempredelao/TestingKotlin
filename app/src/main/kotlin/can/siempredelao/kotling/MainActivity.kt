package can.siempredelao.kotling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var x = 7 // "var" mutable
        var y: String = "my String"
        val z: View = findViewById(R.id.my_view) // "val" immutable

        if (z is TextView) {
            z.text = "I've been casted!"
        }

        // In Kotlin, everything is an object
        val a: Int = 20
        val b: Double = 21.5
        val c: Unit = Unit

        val d: Int = 20
//        val e: Long = d // does not compile, unable to assign to a complex type, casting needed
        val e: Long = d.toLong()

        my_view.text = "LOL"
        my_view.setOnClickListener { v -> v.toString() }

        setMyCustomListener { v -> v.bringToFront() }

        doAsync {
//            op1()
//            op2()
//            op3()
        }
    }

    // Lambda
    fun setMyCustomListener(listener: (view: View) -> Unit) {
    }

    // DSL
    // doAsync will create anonymous classes
    // Replace f() with any number of functions when calling doAsync
    fun doAsync(f: () -> Unit) {
        Thread({ f() }).start()
    }

    // Using "inline" will replace the code of doAsync2 function in each usage at compile time
    // inline functions does not require as many resources as non-inline one
    inline fun doAsync2(crossinline f: () -> Unit) {
        Thread({ f() }).start()
    }
}
