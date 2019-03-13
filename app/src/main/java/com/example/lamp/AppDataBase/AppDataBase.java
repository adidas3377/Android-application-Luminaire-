package com.example.lamp.AppDataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MyDao myDao();


}