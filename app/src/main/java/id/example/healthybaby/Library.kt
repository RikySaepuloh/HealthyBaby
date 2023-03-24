package id.example.healthybaby

import android.os.Build
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
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

fun convertDate(value: String): String {
    val newdate: Date = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(value)
    return SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("in_ID")).format(newdate)
}

fun dateTodayyymmdd(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}

fun dateTodaddmmyyy(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
}

fun getMonthFromBirthdate(tanggalLahir: String?): Int {
    val monthsBetween = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(convertDate(tanggalLahir.toString()))),
        YearMonth.from(LocalDate.parse(dateTodayyymmdd()))
        )
    } else {
    }

    return monthsBetween.toString().toInt()
}

fun getAge(dobString: String): Int {
    var date: Date? = null
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    try {
        date = sdf.parse(dobString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    if (date == null) return 0
    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()
    dob.time = date
    val year = dob[Calendar.YEAR]
    val month = dob[Calendar.MONTH]
    val day = dob[Calendar.DAY_OF_MONTH]
    dob[year, month + 1] = day
    var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
    if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
        age--
    }
    return age
}