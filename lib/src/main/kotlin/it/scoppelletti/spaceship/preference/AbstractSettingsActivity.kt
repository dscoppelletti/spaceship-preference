/*
 * Copyright (C) 2018 Dario Scoppelletti, <http://www.scoppelletti.it/>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("JoinDeclarationAndAssignment", "RedundantVisibilityModifier")

package it.scoppelletti.spaceship.preference

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.UiThread
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import it.scoppelletti.spaceship.app.tryFinish
import it.scoppelletti.spaceship.preference.databinding.ItScoppellettiPrefSettingsActivityBinding

/**
 * Activity hosting a settings fragment.
 *
 * @since 1.0.0
 */
@UiThread
public abstract class AbstractSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ItScoppellettiPrefSettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar

        super.onCreate(savedInstanceState)

        binding = ItScoppellettiPrefSettingsActivityBinding.inflate(
                layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
                .replace(R.id.contentFrame, createFragment())
                .commit()
    }

    /**
     * Creates the settings fragment.
     *
     * @return The new object.
     */
    protected abstract fun createFragment(): AbstractPreferenceFragment

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
