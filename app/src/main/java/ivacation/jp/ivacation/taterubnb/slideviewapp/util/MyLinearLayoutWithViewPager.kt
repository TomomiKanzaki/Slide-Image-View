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
import kotlinx.android.synthetic.main.my_view_page.view.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.util.ArrayList

class MyLinearLayoutWithViewPager// TODO Auto-generated constructor stub
    (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private lateinit var textView: TextView
    private lateinit var textViews: Array<TextView?>
    private val myPages = ArrayList<View>()

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.my_view_page, this)
    }

    /**
     * ViewPagerでページングするViewのリストを受け取り、設定する。
     * @param list
     */
    fun setList(list: ArrayList<View>) {
        viewGroup.removeAllViews()
        textViews = arrayOfNulls(list.size)
        for (i in list.indices) {
            //2ページ以上になる場合は、1ループごとにインジケータ用のViewを足しておく
            if (list.size > 1) {
                textView = TextView(context)
                val lp = LinearLayout.LayoutParams(60, 60)
                lp.rightMargin = 10
                lp.leftMargin = 10
//                lp.bottomMargin = 100
                textView.layoutParams = lp
                textView.setPadding(0, 0, 0, 0)
                textViews[i] = textView

                textViews[i]?.setBackgroundResource(R.drawable.radio)
                if (i == 0) {
                    textViews[i]?.let { setRadioStyle(it, true) }
                } else {
                    textViews[i]?.let { setRadioStyle(it, false) }
                }
                viewGroup.addView(textViews[i])
            }

            myPages.add(list[i])
        }

        checkbox.setOnClickListener {
            if (checkbox.isChecked){
                context.toast("チェックされました")
            } else {
                context.toast("チェックが外れました")
            }
        }

        pager.adapter = MyViewPagerAdapter(list)
        pager.setOnPageChangeListener(MyListener())
    }

    private fun setRadioStyle(tv: TextView, selected: Boolean) {
        val lp = tv.layoutParams
        if (selected) {
            lp.height = 35
            lp.width = 35
        } else {
            lp.height = 20
            lp.width = 20
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

                ll_checkbox.visibility = if (arg0 == textViews.lastIndex){
                    View.VISIBLE
                } else { View.GONE }
            }
        }

    }

}