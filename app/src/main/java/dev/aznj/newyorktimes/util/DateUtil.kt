package dev.aznj.newyorktimes.util

import java.text.SimpleDateFormat
import java.util.Locale

fun reFormatDate(inputDateFormat: String, outputDateFormat: String, inputDate: String): String {
    val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
    val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
    val mParsedDate = mInputDateFormat.parse(inputDate)
    return mOutputDateFormat.format(mParsedDate!!)
}