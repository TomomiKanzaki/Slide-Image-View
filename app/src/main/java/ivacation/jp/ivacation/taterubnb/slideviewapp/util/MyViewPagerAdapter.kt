package ivacation.jp.ivacation.taterubnb.slideviewapp.util

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

class MyViewPagerAdapter (private val list: ArrayList<View>) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
        return arg0 === arg1
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(list[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        (container as ViewPager).addView(list[position])
        return list[position]
    }

//    override fun finishUpdate(container: ViewGroup) {
//        super.finishUpdate(container)
//    }

//    override fun getItemPosition(`object`: Any): Int {
//        return super.getItemPosition(`object`)
//    }

//    override fun getPageTitle(
//        position: Int
//    ): CharSequence? {
//        return super.getPageTitle(position)
//    }
}