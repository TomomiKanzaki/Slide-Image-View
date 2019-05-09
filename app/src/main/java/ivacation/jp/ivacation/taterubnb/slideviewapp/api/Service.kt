package ivacation.jp.ivacation.taterubnb.slideviewapp.api

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Service {

    @GET
    fun getPng(@Url png: String): Single<Response<ResponseBody>>
}