package com.example.poin1;

import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "cheese_table")
public class Cheese {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "cheese")
    private String mCheese;

        public Cheese(String cheese) {this.mCheese = cheese;}

        public String getCheese(){return this.mCheese;}
    }
