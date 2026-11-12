package com.glasshome.tv

import android.content.ContentUris
import android.content.Context
import android.provider.CalendarContract

/** The next events of the device's calendar (needs the calendar permission). */
object CalendarEvents {
    class Event(val title: String, val begin: Long, val allDay: Boolean)

    /** Events starting or running in the next two weeks, soonest first. Slow: call off the main thread. */
    fun upcoming(context: Context, limit: Int = 3): List<Event> {
        val now = System.currentTimeMillis()
        val uri = CalendarContract.Instances.CONTENT_URI.buildUpon()
        ContentUris.appendId(uri, now)
        ContentUris.appendId(uri, now + 14L * 24 * 3600 * 1000)
        val out = ArrayList<Event>()
        // Many TV boxes have no calendar at all: then there is simply nothing to show.
        try {
            context.contentResolver.query(
                uri.build(),
                arrayOf(
                    CalendarContract.Instances.TITLE,
                    CalendarContract.Instances.BEGIN,
                    CalendarContract.Instances.ALL_DAY,
                ),
                null, null, CalendarContract.Instances.BEGIN + " ASC",
            )?.use { c ->
                while (c.moveToNext() && out.size < limit) {
                    out.add(Event(c.getString(0) ?: "", c.getLong(1), c.getInt(2) == 1))
                }
            }
        } catch (e: Exception) {
            // no provider, or permission taken away
        }
        return out
    }
}
