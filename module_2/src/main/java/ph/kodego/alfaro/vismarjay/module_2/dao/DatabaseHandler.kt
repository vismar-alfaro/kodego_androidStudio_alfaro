package ph.kodego.alfaro.vismarjay.module_2.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context): SQLiteOpenHelper(context,DATABASENAME,null, DATABASEVERSION) {

    companion object{
        private val DATABASEVERSION = 1
        private val DATABASENAME = "studentdatabase"

        val tableStudents = "student_table"
        val studentId = "id"
        val studentFirstName = "firstname"
        val studentLastName = "lastname"
        val yearstarted = "year_started"
        val course = "course"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATESTUDENTSTABLE =
                "CREATE TABLE $tableStudents" +
                "($studentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$studentFirstName TEXT, " +
                "$studentLastName  TEXT)"
        db?.execSQL(CREATESTUDENTSTABLE)

        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Alfaro','Vismar Jay')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Lumantas','Mary Delight')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Adaro','Nikko')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Tome','James')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Gallego','Sanco')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Saludares','Norwway')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Bugas','Lorevic')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Saren','Ruthchel')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Suaybaguio','Regem')")
        db?.execSQL("Insert into $tableStudents ($studentLastName,$studentFirstName) values ('Tolleno','Richard')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion:Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableStudents")
        onCreate(db)
    }

}