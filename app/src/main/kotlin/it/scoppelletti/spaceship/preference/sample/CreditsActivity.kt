@file:Suppress("JoinDeclarationAndAssignment")

package it.scoppelletti.spaceship.preference.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import it.scoppelletti.spaceship.app.tryFinish
import it.scoppelletti.spaceship.preference.sample.databinding.CreditsActivityBinding

class CreditsActivity: AppCompatActivity() {

    private lateinit var binding: CreditsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar
        val assetLoader: WebViewAssetLoader

        super.onCreate(savedInstanceState)

        binding = CreditsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)

        assetLoader = WebViewAssetLoader.Builder()
                .addPathHandler("/assets/",
                        WebViewAssetLoader.AssetsPathHandler(this))
                .build()

        binding.webView.webViewClient = object : WebViewClientCompat() {

            override fun shouldInterceptRequest(
                    view: WebView?,
                    url: String?
            ): WebResourceResponse? {
                return url?.let {
                    assetLoader.shouldInterceptRequest(Uri.parse(it))
                }
            }

            override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    url: String?
            ): Boolean {
                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    startActivity(this)
                }

                return true
            }
        }

        binding.webView.loadUrl(
                "https://appassets.androidplatform.net/assets/credits.html")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                tryFinish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
