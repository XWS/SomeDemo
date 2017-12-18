package com.example.aidlproject

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by shiji-xc on 2017/11/21.
 */

class Book protected constructor(`in`: Parcel) : Parcelable {

    var bookName: String? = null
    var price: Int = 0

    init {
        bookName = `in`.readString()
        price = `in`.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(bookName)
        dest.writeInt(price)
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    fun readFromParcel(dest: Parcel) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        bookName = dest.readString()
        price = dest.readInt()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Book> = object : Parcelable.Creator<Book> {
            override fun createFromParcel(`in`: Parcel): Book {
                return Book(`in`)
            }

            override fun newArray(size: Int): Array<Book?> {
                return arrayOfNulls(size)
            }
        }
    }
}
