@file:Suppress("JoinDeclarationAndAssignment")

package it.scoppelletti.spaceship.preference.sample

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import it.scoppelletti.spaceship.app.tryFinish
import it.scoppelletti.spaceship.html.app.HtmlViewFragment
import it.scoppelletti.spaceship.preference.sample.databinding.CreditsActivityBinding

class CreditsActivity : AppCompatActivity() {

    private lateinit var binding: CreditsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar

        super.onCreate(savedInstanceState)

        binding = CreditsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
                .replace(R.id.contentFrame, HtmlViewFragment.newInstance(
                        HtmlViewFragment.URL_ASSET + "credits.html"))
                .commit()
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

