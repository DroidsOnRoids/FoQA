package pl.droidsonroids.foqa

import android.content.ComponentName
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED
import android.content.pm.PackageManager.DONT_KILL_APP
import android.content.pm.ProviderInfo
import android.net.Uri
import android.provider.Settings
import com.willowtreeapps.hyperion.core.internal.HyperionInitProvider

class HyperionDisableProvider : ContentProvider() {

    override fun onCreate() = true

    override fun attachInfo(context: Context, info: ProviderInfo?) {
        super.attachInfo(context, info)
        if (isRunningOnFirebaseTestLab(context)) {
            disableHyperion(context)
        }
    }

    private fun isRunningOnFirebaseTestLab(context: Context): Boolean {
        return Settings.System.getString(context.contentResolver, "firebase.test.lab") == "true"
    }

    private fun disableHyperion(context: Context) {
        val hyperionInitProvider = ComponentName(context, HyperionInitProvider::class.java)
        context.packageManager.setComponentEnabledSetting(hyperionInitProvider, COMPONENT_ENABLED_STATE_DISABLED, DONT_KILL_APP)
    }

    override fun insert(uri: Uri, values: ContentValues?): Nothing? = null

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Nothing? = null

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ) = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = 0

    override fun getType(uri: Uri): Nothing? = null
}
