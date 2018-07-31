package com.promobi.assignment.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.promobi.assignment.R
import com.promobi.assignment.models.Lists
import com.promobi.assignment.models.NewsResponseSchema
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private lateinit var adapter: ListAdapter

    private fun initViews() {
        title = "Category"
        rv_list.layoutManager = LinearLayoutManager(this)
        adapter = ListAdapter(ArrayList<Lists>())
        rv_list.adapter = adapter

        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(
                ViewModel::class.java)
        viewModel.loadResult()

        viewModel.result().observe(this, Observer<NewsResponseSchema> {
            it?.results?.lists?.run {
                if (!isEmpty()) {
                    progress_bar.visibility = View.GONE
                    adapter.updateItemList(this as ArrayList<Lists>)
                }
            }
        })

        viewModel.error().observe(this, Observer<String> {
            Log.e(MainActivity::class.java.simpleName, it)
        })

    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(list: Lists) {
        startActivity(BookActivity.createIntent(this, list))
    }

    override fun onDestroy() {
        viewModel.disposeElements()
        super.onDestroy()
    }
}
