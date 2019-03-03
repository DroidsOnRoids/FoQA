package pl.droidsonroids.foqa.deviceinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import pl.droidsonroids.foqa.fontscale.R

@AutoService(Plugin::class)
class FontScalePlugin : Plugin() {
    override fun createPluginModule(): PluginModule = FontScaleModule()
}

private const val SEEK_BAR_SCALE = 50f

internal class FontScaleModule : PluginModule() {

    @SuppressLint("SetTextI18n")
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

        showFontScale(currentFontScale)

        seekBar.max = 100
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    showFontScale(progress.toFontScale())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(context)) {
                    context.startActivity(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.System.canWrite(context)) {
                    Settings.System.putFloat(context.contentResolver, Settings.System.FONT_SCALE, seekBar.progress.toFontScale())
                } else {
                    showFontScale(currentFontScale)
                    seekBar.progress = currentFontScale.toSeekBarValue()
                }
            }

        })

        return view
    }

    override fun getName() = R.string.foqa_font_scale_plugin_name

    private fun Float.toSeekBarValue() = (this * SEEK_BAR_SCALE).toInt()
    private fun Int.toFontScale() = this / SEEK_BAR_SCALE

    private val currentFontScale
        get() = Settings.System.getFloat(context.contentResolver, Settings.System.FONT_SCALE)
}
