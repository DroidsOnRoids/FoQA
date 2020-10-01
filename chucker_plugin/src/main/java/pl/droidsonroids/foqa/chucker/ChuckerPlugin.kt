package pl.droidsonroids.foqa.chucker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chuckerteam.chucker.api.Chucker
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class ChuckerPlugin : Plugin() {
    override fun createPluginModule(): PluginModule = ChuckerModule()
}

internal class ChuckerModule : PluginModule() {

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.foqa_chucker_menu_item, parent, false)
        view.setOnClickListener {
            it.context.startActivity(Chucker.getLaunchIntent(it.context))
        }
        return view
    }

    override fun getName() = R.string.foqa_chucker_plugin_name
}
