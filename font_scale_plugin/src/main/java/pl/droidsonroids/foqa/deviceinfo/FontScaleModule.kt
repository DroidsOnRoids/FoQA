package pl.droidsonroids.foqa.deviceinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import pl.droidsonroids.foqa.fontscale.R
import kotlin.properties.Delegates

internal class FontScaleModule : PluginModule() {

    private var fontScaleInteractor: FontScaleInteractor by Delegates.notNull()

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.foqa_font_scale_menu_item, parent, false)
        val seekBar = view.findViewById<SeekBar>(R.id.fontScaleSeekBar)
        val valueTextView = view.findViewById<TextView>(R.id.fontScaleValue)
        fontScaleInteractor = FontScaleInteractor(view.context)

        fun showFontScale(scale: Float) {
            valueTextView.text = view.context.getString(R.string.foqa_font_scale_value, scale)
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
                fontScaleInteractor.ensureSystemSettingsWritable()
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
