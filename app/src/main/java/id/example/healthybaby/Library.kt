package id.example.healthybaby

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

fun String.capitalized(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}

fun getTimeNow(): Int {
    val c = Calendar.getInstance()
    return c[Calendar.HOUR_OF_DAY]
}

fun getYear(): String {
    val dateFormat: DateFormat = SimpleDateFormat("yyyy")
    val date = Date()
    return dateFormat.format(date)
}

fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
    return Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    ).years
}
