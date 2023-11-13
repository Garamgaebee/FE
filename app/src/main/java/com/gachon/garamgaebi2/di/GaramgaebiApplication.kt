package com.gachon.garamgaebi2.di

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import com.gachon.garamgaebi2.util.DataStoreUtil
import com.gachon.garamgaebi2.util.SharedPreferenceUtil
import com.gachon.garamgaebi2.util.Utils.BASE_URL
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.first
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
@HiltAndroidApp
class GaramgaebiApplication : Application() {

    companion object {
        private lateinit var application: GaramgaebiApplication
        lateinit var spfManager: SharedPreferenceUtil
        lateinit var dsManager: DataStoreUtil

        val gameOut : MutableLiveData<Boolean> = MutableLiveData(false)
        const val testEmail = "garamgaebiMaster2"
        const val testPW = "000000"
        var registerProcess = 0

        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

        // JWT Token Header 키 값
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val X_REFRESH_TOKEN = "X_REFRESH_TOKEN"
        var myMemberIdx = 0

        var getProfile = true
        var getSNS = true
        var getCareer = true
        var getEdu = true

        var bitmap: Bitmap? = null

        //초기 해쉬맵 객체 선언
        var fragmentHashMap = HashMap<Int, String>()

        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.
        lateinit var sRetrofit: Retrofit



    }
    // 앱이 처음 생성되는 순간, SP를 새로 만들어주고, 레트로핏 인스턴스를 생성합니다.
    override fun onCreate() {
        super.onCreate()
        application = this
        spfManager = SharedPreferenceUtil(applicationContext)
        dsManager = DataStoreUtil(applicationContext)

        //KakaoSdk.init(this, "${BuildConfig.KAKAO_API_KEY}")
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()

        settingScreenPortrait()


    }
    private fun settingScreenPortrait() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            @SuppressLint("SourceLockedOrientationActivity")
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                // 화면 세로모드
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            override fun onActivityStarted(p0: Activity) {}
            override fun onActivityResumed(p0: Activity) {}
            override fun onActivityPaused(p0: Activity) {}
            override fun onActivityStopped(p0: Activity) {}
            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
            override fun onActivityDestroyed(p0: Activity) {}
        })
    }
    // 레트로핏 인스턴스를 생성하고, 레트로핏에 각종 설정값들을 지정해줍니다.
    // 연결 타임아웃시간은 5초로 지정이 되어있고, HttpLoggingInterceptor를 붙여서 어떤 요청이 나가고 들어오는지를 보여줍니다.
    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            //.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthInterceptor()) // JWT 자동 헤더 전송
            .build()

        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}