package com.promobi.assignment.data

import com.promobi.assignment.data.local.DatabaseDao
import com.promobi.assignment.data.remote.ApiService
import com.promobi.assignment.models.NewsResponseSchema
import com.promobi.assignment.util.Constants
import com.promobi.assignment.util.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vishwajit on 01/08/18.
 */
class Repository @Inject constructor(val apiService: ApiService,
                                     val databaseDao: DatabaseDao, val utils: Utils) {

    fun getResult(): Observable<NewsResponseSchema> {
        return if (utils.isConnectedToInternet())
            Observable.concatArrayEager(getResultFromApi(), getResultFromDb())
        else
            getResultFromDb()
    }

    private fun getResultFromApi(): Observable<NewsResponseSchema> {
        return apiService.getNewsList(Constants.API_KEY)
                .doOnNext {
                    it?.run {
                        databaseDao.deleteAll()
                        databaseDao.saveResults(this)
                    }
                }
                .doOnError { it.printStackTrace() }
    }

    private fun getResultFromDb(): Observable<NewsResponseSchema> {
        return databaseDao.getNewsResponseSchema()
                .toObservable()
                .doOnNext { }
                .doOnError { it.printStackTrace() }
    }
}