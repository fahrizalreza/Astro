package com.astro.test.rezafahrizal.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.astro.test.rezafahrizal.model.UserFavorite
import com.astro.test.rezafahrizal.model.UserLocal
import com.astro.test.rezafahrizal.utilities.AppConfig
import com.astro.test.rezafahrizal.utilities.ParentConverter

@Database(
    entities = [UserLocal::class, UserFavorite::class], version = AppConfig.DATABASE_VERSION, exportSchema = false
)
@TypeConverters(ParentConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun parametersDao(): ParametersDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, AppConfig.DATABASE_NAME
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}