package pl.droidsonroids.foqa.deviceinfo

import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class FontScalePlugin : Plugin() {
    override fun createPluginModule(): PluginModule = FontScaleModule()
}