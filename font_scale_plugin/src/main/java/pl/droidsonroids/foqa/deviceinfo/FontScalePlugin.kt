package pl.droidsonroids.foqa.deviceinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class FontScalePlugin : Plugin() {
    override fun createPluginModule(): PluginModule = FontScaleModule()
}

internal class FontScaleModule : PluginModule() {

    @SuppressLint("SetTextI18n")
    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.foqa_font_scale_menu_item, parent, false) as SeekBar
        view.max = 200
        view.progress = (Settings.System.getFloat(context.contentResolver, Settings.System.FONT_SCALE) * 100).toInt()
        view.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val value = progress.toFloat() / seekBar.max + 1f
                if (Settings.System.canWrite(context)) {
                    Settings.System.putFloat(context.contentResolver, Settings.System.FONT_SCALE, value)
                } else {
                    context.startActivity(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit

        })
        return view
    }
}
