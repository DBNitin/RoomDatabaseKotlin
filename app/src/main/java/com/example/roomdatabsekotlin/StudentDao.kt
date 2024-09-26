package com.example.roomdatabsekotlin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(studentTable: StudentTable)
    @Query("select * from StudentTable")
     fun getAllStudent():List<StudentTable>
     @Delete
     fun deleteStudent(studentTable: StudentTable)

}