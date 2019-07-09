package pl.droidsonroids.foqa.chuck

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.auto.service.AutoService
import com.readystatesoftware.chuck.Chuck
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class ChuckPlugin : Plugin() {
    override fun createPluginModule(): PluginModule = ChuckModule()
}

internal class ChuckModule : PluginModule() {

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.foqa_chuck_menu_item, parent, false)
        view.setOnClickListener {
            it.context.startActivity(Chuck.getLaunchIntent(it.context))
        }
        return view
    }

    override fun getName() = R.string.foqa_chuck_plugin_name
}
