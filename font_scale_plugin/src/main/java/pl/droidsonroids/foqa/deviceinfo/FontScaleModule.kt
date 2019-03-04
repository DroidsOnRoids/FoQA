package pl.droidsonroids.foqa.deviceinfo

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import pl.droidsonroids.foqa.fontscale.R

internal class FontScaleModule : PluginModule() {

    private lateinit var fontScaleInteractor: FontScaleInteractor

    override fun onCreate() {
        super.onCreate()
        fontScaleInteractor = FontScaleInteractor(context)
    }

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.foqa_font_scale_menu_item, parent, false)
        val seekBar = view.findViewById<SeekBar>(R.id.fontScaleSeekBar)
        val valueTextView = view.findViewById<TextView>(R.id.fontScaleValue)

        fun showFontScale(scale: Float) {
            valueTextView.text = context.getString(R.string.foqa_font_scale_value, scale)
            valueTextView.scaleX = scale
            valueTextView.scaleY = scale
            seekBar.progress = scale.toSeekBarValue()
        }

        showFontScale(fontScaleInteractor.systemFontScale)

        seekBar.max = SEEK_BAR_MAX
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    showFontScale(progress.toFontScale())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                fontScaleInteractor.ensureSystemSettingsWritability()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (fontScaleInteractor.isSystemSettingsWritable) {
                    fontScaleInteractor.systemFontScale = seekBar.progress.toFontScale()
                } else {
                    showFontScale(fontScaleInteractor.systemFontScale)
                    seekBar.progress = fontScaleInteractor.systemFontScale.toSeekBarValue()
                }
            }

        })

        return view
    }

    override fun getName() = R.string.foqa_font_scale_plugin_name
}

internal class FontScaleInteractor(private val context: Context) {

    var systemFontScale
        get() = Settings.System.getFloat(context.contentResolver, Settings.System.FONT_SCALE)
        set(value) {
            Settings.System.putFloat(context.contentResolver, Settings.System.FONT_SCALE, value)
        }

    val isSystemSettingsWritable
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.System.canWrite(context)

    fun ensureSystemSettingsWritability() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(context)) {
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
            Toast.makeText(context, R.string.foqa_font_scale_plugin_name, Toast.LENGTH_SHORT).show()
        }
    }
}