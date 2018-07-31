package com.promobi.assignment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.promobi.assignment.api.ApiModule
import com.promobi.assignment.api.ApiService
import com.promobi.assignment.api.DaggerMyComponent
import com.promobi.assignment.api.MyComponent
import com.promobi.assignment.models.Lists
import com.promobi.assignment.models.NewsResponseSchema
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: ApiService

    lateinit var myComponent: MyComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        myComponent = DaggerMyComponent.builder()
                .apiModule(ApiModule())
                .build()

        myComponent.inject(this)
        api.getNewsList("1eb03f516e4346baa4febed5511c231a")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    saveToDB(it)
                }
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe()
    }

    private fun initViews() {
    }

    private fun saveToDB(responseSchema: NewsResponseSchema?) {
        rv_list.layoutManager=LinearLayoutManager(this)
        rv_list.adapter=ListAdapter(responseSchema!!.results.lists as ArrayList<Lists>)
//        val db = AppDatabase.getDatabase(this).databaseDao()
////        db.deleteAll()
//        db.saveResults(responseSchema!!.results)
//        Log.d("db", db.getResults().value?.get(0)?.bestsellersDate)

    }
}
