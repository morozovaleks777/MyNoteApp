package com.example.mynoteapp.data.database


import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_tbl ")
     fun getDbNotes(): Flow<  List<DbNote>>

     @Query("SELECT * from notes_tbl where id=:id")
   suspend  fun getDbNoteById(id:String):DbNote

     @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend  fun insert(dbNote: DbNote)

     @Update(onConflict = OnConflictStrategy.REPLACE)
   suspend  fun update(dbNote: DbNote)

     @Delete
    suspend fun deleteDbNote(dbNote: DbNote)

     @Query("DELETE  from notes_tbl ")
     suspend fun deleteAll()

}