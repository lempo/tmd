package ru.lempo.tmdviewer.core.module

import android.os.Build
import android.util.Log
import com.google.gson.*
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.lempo.tmdviewer.BuildConfig
import ru.lempo.tmdviewer.network.TMDApi
import ru.lempo.tmdviewer.data.remote.interceptor.ApiKeyInterceptor
import ru.lempo.tmdviewer.data.remote.utils.Tls12SocketFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext

/**
 * @author Yamushev Igor
 * @since  03.08.17
 */
@Module
class NetworkModule {

    companion object {
        private val CONNECTION_TIMEOUT_S: Long = 5
        private val READ_TIMEOUT_S: Long = 5
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient, gson: Gson): TMDApi =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(TMDApi::class.java)!!

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
        return builder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder() = OkHttpClient.Builder()
            .apply {
                setTimeouts(this)
                enableTls12OnPreLollipop(this)
                addInterceptor(ApiKeyInterceptor())
                if (BuildConfig.DEBUG)
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder) =
            builder.build()

    private fun setTimeouts(builder: OkHttpClient.Builder) {
        builder.connectTimeout(CONNECTION_TIMEOUT_S, TimeUnit.SECONDS)
        builder.readTimeout(READ_TIMEOUT_S, TimeUnit.SECONDS)
    }

    private fun enableTls12OnPreLollipop(client: OkHttpClient.Builder): OkHttpClient.Builder {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            try {
                val sc = SSLContext.getInstance("TLSv1.2")
                sc.init(null, null, null)
                client.sslSocketFactory(Tls12SocketFactory(sc.socketFactory))

                val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build()

                val specs = ArrayList<ConnectionSpec>()
                specs.add(cs)
                specs.add(ConnectionSpec.COMPATIBLE_TLS)
                specs.add(ConnectionSpec.CLEARTEXT)

                client.connectionSpecs(specs)
            } catch (exc: Throwable) {
                Log.e("OkHttp", "Error while setting TLS 1.2", exc)
            }

        }
        return client
    }
}