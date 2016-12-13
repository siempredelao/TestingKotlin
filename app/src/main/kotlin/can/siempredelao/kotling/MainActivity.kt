package can.siempredelao.kotling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

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

        // Background thread
        doAsync {
            var result = runLongTask()
            uiThread { // This block runs on UI thread, as its name says... :p
                toast(result)
            }
        }

        val aLinearLayout = LinearLayout(this)
        aLinearLayout.children.forEach { it.visible() }

        data class Person2(var name: String, var surname: String, var id: String)

        // Destructuring declaration
        val person2 = Person2("x", "y", "z")
        val (n, s, i) = person2

        val map = mapOf(1 to "a", 2 to "b")
        for ((key, value) in map) {
            toast("key: $key, value: $value")
        }

        data class Person3(val name: String, val surname: String, val id: String)

        // Object copy
        val person3 = Person3("John", "Smith", "123abc")
        val person4 = person3.copy(surname="Rogers")
    }

    private fun runLongTask(): Int {
        Thread.sleep(5000)
        return 0
    }

    // Lambda
    fun setMyCustomListener(listener: (view: View) -> Unit) {
    }

    // DSL
    // doAsync will create anonymous classes
    // Replace f() with any number of functions when calling doAsync
    fun doAsync3(f: () -> Unit) {
        Thread({ f() }).start()
    }

    // Using "inline" will replace the code of doAsync2 function in each usage at compile time
    // inline functions does not require as many resources as non-inline one
    inline fun doAsync2(crossinline f: () -> Unit) {
        Thread({ f() }).start()
    }

    // Extension function
    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    val ViewGroup.children: List<View> get() = (0..childCount - 1).map { getChildAt(it) }
}
