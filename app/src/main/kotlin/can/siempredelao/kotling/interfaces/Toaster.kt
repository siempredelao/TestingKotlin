package can.siempredelao.kotling.interfaces

import android.content.Context
import android.widget.Toast

/**
 * Created by david on 13.12.16.
 */
interface Toaster {
    val context: Context // Must be implemented in implementation class

    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}