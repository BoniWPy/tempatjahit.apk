/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 */
package com.darsh.multipleimageselect.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Image
implements Parcelable {
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>(){

        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        public Image[] newArray(int n) {
            return new Image[n];
        }
    };
    public long id;
    public boolean isSelected;
    public String name;
    public String path;

    public Image(long l, String string2, String string3, boolean bl) {
        this.id = l;
        this.name = string2;
        this.path = string3;
        this.isSelected = bl;
    }

    private Image(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.path = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeLong(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.path);
    }

}

