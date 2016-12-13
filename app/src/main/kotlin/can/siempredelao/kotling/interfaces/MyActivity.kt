package can.siempredelao.kotling.interfaces

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by david on 13.12.16.
 */
class MyActivity : AppCompatActivity(), Toaster {
    override val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("onCreate")
    }
}

interface A {
    fun functionA(){}
}

interface B {
    fun functionB(){}
}

class C(val a: A, val b: B) {
    fun functionC(){
        a.functionA()
        b.functionB()
    }
}
// or
class D(a: A, b: B): A by a, B by b { // using A and B components directly
    fun functionC(){
        functionA()
        functionB()
    }
}