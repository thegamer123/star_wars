package com.bouraoui.startwars.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object TimestampConverter  {


    fun String.getStringTimeStampWithDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
        val date = LocalDate.parse(this, formatter)
        val  output = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return date.format(output).toString()
    }

}