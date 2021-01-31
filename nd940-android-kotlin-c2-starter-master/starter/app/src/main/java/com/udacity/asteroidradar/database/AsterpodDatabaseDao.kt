
package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Defines methods for using the SleepNight class with Room.
 */
@Dao
interface AsteroidDatabaseDao {

    @Insert
    suspend fun insert(asteroid: Asteroid)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param night new value to write
     */
    @Update
    suspend fun update(asteroid: Asteroid)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from asteroid_table WHERE id = :key")
    suspend fun get(key: Long): Asteroid?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM asteroid_table")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM asteroid_table ORDER BY id DESC")
    fun getAllAsteroids(): LiveData<List<Asteroid>>

    /**
     * Selects and returns the latest night.
     */
    @Query("SELECT * FROM asteroid_table ORDER BY id DESC LIMIT 1")
    suspend fun getAsteroid(): Asteroid?

    /**
     * Selects and returns the night with given nightId.
     */
    @Query("SELECT * from asteroid_table WHERE id = :key")
    fun getAsteroidWithId(key: Long): LiveData<Asteroid>
}

