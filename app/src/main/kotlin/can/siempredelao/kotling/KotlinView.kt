package can.siempredelao.kotling

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created by david on 12.12.16.
 */
class KotlinView : View {

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    
}