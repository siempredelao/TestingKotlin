package can.siempredelao.kotling

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sp
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    lateinit var textView10:TextView
    val textView11 by lazy { findView<TextView>(R.id.my_view) }

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

        // Functional operators
        aLinearLayout.children.forEach { v -> v.visibility = VISIBLE }
        // , or better:
        aLinearLayout.children.forEach { it.visibility = VISIBLE }

        // maps a integer range to a view collection (aLinearLayout children)
        val childViews = (0..aLinearLayout.childCount - 1).map { aLinearLayout.getChildAt(it) }

        // filters a collection elements
        val childViews2 = aLinearLayout.children.filter { it is ViewGroup }
        // in this case, we have filterIsInstance to perform the same action
        val childViews3 = aLinearLayout.children.filterIsInstance<ViewGroup>()

        // takes the first/last TextView child
        val firstTextView = aLinearLayout.children.first { it is TextView }
        val lastTextView = aLinearLayout.children.last { it is TextView }

        // sorts children by visibility
        val firstTextView2 = aLinearLayout.children.sortedBy { it.visibility }

        // takes those children which are instance of ViewGroup, sort them by visibility and take those which
        // visibility is lower than GONE (in theory, VISIBLE and INVISIBLE)
        (0..aLinearLayout.childCount - 1)
                .map { aLinearLayout.getChildAt(it) }
                .filterIsInstance<ViewGroup>()
                .sortedBy { it.visibility }
                .takeWhile { it.visibility < View.GONE }

        // Generic functions
        // Instead of val childViews = (0..aLinearLayout.childCount - 1).map { aLinearLayout.getChildAt(it) }
        with(aLinearLayout) { // avoid repeating aLinearLayout
            val childViews = (0..childCount - 1).map { getChildAt(it) }
        }

        with2(my_view) {
            text = "Hello World"
            visibility = View.VISIBLE
            textSize = sp(14).toFloat()
        }

        val textView = with3(TextView(this)) {
            text = "Hello World"
            visibility = View.VISIBLE
            textSize = sp(14).toFloat()
        }

        val textView2 = TextView(this).apply {
            text = "Hello World"
            visibility = View.VISIBLE
            textSize = sp(14).toFloat()
        }

        // If TextView text is not null, show a toast
        textView?.text?.let { toast(it) }

        // When clause
        when (textView.visibility) {
            View.VISIBLE -> toast("visible")
            View.INVISIBLE -> toast("invisible")
            else -> toast("gone")
        }

        val view = View(this)
        when (view) { // Checking types on left side: right side will be casted to that type
            is TextView -> toast(view.text)
            is ListView -> toast("Item count = ${view.adapter.count}")
            is SearchView -> toast("Current query: ${view.query}")
            else -> toast("View type not supported")
        }

        val res = when { // when without parameter
            x in 1..10 -> "cheap"
            s.contains("hello") -> "it's a welcome!"
            view is ViewGroup -> "child count: ${view.getChildCount()}"
            else -> ""
        }

        // Reified types
        startActivity<MainActivity>()

//        findView(R.id.my_view) // not working :s

        // Delegated properties
        textView10 = findView(R.id.my_view)
        toast(textView10.text)

        toast(textView11.text)

//        var items: List<Item> by Delegates.observable(emptyList()) {
//            p, old, new -> notifyDataSetChanged()
//        }

        // Adapter
        val recycler = RecyclerView(this)
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = MyAdapter(items) {
            toast("${it.title} Clicked")
        }
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

    inline fun <T> with2(obj: T, f: T.() -> Unit) {
        obj.f()
    }

    // Builder: returns a generic type
    inline fun <T> with3(obj: T, f: T.() -> Unit): T {
        obj.f()
        return obj
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.home -> consume { navigateToHome() }
//        R.id.search -> consume { MenuItemCompat.expandActionView(item) }
//        R.id.settings -> consume { navigateToSettings() }
        else -> super.onOptionsItemSelected(item)
    }

    private fun navigateToSettings() {
    }

    private fun navigateToHome() {
    }

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    inline fun <reified T : Activity> Activity.startActivity() {
        startActivity(Intent(this, T::class.java))
    }

    inline fun <reified T> Activity.findView(id: Int) = findViewById(id) as T
}
