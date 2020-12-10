package com.lwlizhe.moudle.common.base.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import java.io.*

/**
 * Created by zhiyicx on 2016/3/15.
 */
object DataHelper {
    private var mSharedPreferences: SharedPreferences? = null
    const val SP_NAME = "config"

    /**
     * 存储重要信息到sharedPreferences；
     *
     * @param key
     * @param value
     */
    fun SetStringSF(
        context: Context,
        key: String?,
        value: String?
    ) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        mSharedPreferences!!.edit().putString(key, value).commit()
    }

    /**
     * 返回存在sharedPreferences的信息
     *
     * @param key
     * @return
     */
    fun getStringSF(context: Context, key: String?): String? {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        return mSharedPreferences!!.getString(key, null)
    }

    /**
     * 存储重要信息到sharedPreferences；
     *
     * @param key
     * @param value
     */
    fun SetIntergerSF(
        context: Context,
        key: String?,
        value: Int
    ) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        mSharedPreferences!!.edit().putInt(key, value).commit()
    }

    /**
     * 返回存在sharedPreferences的信息
     *
     * @param key
     * @return
     */
    fun getIntergerSF(context: Context, key: String?): Int {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        return mSharedPreferences!!.getInt(key, -1)
    }

    /**
     * 清除某个内容
     */
    fun removeSF(context: Context, key: String?) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        mSharedPreferences!!.edit().remove(key).commit()
    }

    /**
     * 清除Shareprefrence
     */
    fun clearShareprefrence(context: Context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        mSharedPreferences!!.edit().clear().commit()
    }

    /**
     * 将对象储存到sharepreference
     *
     * @param key
     * @param device
     * @param <T>
    </T> */
    fun <T> saveDeviceData(
        context: Context,
        key: String?,
        device: T
    ): Boolean {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        val baos = ByteArrayOutputStream()
        return try {   //Device为自定义类
            // 创建对象输出流，并封装字节流
            val oos = ObjectOutputStream(baos)
            // 将对象写入字节流
            oos.writeObject(device)
            // 将字节流编码成base64的字符串
            val oAuth_Base64 = String(
                Base64.encode(
                    baos
                        .toByteArray(), Base64.DEFAULT
                )
            )
            mSharedPreferences!!.edit().putString(key, oAuth_Base64).commit()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 将对象从shareprerence中取出来
     *
     * @param key
     * @param <T>
     * @return
    </T> */
    fun <T> getDeviceData(context: Context, key: String?): T? {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(
                SP_NAME,
                Context.MODE_PRIVATE
            )
        }
        var device: T? = null
        val productBase64 = mSharedPreferences!!.getString(key, null) ?: return null
        // 读取字节
        val base64 =
            Base64.decode(productBase64.toByteArray(), Base64.DEFAULT)

        // 封装到字节流
        val bais = ByteArrayInputStream(base64)
        try {
            // 再次封装
            val bis = ObjectInputStream(bais)

            // 读取对象
            device = bis.readObject() as T
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return device
    }

    /**
     * 使用递归获取目录文件大小
     *
     * @param dir
     * @return
     */
    fun getDirSize(dir: File?): Long {
        if (dir == null) {
            return 0
        }
        if (!dir.isDirectory) {
            return 0
        }
        var dirSize: Long = 0
        val files = dir.listFiles()
        for (file in files) {
            if (file.isFile) {
                dirSize += file.length()
            } else if (file.isDirectory) {
                dirSize += file.length()
                dirSize += getDirSize(file) // 递归调用继续统计
            }
        }
        return dirSize
    }

    /**
     * 使用递归删除文件夹
     *
     * @param dir
     * @return
     */
    fun DeleteDir(dir: File?): Boolean {
        if (dir == null) {
            return false
        }
        if (!dir.isDirectory) {
            return false
        }
        val files = dir.listFiles()
        for (file in files) {
            if (file.isFile) {
                file.delete()
            } else if (file.isDirectory) {
                DeleteDir(file) // 递归调用继续删除
            }
        }
        return true
    }

    @Throws(IOException::class)
    fun BytyToString(`in`: InputStream): String {
        val out = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var num = 0
        while (`in`.read(buf).also { num = it } != -1) {
            out.write(buf, 0, buf.size)
        }
        val result = out.toString()
        out.close()
        return result
    }
}