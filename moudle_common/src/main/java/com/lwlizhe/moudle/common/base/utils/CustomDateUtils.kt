package com.lwlizhe.moudle.common.base.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.text.TextUtils
import android.widget.TextView
import com.lwlizhe.moudle_common.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 62445 on 2017/6/10.
 */
object CustomDateUtils {
    private const val FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"
    private const val FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmmss"
    private const val FORMAT_MM_DD_HH_MM = "MM-dd HH:mm"
    private const val FORMAT_MM_DD_HH_MM_C = "MM月dd日 HH:mm"
    private const val FORMAT_DD = "dd"
    private const val FORMAT_MM = "MM"
    const val DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm"
    const val DATE_FORMAT = "yyyy年MM月dd日    HH:mm"
    const val DATE_FORMAT_YMDHMS_Z = "yyyy年MM月dd日  HH:mm:ss"
    const val DATE_FORMAT_DD = "dd"
    const val DATE_FORMAT_YMDHMSA = "yyyy-MM-dd hh:mm:ss a"
    const val DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_YMDHMA = "yyyy-MM-dd hh:mm a"
    const val DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm"
    const val DATE_FORMAT_YMD = "yyyy-MM-dd"
    const val DATE_FORMAT_HMA = "hh:mm a"
    const val DATE_FORMAT_HM = "HH:mm"
    const val DEFAULT_YEAR = 1970
    fun transformTimeStampToData(timeStamp: Int): String {
        val dateFormat = SimpleDateFormat("yyyy年MM月dd日")
        return dateFormat.format(Date(timeStamp * 1000L))
    }

    /**
     * 由秒得到转换之后的时间（分钟/小时/天）
     *
     * @param second
     * @return
     */
    fun getTimeFromMillisecond(
        mContext: Context,
        second: Long
    ): String {
        val now = System.currentTimeMillis() / 1000
        val createTime = now - second
        return if (createTime <= 0) {
            mContext.getString(R.string.label_now)
        } else {
            val minutes = createTime / 60 + 1
            if (minutes < 60) {
                minutes.toString() + mContext.getString(R.string.label_minites_before)
            } else {
                val hours = minutes / 60
                if (hours < 24) {
                    hours.toString() + mContext.getString(R.string.label_hours_before)
                } else {
                    val days = hours / 24
                    if (days < 30) {
                        days.toString()  + mContext.getString(R.string.label_day_before)
                    } else {
                        val month = days / 30
                        if (month < 12) {
                            month.toString()  + mContext.getString(R.string.label_month_before)
                        } else {
                            val year = month / 12
                            year.toString()  + mContext.getString(R.string.label_year_before)
                        }
                    }
                }
            }
        }
    }

    /**
     * 由秒得到转换之后的时间
     *
     * @param second
     * @return
     */
    fun getCreateTime(second: Long): String {
        val currentMilliseconds = System.currentTimeMillis()
        val currentCalendar = Calendar.getInstance()
        currentCalendar.timeInMillis = currentMilliseconds
        val currentYear = currentCalendar[Calendar.YEAR]
        val currentMonth = currentCalendar[Calendar.MONTH] + 1
        //
        val milliseconds = second * 1000
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1
        val day = calendar[Calendar.DAY_OF_MONTH]
        val mYear = year.toString() + "年"
        val mMonth = month.toString() + "月"
        val mDay = day.toString() + "日"
        val sb = StringBuffer()
        // 2014-7-14
        // 2014 != 2015
        if (year != currentYear) {
            sb.append(mYear)
            sb.append(mMonth)
            sb.append(mDay)
        } else {
            // 7 != 8
            if (month != currentMonth) {
                sb.append(mMonth)
                sb.append(mDay)
            } else {
                sb.append(mDay)
            }
        }
        return sb.toString()
    }

    /**
     * 由秒得到转换之后的支出时间
     *
     * @param second
     * @return
     */
    fun getExpenseTime(second: Long): String {
        //
        val milliseconds = second * 1000
        val date = Date(milliseconds)
        val formatter =
            SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM)
        formatter.format(date)
        val calendar = Calendar.getInstance()
        calendar.time = date
        val month = calendar[Calendar.MONTH] + 1
        val day = calendar[Calendar.DAY_OF_MONTH]
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]
        val mMonth = (if (month < 10) "0" else "") + month + "月"
        val mDay = (if (day < 10) "0" else "") + day + "日  "
        val mTime =
            (if (hour < 10) "0" else "") + hour + ":" + (if (minute < 10) "0" else "") + minute
        return mMonth + mDay + mTime
    }

    fun formatDate(dataStr: String?): String {
        val sdf =
            SimpleDateFormat(DATE_FORMAT_YMDHMS)
        return sdf.format(dataStr)
    }

    fun formatDate(dataStr: Date?): String {
        val sdf =
            SimpleDateFormat(DATE_FORMAT_YMDHMS)
        return sdf.format(dataStr)
    }

    fun formatDateMMDD(dataStr: Date?): String {
        val sdf =
            SimpleDateFormat(FORMAT_MM_DD_HH_MM_C)
        return sdf.format(dataStr)
    }

    fun formatDateMMDD(mContext: Context, dataStr: String): String {
        val time = dataStr.toInt()
        val sdf =
            SimpleDateFormat(FORMAT_MM_DD_HH_MM)
        val dateString = sdf.format(Date(time * 1000L))
        return dateString + mContext.getString(R.string.label_beijing_time)
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    val stringDate: String
        get() {
            val currentTime = Date()
            val formatter =
                SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM)
            return formatter.format(currentTime)
        }

    /**
     * 获取当前时间
     *
     * @return
     */
    val stringDate2: String
        get() {
            val currentTime = Date()
            val formatter =
                SimpleDateFormat(FORMAT_YYYYMMDDHHMM)
            return formatter.format(currentTime)
        }

    /**
     * 根据毫秒数得到当前月份
     *
     * @param millis
     * @return
     */
    fun getDay(millis: Long): String {
        val date = Date(millis)
        val sdf = SimpleDateFormat(FORMAT_DD)
        return sdf.format(date)
    }

    private var lastClickTime: Long = 0

    /**
     * 判断两次点击的间隔时间
     *
     * @return
     */
    val isFastDoubleClick: Boolean
        get() {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            if (0 < timeD && timeD < 2000) {
                return true
            }
            lastClickTime = time
            return false
        }

    /**
     * 转换为报表每个月title的日期
     * @param year
     * @param month
     */
    fun formatReportDate(year: String?, month: String?): String {
        val sbDate = StringBuilder()
        sbDate.append(year)
            .append("年")
            .append(month)
            .append("月")
        return sbDate.toString()
    }

    /**
     * date format to AM/PM format: a
     *
     * @param date
     * @return AM/PM
     */
    fun toAMPMString(date: Date?): String? {
        return try {
            val myFmt =
                SimpleDateFormat("a", Locale.US)
            myFmt.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun to12HourTimeString(date: Date?, displayAM: Boolean): String? {
        if (date == null) {
            return null
        }
        val time = to12HourTimeString(date)
        if (!displayAM) {
            return time
        }
        val am = toAMPMString(date)
        return "$time $am"
    }

    /**
     * date format to time for 24 hour format format: HH:mm ex. 08:04
     *
     * @param date
     * @return
     */
    fun to24HourTimeString(date: Date?): String? {
        return try {
            val myFmt =
                SimpleDateFormat("HH:mm", Locale.US)
            myFmt.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    /**
     * date format to time for 12 hour format format: hh:mm ex. 08:04
     *
     * @param date
     * @return
     */
    fun to12HourTimeString(date: Date?): String? {
        return try {
            val myFmt =
                SimpleDateFormat("hh:mm", Locale.US)
            myFmt.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun toTimeString(context: Context?, date: Date?): String? {
        val M12 = "h:mm"
        val M24 = "HH:mm"
        val formatTime =
            if (android.text.format.DateFormat.is24HourFormat(context)) M24 else M12
        return try {
            val myFmt =
                SimpleDateFormat(formatTime, Locale.US)
            myFmt.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    /**
     * date format to yyyy-MM-dd format ex. 2014-05-28
     *
     * @param date
     * @return
     */
    fun toDateString(date: Date?): String? {
        return try {
            val myFmt =
                SimpleDateFormat(DATE_FORMAT_YMD, Locale.US)
            myFmt.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    /**
     * date format to yyyy-MM-dd HH:mm:ss format ex. 2014-05-28
     *
     * @param date
     * @return
     */
    fun toDateTimeString(date: Date?): String? {
        return try {
            val myFmt =
                SimpleDateFormat(DATE_FORMAT_YMDHMS, Locale.US)
            myFmt.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun toDate(dateString: String): Date? {
        var dateString = dateString
        if (TextUtils.isEmpty(dateString)) {
            return null
        }
        val sdf: DateFormat
        dateString = dateString.trim { it <= ' ' }
        val tmpPos = dateString.indexOf("-")
        sdf = if (tmpPos > 0) {
            val space = dateString.split(" ").toTypedArray()
            if (dateString.length == 10 && space.size == 1) {
                SimpleDateFormat(DATE_FORMAT_YMD, Locale.US)
            } else if (dateString.length == 16 && space.size == 2) {
                SimpleDateFormat(
                    DATE_FORMAT_YMDHM,
                    Locale.US
                )
            } else if (dateString.length == 19 && space.size == 2) {
                SimpleDateFormat(
                    DATE_FORMAT_YMDHMS,
                    Locale.US
                )
            } else if (space.size == 3) {
                if (dateString.length == 19) {
                    SimpleDateFormat(
                        DATE_FORMAT_YMDHMA,
                        Locale.US
                    )
                } else if (dateString.length == 22) {
                    SimpleDateFormat(
                        DATE_FORMAT_YMDHMSA,
                        Locale.US
                    )
                } else {
                    return null
                }
            } else {
                return null
            }
        } else {
            if (dateString.length == 5) {
                SimpleDateFormat(DATE_FORMAT_HM, Locale.US)
            } else if (dateString.length == 7 || dateString.length == 8) {
                SimpleDateFormat(DATE_FORMAT_HMA, Locale.US)
            } else {
                return null
            }
        }
        var d: Date? = null
        try {
            d = sdf.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return d
    }

    /**
     * date format to date for locale format
     *
     * @param date
     * @return
     */
    fun toDateLocaleString(date: Date?): String? {
        return try {
            val ddf =
                DateFormat.getDateInstance(DateFormat.MEDIUM)
            ddf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    /**
     * date format to time for locale
     *
     * @param date
     * @return
     */
    fun toTimeLocaleString(
        context: Context?,
        date: Date?
    ): String? {
        return try {
            val dtf = android.text.format.DateFormat.getTimeFormat(context)
            dtf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    /**
     * date format to datetime for locale
     *
     * @param date
     * @return
     */
    fun toDateTimeLocaleString(
        context: Context?,
        date: Date?
    ): String? {
        return try {
            val ddf =
                DateFormat.getDateInstance(DateFormat.MEDIUM)
            val dtf = android.text.format.DateFormat.getTimeFormat(context)
            ddf.format(date) + " " + dtf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun toDateTime(
        date: Date?,
        time12Hour: String,
        am: String?
    ): Date? {
        var time12Hour = time12Hour
        var am = am
        val dateString = toDateString(date)
        if (TextUtils.isEmpty(time12Hour)) {
            time12Hour = "00:00:00"
            am = "AM"
        }
        if (TextUtils.isEmpty(am)) {
            am = ""
        }
        val ts = time12Hour.split(":").toTypedArray()
        if (ts.size > 0 && ts[0].length == 1) {
            val hour = "0" + ts[0]
            ts[0] = hour
            time12Hour = TextUtils.join(":", ts)
        }
        val dateTimeString =
            String.format("%1\$s %2\$s %3\$s", dateString, time12Hour, am)
        return toDate(dateTimeString)
    }

    fun toDateTimeLocaleString(
        context: Context?,
        date: Date?,
        time: String,
        am: String?
    ): String? {
        val dt = toDateTime(date, time, am)
        return if (dt != null) {
            if (TextUtils.isEmpty(time)) {
                toDateLocaleString(dt)
            } else {
                toDateTimeLocaleString(context, dt)
            }
        } else null
    }

    fun getDate(
        context: Context?,
        v: TextView,
        calendar: Calendar,
        title: String?
    ) {
        val datePickerDialog = DatePickerDialog(
            context!!,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate =
                    setDate(year, monthOfYear + 1, dayOfMonth)
                v.text = toDateLocaleString(selectedDate)
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = monthOfYear
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.setTitle(title)
        datePickerDialog.show()
    }

    /**
     * 根据指定格式获取指定时间
     */
    @SuppressLint("SimpleDateFormat")
    fun getDate(timeMillis: Long, formatString: String?): String {
        val formatter = SimpleDateFormat(formatString)
        val curDate = Date(timeMillis)
        return formatter.format(curDate)
    }

    /**
     * set date
     *
     * @param year
     * , monthOfYear,dayOfMonth
     * @return
     */
    fun setDate(year: Int, monthOfYear: Int, dayOfMonth: Int): Date? {
        val date =
            year.toString() + "-" + (if (monthOfYear < 10) "0$monthOfYear" else monthOfYear) + "-" + if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
        return toDate(date)
    }

    /**
     * set datetime
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    fun setDateTime(
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        minute: Int,
        second: Int
    ): Date? {
        val datetime =
            (year.toString() + "-" + (if (month < 10) "0$month" else month) + "-" + (if (day < 10) "0$day" else day) + "-" + (if (hour < 10) "0$hour" else hour) + "-"
                    + (if (minute < 10) "0$minute" else minute) + "-" + if (second < 10) "0$second" else second)
        return toDate(datetime)
    }

    /**
     * set time
     *
     * @param hour
     * @param minute
     * @param second
     * @return 1970-01-01 time
     */
    fun setTime(hour: Int, minute: Int, second: Int): Date {
        val cal = Calendar.getInstance()
        cal[DEFAULT_YEAR, 0, 1, hour, minute] = second
        cal[Calendar.MILLISECOND] = 0
        return cal.time
    }

    fun toTime(timeString: String): Date? {
        if (!TextUtils.isEmpty(timeString)) {
            val time = toDate(timeString)
            var hour: Int? = null
            var minute = 0
            if (time != null) {
                val cal = Calendar.getInstance()
                cal.time = time
                hour = cal[Calendar.HOUR_OF_DAY]
                minute = cal[Calendar.MINUTE]
            } else {
                val hourMinute = timeString.split(":").toTypedArray()
                if (hourMinute.size == 2) {
                    hour = hourMinute[0].toInt()
                    minute = hourMinute[1].toInt()
                }
            }
            if (hour != null && minute != null) {
                return setTime(hour, minute, 0)
            }
        }
        return null
    }

    /**
     * add datetime
     *
     * @param date
     * @param minute
     * @return
     */
    fun addMinutes(date: Date?, minute: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MINUTE, minute)
        return cal.time
    }

    /**
     * get hour
     *
     * @param minute
     * minute
     * @return 1.5 = 1 hour, 30 minute
     */
    fun toHours(minute: Int): Double {
        return String.format("%.2f", minute / 60.0).toDouble()
    }

    /**
     * get duration for time
     *
     * @param date
     * @return duration Minutes
     */
    fun toDiffMinutes(date: Date): Int {
        val cal = Calendar.getInstance()
        cal.time = date
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return toMinute(date.time - cal.timeInMillis)
    }

    /**
     * get duration for time
     *
     * @param minutes
     * @return hour:minute
     */
    fun toHourMinuteString(minutes: Int?): String? {
        if (minutes == null) {
            return null
        }
        val second = minutes * 60.toLong()
        val hour = second % (24 * 3600) / 3600
        val minute = second % 3600 / 60
        return String.format(
            "%1\$s:%2\$s",
            if (hour < 10) "0$hour" else hour,
            if (minute < 10) "0$minute" else minute
        )
    }

    /**
     * Convert hours to minutes
     *
     * @param hours
     * 1.5h
     * @return 90min
     */
    fun toMinute(hours: Double?): Int {
        return if (hours == null) {
            0
        } else Math.round(hours.toFloat() * 60)
    }

    /**
     * get minutes from duration
     *
     * milliseconds
     * @return minutes
     */
    fun toMinute(milliseconds: Long): Int {
        val sencond = milliseconds / 1000
        return sencond.toInt() / 60
    }

    /**
     * get time difference
     *
     * @param befor
     * @param after
     * @return
     */
    fun getDiffMinutes(befor: Date?, after: Date?): Int {
        var befor = befor
        var after = after
        if (befor == null) {
            befor = Date()
        }
        if (after == null) {
            after = Date()
        }
        val diffMilliseconds = after.time - befor.time
        return toMinute(diffMilliseconds)
    }

    /**
     * the same date
     *
     * @param leftDate
     * @param rightDate
     * @return
     */
    fun isSameDate(leftDate: Date?, rightDate: Date?): Boolean {
        val cal = Calendar.getInstance()
        cal.time = leftDate
        val leftDay = cal[Calendar.DATE]
        cal.time = rightDate
        val rightDay = cal[Calendar.DATE]
        return leftDay == rightDay
    }

    /**
     * time overlap
     *
     * @param s1
     * @param e1
     * @param s2
     * @param e2
     * @return
     */
    fun isOverLaps(
        s1: Date?,
        e1: Date?,
        s2: Date?,
        e2: Date?
    ): Boolean {
        return if (s1 == null || e1 == null || s2 == null || e2 == null) {
            false
        } else e2.time > s1.time && s2.time < e1.time
    }

    fun equalDate(left: Date?, right: Date?): Boolean {
        if (left == null || right == null) {
            return false
        }
        val calLeft = Calendar.getInstance()
        val calRight = Calendar.getInstance()
        setDateMillisecondToZero(left, right, calLeft, calRight)
        return calLeft.timeInMillis == calRight.timeInMillis
    }

    fun beforDate(left: Date?, right: Date?): Boolean {
        if (left == null) {
            return false
        }
        if (right == null) {
            return true
        }
        val calLeft = Calendar.getInstance()
        val calRight = Calendar.getInstance()
        setDateMillisecondToZero(left, right, calLeft, calRight)
        return calLeft.before(calRight)
    }

    fun afterDate(left: Date?, right: Date?): Boolean {
        if (left == null) {
            return true
        }
        if (right == null) {
            return false
        }
        val calLeft = Calendar.getInstance()
        val calRight = Calendar.getInstance()
        setDateMillisecondToZero(left, right, calLeft, calRight)
        return calLeft.after(calRight)
    }

    private fun setDateMillisecondToZero(
        left: Date,
        right: Date,
        calLeft: Calendar,
        calRight: Calendar
    ) {
        calLeft.time = left
        calLeft[Calendar.MILLISECOND] = 0
        calRight.time = right
        calRight[Calendar.MILLISECOND] = 0
    }

    /**
     * 根据指定格式获取时间
     */
    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(formatString: String?): String {
        val formatter = SimpleDateFormat(formatString)
        val curDate = Date(System.currentTimeMillis())
        return formatter.format(curDate)
    }

    /**
     * 获取格式为：yyyy年MM月dd日 HH:mm:ss 的当前时间
     */
    @get:SuppressLint("SimpleDateFormat")
    val currentDate: String
        get() {
            val formatter =
                SimpleDateFormat(DATE_FORMAT)
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    /**
     * 获取格式为：yyyy年MM月dd日的当前时间
     */
    @get:SuppressLint("SimpleDateFormat")
    val today: String
        get() {
            val formatter =
                SimpleDateFormat(DATE_FORMAT_DD)
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    /**
     * 获取时间秒数，精确到分
     *
     * @param dateString
     * 201508081025
     * @return
     */
    fun getSecond(dateString: String?): Long {
        val sdf =
            SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMM)
        var millionSeconds: Long = 0
        try {
            millionSeconds = sdf.parse(dateString).time
        } catch (e1: ParseException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        } // 毫秒
        val c = Calendar.getInstance()
        try {
            c.time = SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMM)
                .parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return c.timeInMillis / 1000
    }
}