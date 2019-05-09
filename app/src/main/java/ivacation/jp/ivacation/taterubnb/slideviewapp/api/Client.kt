package ivacation.jp.ivacation.taterubnb.slideviewapp.api

import android.content.Context
import android.util.Log
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ivacation.jp.ivacation.taterubnb.slideviewapp.BuildConfig
import okhttp3.OkHttpClient
import okio.Okio
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit

class Client(val context: Context) {

    private val baseUrl = "https://stg-regist.smart-check.in/"

    private val service = restClient().create(Service::class.java)

    private val filePath = context.cacheDir.absolutePath + "/"

    private fun restClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(builderHttpClient())
            .build()
    }

    private fun builderHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        return client.build()
    }

    fun getPng(png: String): Single<String> {
        return service.getPng(png)
            .subscribeOn(Schedulers.io())
            .flatMap { response ->
                Single.create<String>{ emitter ->
                    if (response.code() != 200){
                        emitter.onError(Exception("取得失敗"))
                    }

                    try {
                        val fileName = png.replace("/", "_")
                        Log.i("PATH ", "${filePath + fileName}")
                        val destinationFile = File(filePath + fileName)
                        val bufferedSink = Okio.buffer(Okio.sink(destinationFile))

                        bufferedSink.writeAll(response.body()?.source())
                        bufferedSink.close()

                        emitter.onSuccess(destinationFile.path)
                    } catch (e: Exception){
                        Log.e("saveFile ", "$e")
                        emitter.onError(e)
                    }
                }
            }
    }

}