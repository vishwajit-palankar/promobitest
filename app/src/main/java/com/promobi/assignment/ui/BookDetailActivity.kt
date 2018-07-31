package com.promobi.assignment.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.promobi.assignment.R
import com.promobi.assignment.databinding.ActivityBookDetailBinding
import com.promobi.assignment.models.Book
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class BookDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBookDetailBinding

    companion object {
        val BOOK_MODEL = "BOOK_MODEL"
        fun createIntent(context: Context, book: Book): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(BOOK_MODEL, book)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        initViews()
    }

    private fun initViews() {
        title = "Book Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.book = intent.getParcelableExtra(BOOK_MODEL)
        binding.tvReadReview.setOnClickListener { binding.book?.bookReviewLink?.run { openUrl(this) } }
        binding.tvReadFirstChapter.setOnClickListener { binding.book?.firstChapterLink?.run { openUrl(this) } }
        binding.book?.buyLinks?.run {
            binding.rvBuyLink.isNestedScrollingEnabled = false
            binding.rvBuyLink.layoutManager = LinearLayoutManager(this@BookDetailActivity)
            binding.rvBuyLink.adapter = BuyLinkAdapter(this)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
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
    fun onMessageEvent(url: String) {
        openUrl(url)
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
}
