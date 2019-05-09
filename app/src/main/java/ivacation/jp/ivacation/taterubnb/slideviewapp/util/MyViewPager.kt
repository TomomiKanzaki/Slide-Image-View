package ivacation.jp.ivacation.taterubnb.slideviewapp.util

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class MyViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    private var willIntercept = true

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return if (willIntercept) {
            super.onInterceptTouchEvent(arg0)
        } else  false
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        return try {
            if (willIntercept) {
                super.onTouchEvent(arg0)
            } else false
        } catch (e: Exception) { false }
    }
}
