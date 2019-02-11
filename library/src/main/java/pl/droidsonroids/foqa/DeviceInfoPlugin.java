package pl.droidsonroids.foqa;

import android.os.Build;
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItem;
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItemHyperionPlugin;
import com.jaredrummler.android.device.DeviceName;

final class DeviceInfoPlugin {

    static void initialize() {
        String manufacturer = Build.MANUFACTURER;
        String deviceModel = DeviceName.getDeviceName();
        String sdk = Build.VERSION.RELEASE;
        SimpleItem item = new SimpleItem.Builder()
                .image(R.drawable.ic_info)
                .text(manufacturer + ' ' + deviceModel + " Android: " + sdk)
                .build();
        SimpleItemHyperionPlugin.addItem(item);
    }
}
