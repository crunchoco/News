package com.example.news.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.news.models.Source

class Converters {

    /*
    fromSource
    將 Source 對象轉換為 String。在這裡，我們返回 Source 對象的 name 屬性。
    將複雜類型轉換為可以直接存儲在資料庫中的基本類型。
     */

    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.name
    }

    /*
    將 String 轉換回 Source 對象。我們使用 name 字符串創建並返回一個新的 Source 對象。
    資料庫中讀取數據時，將基本類型轉換回自定義類型。
     */

    @TypeConverter
    fun toSource(name: String?): Source? {
        return if (name != null) Source(name, name) else null
    }
}