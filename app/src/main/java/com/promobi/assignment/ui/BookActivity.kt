package com.promobi.assignment.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.promobi.assignment.R
import com.promobi.assignment.models.Book
import com.promobi.assignment.models.Lists
import kotlinx.android.synthetic.main.activity_book.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class BookActivity : AppCompatActivity() {

    companion object {
        val LIST_ITEM = "LIST_ITEM"
        fun createIntent(context: Context, list: Lists): Intent {
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra(LIST_ITEM, list)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        initViews()
    }

    private fun initViews() {
        title = "Books"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val list =intent.getParcelableExtra<Lists>(LIST_ITEM)
        rv_books.layoutManager=LinearLayoutManager(this)
        rv_books.adapter= BookAdapter(list.books as ArrayList<Book>)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
    fun onMessageEvent(book: Book) {
        startActivity(BookDetailActivity.createIntent(this, book))
    }
}
