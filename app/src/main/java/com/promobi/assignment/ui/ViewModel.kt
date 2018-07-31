package com.promobi.assignment.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.promobi.assignment.models.NewsResponseSchema
import com.promobi.assignment.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Vishwajit on 01/08/18.
 */
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var result: MutableLiveData<NewsResponseSchema> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<NewsResponseSchema>

    fun result(): LiveData<NewsResponseSchema> = result
    fun error(): LiveData<String> = error

    fun loadResult() {
        disposableObserver = object : DisposableObserver<NewsResponseSchema>() {
            override fun onComplete() {

            }

            override fun onNext(t: NewsResponseSchema) {
                result.postValue(t)
            }

            override fun onError(e: Throwable) {
                error.postValue(e.message)
            }
        }


        repository.getResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver)
    }

    fun disposeElements(){
        if(!disposableObserver.isDisposed) disposableObserver.dispose()
    }

}