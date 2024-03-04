package pl.droidsonroids.foqa

import android.content.Context
import android.provider.Settings
import androidx.startup.Initializer
import com.willowtreeapps.hyperion.core.Hyperion
import com.willowtreeapps.hyperion.core.internal.HyperionInitializer

class HyperionDisableInitializer : Initializer<HyperionDisableInitializer> {

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(HyperionInitializer::class.java)

    override fun create(context: Context): HyperionDisableInitializer {
        if (isRunningOnFirebaseTestLab(context)) {
            Hyperion.disable()
        }
        return this
    }

    private fun isRunningOnFirebaseTestLab(context: Context) =
        Settings.System.getString(context.contentResolver, "firebase.test.lab") == "true"
}
