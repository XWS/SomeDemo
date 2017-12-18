package com.example.aidlproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shiji-xc on 2017/11/21.
 */

public class Book implements Parcelable {

     private String bookName;
     private int price;

     protected Book(Parcel in) {
          bookName = in.readString();
          price = in.readInt();
     }

     public static final Creator<Book> CREATOR = new Creator<Book>() {
          @Override
          public Book createFromParcel(Parcel in) {
               return new Book(in);
          }

          @Override
          public Book[] newArray(int size) {
               return new Book[size];
          }
     };

     public String getBookName() {
          return bookName;
     }

     public void setBookName(String bookName) {
          this.bookName = bookName;
     }

     public int getPrice() {
          return price;
     }

     public void setPrice(int price) {
          this.price = price;
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
          dest.writeString(bookName);
          dest.writeInt(price);
     }

     /**
      * 参数是一个Parcel,用它来存储与传输数据
      * @param dest
      */
     public void readFromParcel(Parcel dest) {
          //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
          bookName = dest.readString();
          price = dest.readInt();
     }
}
