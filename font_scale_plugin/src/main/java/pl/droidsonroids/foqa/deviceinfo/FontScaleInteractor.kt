package pl.droidsonroids.foqa.deviceinfo

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import pl.droidsonroids.foqa.fontscale.R

internal class FontScaleInteractor(private val context: Context) {

    var systemFontScale
        get() = Settings.System.getFloat(context.contentResolver, Settings.System.FONT_SCALE, 1.0f)
        set(value) {
            Settings.System.putFloat(context.contentResolver, Settings.System.FONT_SCALE, value)
        }

    val isSystemSettingsWritable
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.System.canWrite(context)

    fun ensureSystemSettingsWritable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(context)) {
            startSystemSettingsActivity()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun startSystemSettingsActivity() {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .setData(Uri.parse("package:" + context.packageName))
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.w("FoQA", e)
            Toast.makeText(
                context,
                R.string.foqa_font_scale_system_settings_disabled,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
