package br.com.maceda.marvel.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import br.com.maceda.marvel.data.dao.AppDatabase
import br.com.maceda.marvel.data.dao.CharacterDao
import br.com.maceda.marvel.data.remote.CharacterService
import br.com.maceda.marvel.repository.CacheRepository
import br.com.maceda.marvel.repository.CharacterRepository
import br.com.maceda.marvel.repository.DatabaseRepository
import br.com.maceda.marvel.repository.ServiceRepository
import br.com.maceda.marvel.util.Constants
import br.com.maceda.marvel.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .addInterceptor {
                val timeStamp = System.currentTimeMillis()
                val newUrl = it.request()
                    .url
                    .newBuilder()
                    .addQueryParameter(Constants.Params.TS, timeStamp.toString())
                    .addQueryParameter(Constants.Params.API_KEY, Constants.PUBLIC_KEY)
                    .addQueryParameter(Constants.Params.HASH,providerHashMd5(timeStamp.toString() + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY))
                    .build()

                val requestModified = it.request().newBuilder().url(newUrl).build()

                it.proceed(requestModified)
            }
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providerServiceApi(retrofit: Retrofit): CharacterService{
        return retrofit.create(CharacterService::class.java)
    }

    @Singleton
    @Provides
    fun providerDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "marvel"
        ).build()
    }

    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    @Provides
    fun provideCharacterRepository(service: ServiceRepository,
                                   database: DatabaseRepository,
                                   characterDao: CharacterDao,): CharacterRepository {
        return CacheRepository(service, database, characterDao)
        //return service
        //return database
    }

    @Singleton
    @Provides
    fun providerHashMd5(value: String): String {
        var pass = value
        var encryptedString: String? = null
        val md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
            md5.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, md5.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            encryptedString = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }
        Log.d("hash","$encryptedString")
        return encryptedString ?: ""
    }
}