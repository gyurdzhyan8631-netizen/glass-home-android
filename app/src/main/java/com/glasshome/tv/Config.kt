package com.glasshome.tv

/*
 * Glass Home - settings. Edit, then rebuild and reinstall (scripts/install.sh).
 */
object Config {
    // Car mode: touch first, phone-style apps, bigger tiles, a still background
    // and no screensaver (a tap on a dark screen should press, not just wake it).
    const val CAR_MODE = true
    const val STATIC_BACKGROUND = CAR_MODE

    const val PHOTO_INTERVAL_MS = 45_000L
    const val FADE_MS = 1_600
    const val KEN_BURNS_SCALE = 1.06f

    // Screensaver: only the photos after this long without a button press.
    const val AMBIENT_ENABLED = !CAR_MODE
    const val AMBIENT_AFTER_MS = 3 * 60 * 1000L

    // Evening dimming of the photos, by the TV's clock (24-hour).
    const val NIGHT_ENABLED = true
    const val NIGHT_FROM = "20:00"
    const val NIGHT_TO = "06:00"

    // Weather under the clock, from Open-Meteo (free, no key). Set the place on
    // the TV: select the weather (or "Set weather") and press OK, then enter a
    // ZIP or postal code. Or build one in with glasshome.properties (see
    // glasshome.properties.example), which stays out of git; find coordinates with
    // https://geocoding-api.open-meteo.com/v1/search?name=YOUR+TOWN
    val weather = Weather(
        enabled = BuildConfig.WEATHER_LATITUDE != 0.0 || BuildConfig.WEATHER_LONGITUDE != 0.0,
        place = BuildConfig.WEATHER_PLACE,          // label under the forecast
        latitude = BuildConfig.WEATHER_LATITUDE,
        longitude = BuildConfig.WEATHER_LONGITUDE,
        timezone = BuildConfig.WEATHER_TIMEZONE,    // "auto", or an IANA name such as "Europe/London"
        fahrenheit = BuildConfig.WEATHER_FAHRENHEIT,
        refreshMs = 30 * 60 * 1000L,
    )

    // The dock on first start, in order: whichever of these are installed, then
    // Settings. Afterwards the dock is edited on the TV (hold OK, or +).
    val defaultDock = listOf(
        // Car: navigation and music first.
        "ru.yandex.yandexnavi",
        "ru.yandex.yandexmaps",
        "ru.dublgis.dgismobile",                // 2GIS
        "com.google.android.apps.maps",
        "com.waze",
        "ru.yandex.music",
        "com.spotify.music",
        "com.google.android.apps.youtube.music",
        "org.videolan.vlc",
        "com.google.android.youtube.tv",
        "io.gh.reisxd.tizentube.cobalt",       // TizenTube, for when stock YouTube is off
        "com.netflix.ninja",
        "com.amazon.amazonvideo.livingroom",
        "com.disney.disneyplus",
        "com.wbd.stream",                       // Max
        "com.apple.atve.androidtv.appletv",
        "com.plexapp.android",
        "com.spotify.tv.android",
        "com.google.android.youtube.tvmusic",
        Apps.SETTINGS,
    )

    // The VPN and Kino widgets open the first of these that is installed, until
    // you pick another app (hold the widget).
    val vpnApps = listOf(
        "com.wireguard.android", "de.blinkt.openvpn", "net.openvpn.openvpn",
        "com.v2ray.ang", "app.hiddify.com", "com.github.kr328.clash", "org.amnezia.vpn",
        "com.happproxy", "com.protonvpn.android", "com.nordvpn.android",
        "com.expressvpn.vpn", "com.surfshark.vpnclient.android",
        "com.cloudflare.onedotonedotonedotone",
    )
    val kinoApps = listOf(
        "ru.kinopoisk", "ru.ivi.client", "ru.rt.video.app.tv", "ru.start.androidmobile",
        "ru.rutube.app", "com.netflix.ninja", "com.netflix.mediaclient",
        "com.google.android.youtube", "com.google.android.youtube.tv",
        "com.plexapp.android", "org.videolan.vlc",
    )

    data class Weather(
        val enabled: Boolean,
        val place: String,
        val latitude: Double,
        val longitude: Double,
        val timezone: String,
        val fahrenheit: Boolean,
        val refreshMs: Long,
    )
}
