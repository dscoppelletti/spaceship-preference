@file:Suppress("JoinDeclarationAndAssignment")

package it.scoppelletti.spaceship.preference.sample

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import it.scoppelletti.spaceship.app.tryFinish
import it.scoppelletti.spaceship.preference.AbstractPreferenceFragment
import it.scoppelletti.spaceship.preference.sample.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar

        super.onCreate(savedInstanceState)

        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)

        OssLicensesMenuActivity.setActivityTitle(getString(R.string.cmd_oss))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                tryFinish()
                return true
            }

            R.id.cmd_oss -> {
                startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

@Suppress("unused")
class SettingsFragment : AbstractPreferenceFragment() {

    override fun onCreatePreferences(
            savedInstanceState: Bundle?,
            rootKey: String?
    ) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        findPreference<Preference>(MainApp.PROP_FEEDBACK)?.startActivityConfig()
        findPreference<Preference>(MainApp.PROP_CREDITS)?.startActivityConfig()
    }
}

