import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class City(
    val name: String,
    val timezone: TimeZone
)


val cities = listOf(
    City(
        "Gaza", "Asia/Gaza".toTimeZone
    ),
    City(
        "Cairo", "Africa/Cairo".toTimeZone
    ),
    City(
        "Mecca", "Asia/Riyadh".toTimeZone
    ),
    City(
        "Tokyo", "Asia/Tokyo".toTimeZone
    ),
    City(
        "New York", "America/New_York".toTimeZone
    ),
)

val TimeZone.dateTime: LocalDateTime
    get() {
        val now = Clock.System.now()
        return now.toLocalDateTime(this)
    }


fun City.dateTime(
    initial: Instant = Clock.System.now(),
    duration: Duration
): LocalDateTime {
    val after = initial.plus(duration)
    return after.toLocalDateTime(this.timezone)
}


val LocalDateTime.HH_mm_ss: String
    get() {
        return format(
            LocalDateTime.Format {
                hour()
                char(':')
                minute()
                char(':')
                second()

            }
        )
    }


val LocalDateTime.dMy: String
    get() {
        return format(
            LocalDateTime.Format {
                dayOfMonth()
                char('-')
                monthNumber()
                char('-')
                year()
            }
        )
    }

val City.currentDateTime: LocalDateTime
    get() = timezone.dateTime


private val String.toTimeZone
    get() = TimeZone.of(this)

