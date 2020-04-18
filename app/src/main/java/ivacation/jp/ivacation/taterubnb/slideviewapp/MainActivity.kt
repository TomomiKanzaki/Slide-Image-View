package ivacation.jp.ivacation.taterubnb.slideviewapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import ivacation.jp.ivacation.taterubnb.slideviewapp.api.Client
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_view_page_layout.view.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.lang.Exception
import java.util.ArrayList
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.my_view_page.*
import java.io.File
import java.io.IOException


class MainActivity : AppCompatActivity() {

//    val EN1 = "house_rules/1/en/1.png"
//    val EN2 = "house_rules/1/en/2.png"
//    val EN3 = "house_rules/1/en/3.png"
//    val EN4 = "house_rules/1/en/4.png"
//    val EN5 = "house_rules/1/en/5.png"
//    val EN6 = "house_rules/1/en/6.png"
//    val EN7 = "house_rules/1/en/7.png"
//    val JA1 = "house_rules/1/ja/1.png"
//    val JA2 = "house_rules/1/ja/2.png"
//    val JA3 = "house_rules/1/ja/3.png"
//    val JA4 = "house_rules/1/ja/4.png"
//    val JA5 = "house_rules/1/ja/5.png"
//    val JA6 = "house_rules/1/ja/6.png"
//    val JA7 = "house_rules/1/ja/7.png"
    val KO1 = "test1.png"
    val KO2 = "test2.png"
    val KO3 = "test3.png"
    val KO4 = "test4.png"
    val KO5 = "test5.png"
    val KO6 = "test6.png"
    val KO7 = "test7.png"
//    val ZH_CH1 = "house_rules/1/zh-ch/1.png"
//    val ZH_CH2 = "house_rules/1/zh-ch/2.png"
//    val ZH_CH3 = "house_rules/1/zh-ch/3.png"
//    val ZH_CH4 = "house_rules/1/zh-ch/4.png"
//    val ZH_CH5 = "house_rules/1/zh-ch/5.png"
//    val ZH_CH6 = "house_rules/1/zh-ch/6.png"
//    val ZH_CH7 = "house_rules/1/zh-ch/7.png"
//    val ZH_TW1 = "house_rules/1/zh-tw/1.png"
//    val ZH_TW2 = "house_rules/1/zh-tw/2.png"
//    val ZH_TW3 = "house_rules/1/zh-tw/3.png"
//    val ZH_TW4 = "house_rules/1/zh-tw/4.png"
//    val ZH_TW5 = "house_rules/1/zh-tw/5.png"
//    val ZH_TW6 = "house_rules/1/zh-tw/6.png"
//    val ZH_TW7 = "house_rules/1/zh-tw/7.png"
//
//    val list_EN = listOf<String>(EN1, EN2, EN3, EN4, EN5, EN6, EN7)
//    val list_JA = listOf<String>(JA1, JA2, JA3, JA4, JA5, JA6, JA7)
    val list_KO = listOf<String>(KO1, KO2, KO3, KO4, KO5, KO6, KO7)
//    val list_ZH_CH = listOf<String>(ZH_CH1, ZH_CH2, ZH_CH3, ZH_CH4, ZH_CH5, ZH_CH6, ZH_CH7)
//    val list_ZH_TW = listOf<String>(ZH_TW1, ZH_TW2, ZH_TW3, ZH_TW4, ZH_TW5, ZH_TW6, ZH_TW7)

    private lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filePath = cacheDir.absolutePath + "/"
    }

    override fun onResume() {
        super.onResume()

        val list = ArrayList<View>()

        list_KO.forEach{endPoint ->
            val absolutePath = filePath + endPoint.replace("/", "_")
            val image = getImageForViewPager(absolutePath)
            if (image == null){
                list.clear()
                if (endPoint == list_KO[list_KO.lastIndex]) {
                    val client = Client(this)
                    getPngFromServer(client, list_KO)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { progress_bar.visibility = View.VISIBLE }
                        .subscribe({ filePath ->
                            val image = getImageForViewPager(filePath)
                            if (image == null) {
                                Exception("取得失敗")
                            } else {
                                list.add(image)
                            }
                        }, {
                            progress_bar.visibility = View.GONE
                            longToast("error; $it")
                            Log.e("Exception ", " $it")
                        }, {
                            progress_bar.visibility = View.GONE
                            viewPager.setList(list)
                        })
                }
            } else {
                list.add(image)
                if (endPoint == list_KO[list_KO.lastIndex]){
                    viewPager.setList(list)
                }
            }
        }
    }

    private fun getPngFromServer(client: Client, list: List<String>, index: Int = 0): Observable<String> = when(index){
        in 0.. list.lastIndex -> {
            Observable.concat(client.getPng(list[index]).toObservable(), getPngFromServer(client, list, index + 1))
        }
        else -> Observable.empty()
    }

    private fun getImageForViewPager(filePath: String): View?{
        val bitmap = try {
            BitmapFactory.decodeFile(filePath)
        } catch (e: Exception){
            longToast("画像取得失敗")
            Log.e("getFILE ", "$e")
            null
        }?: return null

        val pager = LayoutInflater.from(this).inflate(R.layout.my_view_page_layout, null)
        pager.image.setImageBitmap(bitmap)

        return pager
    }
}
