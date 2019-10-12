package pl.droidsonroids.foqa.deviceinfo

const val SEEK_BAR_MAX = 100
private const val FONT_SCALE_MIN = 0.4f
private const val FONT_SCALE_MAX = 1.6f
private const val SEEK_BAR_SCALE = SEEK_BAR_MAX / (FONT_SCALE_MAX - FONT_SCALE_MIN)

internal fun Float.toSeekBarValue() = ((this - FONT_SCALE_MIN) * SEEK_BAR_SCALE).toInt()
internal fun Int.toFontScale() = (this / SEEK_BAR_SCALE) + FONT_SCALE_MIN
