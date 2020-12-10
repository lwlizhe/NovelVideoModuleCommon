package com.lwlizhe.moudle.common.base.utils

import android.text.InputFilter
import android.text.Spanned
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern
import kotlin.experimental.and

/**
 * Created by zhiyicx on 2016/3/16.
 */
object CharactorHandler {
    val emojiFilter: InputFilter = object : InputFilter {
        //emoji过滤器
        var emoji = Pattern.compile(
            "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE or Pattern.CASE_INSENSITIVE
        )

        override fun filter(
            source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int,
            dend: Int
        ): CharSequence? {
            val emojiMatcher = emoji.matcher(source)
            return if (emojiMatcher.find()) {
                ""
            } else null
        }
    }

    /**
     * json 格式化
     * @param bodyString
     * @return
     */
    fun jsonFormat(bodyString: String): String {
        val message: String
        message = try {
            if (bodyString.startsWith("{")) {
                val jsonObject = JSONObject(bodyString)
                jsonObject.toString(4)
            } else if (bodyString.startsWith("[")) {
                val jsonArray = JSONArray(bodyString)
                jsonArray.toString(4)
            } else {
                bodyString
            }
        } catch (e: JSONException) {
            bodyString
        }
        return message
    }
}