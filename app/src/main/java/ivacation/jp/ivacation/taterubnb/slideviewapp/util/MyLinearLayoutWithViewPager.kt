package ivacation.jp.ivacation.taterubnb.slideviewapp.util

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import ivacation.jp.ivacation.taterubnb.slideviewapp.R
import java.util.ArrayList

class MyLinearLayoutWithViewPager// TODO Auto-generated constructor stub
    (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var viewPager: MyViewPager
    private var group: ViewGroup
    private lateinit var textView: TextView
    private lateinit var textViews: Array<TextView?>
    private var _context: Context = context
    private val myPages = ArrayList<View>()

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.my_view_page, this)
        viewPager = findViewById<MyViewPager>(R.id.pager)
        group = findViewById<ViewGroup>(R.id.viewGroup)
    }

    /**
     * ViewPagerでページングするViewのリストを受け取り、設定する。
     * @param list
     */
    fun setList(list: ArrayList<View>) {
        group.removeAllViews()
        textViews = arrayOfNulls(list.size)
        for (i in list.indices) {
            //2ページ以上になる場合は、1ループごとにインジケータ用のViewを足しておく
            if (list.size > 1) {
                textView = TextView(_context)
                val lp = LinearLayout.LayoutParams(60, 60)
                lp.rightMargin = 10
                lp.leftMargin = 10
//                lp.bottomMargin = 100
                textView.layoutParams = lp
                textView.setPadding(0, 0, 0, 0)
                textViews[i] = textView

                textViews[i]?.setBackgroundResource(R.drawable.carrot)
                if (i == 0) {
                    textViews[i]?.let { setRadioStyle(it, true) }
                } else {
                    textViews[i]?.let { setRadioStyle(it, false) }
                }
                group.addView(textViews[i])
            }

            myPages.add(list[i])
        }
        viewPager.adapter = MyViewPagerAdapter(list)
        viewPager.setOnPageChangeListener(MyListener())
    }

    private fun setRadioStyle(tv: TextView, selected: Boolean) {
        val lp = tv.layoutParams
        if (selected) {
            lp.height = 50
            lp.width = 50
        } else {
            lp.height = 40
            lp.width = 40
        }
        tv.layoutParams = lp
    }

    internal inner class MyListener : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(arg0: Int) {}

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageSelected(arg0: Int) {
            for (i in textViews.indices) {
                if (arg0 != i) {
                    textViews[i]?.let { setRadioStyle(it, false) }
                } else {
                    textViews[i]?.let { setRadioStyle(it, true) }
                }
            }
        }

    }

}