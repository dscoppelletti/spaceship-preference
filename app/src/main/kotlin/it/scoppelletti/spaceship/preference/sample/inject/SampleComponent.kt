package it.scoppelletti.spaceship.preference.sample.inject

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import it.scoppelletti.spaceship.html.inject.HtmlComponent
import it.scoppelletti.spaceship.html.inject.HtmlModule
import it.scoppelletti.spaceship.inject.AppComponent
import it.scoppelletti.spaceship.inject.AppModule
import it.scoppelletti.spaceship.inject.StdlibComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [ AppModule::class, HtmlModule::class ])
interface SampleComponent : AppComponent, HtmlComponent, StdlibComponent {

    @Component.Factory
    interface Factory {
        fun create(
                @BindsInstance
                application: Application
        ): SampleComponent
    }
}
