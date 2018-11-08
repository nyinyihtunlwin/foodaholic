package com.nyinyihtunlwin.projects.foodaholic.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.nyinyihtunlwin.projects.foodaholic.data.db.dao.CategoryDao
import com.nyinyihtunlwin.projects.foodaholic.data.db.dao.MealDao
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel


@Database(
    entities = [CategoryModel::class, MealModel::class], version = 1, exportSchema = false
)
abstract class FoodaholicDB : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao

    companion object {

        private val DB_NAME = "Foodaholic.DB"
        private var INSTANCE: FoodaholicDB? = null

        fun getDatabase(context: Context): FoodaholicDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, FoodaholicDB::class.java, DB_NAME)
                    .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                    .build()
            }
            return INSTANCE!!
        }
    }
}