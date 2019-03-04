package pl.droidsonroids.foqa.deviceinfo

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.auto.service.AutoService
import com.jaredrummler.android.device.DeviceName
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class DeviceInfoPlugin : Plugin() {
    override fun createPluginModule(): PluginModule = DeviceInfoModule()
}

internal class DeviceInfoModule : PluginModule() {

    @SuppressLint("SetTextI18n")
    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        val view =
            layoutInflater.inflate(R.layout.foqa_deviceinfo_menu_item, parent, false) as TextView
        val manufacturer = Build.MANUFACTURER
        val deviceModel = DeviceName.getDeviceName()
        val sdk = Build.VERSION.RELEASE
        view.text = "$manufacturer $deviceModel Android: $sdk"
        return view
    }

    override fun getName() = R.string.foqa_device_info_plugin_name
}
