package com.lwlizhe.moudle.common.base.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by lwlizhe on 2017/7/17.
 */
object MD5Util {
    /**
     * 32位MD5加密方法
     * 16位小写加密只需getMd5Value("xxx").substring(8, 24);即可
     *
     * @param sSecret
     * @return
     */
    fun getMd5Value(sSecret: String): String {
        try {
            val bmd5 = MessageDigest.getInstance("MD5")
            bmd5.update(sSecret.toByteArray())
            var i: Int
            val buf = StringBuffer()
            val b = bmd5.digest() // 加密
            for (offset in b.indices) {
                i = b[offset].toInt()
                if (i < 0) i += 256
                if (i < 16) buf.append("0")
                buf.append(Integer.toHexString(i))
            }
            return buf.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}