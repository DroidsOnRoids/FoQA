package pl.droidsonroids.foqa.deviceinfo

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import pl.droidsonroids.foqa.fontscale.R

internal class FontScaleInteractor(private val context: Context) {

    var systemFontScale
        get() = Settings.System.getFloat(
            context.contentResolver,
            Settings.System.FONT_SCALE
        )
        set(value) {
            Settings.System.putFloat(
                context.contentResolver,
                Settings.System.FONT_SCALE,
                value
            )
        }

    val isSystemSettingsWritable
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.System.canWrite(
            context
        )

    fun ensureSystemSettingsWritability() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(
                context
            )) {
            startSystemSettingsActivity()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun startSystemSettingsActivity() {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                R.string.foqa_font_scale_system_settings_disabled,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}