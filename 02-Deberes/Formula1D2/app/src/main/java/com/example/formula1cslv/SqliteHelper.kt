package com.example.formula1cslv
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.formula1cslv.entidades.Auto
import com.example.formula1cslv.entidades.Carrera

class SqliteHelper(
    context: Context? /* this */
) : SQLiteOpenHelper(context, "AndroidApp", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createGrandPrixTable = """
        CREATE TABLE GrandPrix(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombreCircuito TEXT NOT NULL,
            fecha DATE,
            longitudCarrera DECIMAL(10,2),
            latitud DECIMAL(10,6),  -- Añadir este campo
            longitud DECIMAL(10,6)  -- Añadir este campo
        );
    """.trimIndent()

        val createAutoTable = """
        CREATE TABLE Auto(
            numeroIdentificador INTEGER PRIMARY KEY AUTOINCREMENT,
            escuderia TEXT,
            tiempoTotal TEXT,
            puntosObtenidos INTEGER,
            penalizacion DECIMAL(10,2),
            piloto TEXT,
            identificadorGP INTEGER,
            FOREIGN KEY (identificadorGP) REFERENCES GrandPrix(identificadorGP) ON DELETE CASCADE
        );
    """.trimIndent()

        db?.execSQL(createGrandPrixTable)
        db?.execSQL(createAutoTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    fun getAllGrandPrixs(): ArrayList<Carrera> {
        val lectureDB = readableDatabase
        val queryScript = """
        SELECT * FROM GrandPrix
    """.trimIndent()
        val queryResult = lectureDB.rawQuery(
            queryScript,
            emptyArray()
        )
        val response = arrayListOf<Carrera>()
        if (queryResult.moveToFirst()) {
            do {
                response.add(
                    Carrera(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getString(2),
                        queryResult.getDouble(3),
                        queryResult.getDouble(4), // Leer latitud
                        queryResult.getDouble(5)  // Leer longitud
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()
        return response
    }
    fun getCarsByGrandPrix(identificadorGP: Int):ArrayList<Auto>{
        val lectureDB = readableDatabase
        val queryScript ="""
            SELECT * FROM Auto WHERE identificadorGP=?
        """.trimIndent()
        val queryResult = lectureDB.rawQuery(
            queryScript,
            arrayOf(identificadorGP.toString())
        )
        val response = arrayListOf<Auto>()

        if (queryResult.moveToFirst()){
            do {
                response.add(
                    Auto(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getString(2),
                        queryResult.getInt(3),
                        queryResult.getDouble(4),
                        queryResult.getString(5),
                    )
                )
            }while (queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()
        return response
    }

    fun createGrandPrix(
        nombreCircuito: String,
        fecha: String,
        longitudCarrera: Double,
        latitud: Double,      // Añadir este parámetro
        longitud: Double      // Añadir este parámetro
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToStore = ContentValues()
        valuesToStore.put("nombreCircuito", nombreCircuito)
        valuesToStore.put("fecha", fecha)
        valuesToStore.put("longitudCarrera", longitudCarrera)
        valuesToStore.put("latitud", latitud)        // Añadir este valor
        valuesToStore.put("longitud", longitud)      // Añadir este valor

        val storeResult = writeDB.insert(
            "GrandPrix",
            null,
            valuesToStore
        )
        writeDB.close()

        return storeResult.toInt() != -1
    }
    fun createCar(
        escuderia:String,
        tiempoTotal:String,
        puntosObtenidos:Int,
        penalizacion:Double,
        piloto:String,
        identificadorGP: Int
    ):Boolean{
        val writeDB = writableDatabase
        val valuesToStore = ContentValues()
        valuesToStore.put("escuderia",escuderia)
        valuesToStore.put("tiempoTotal",tiempoTotal)
        valuesToStore.put("puntosObtenidos",puntosObtenidos)
        valuesToStore.put("penalizacion",penalizacion)
        valuesToStore.put("piloto",piloto)
        valuesToStore.put("identificadorGP",identificadorGP)

        val storeResult = writeDB.insert(
            "Auto",
            null,
            valuesToStore
        )
        writeDB.close()

        return storeResult.toInt() !=-1
    }

    fun updateGrandPrix(
        id: Int,
        fecha: String,
    ): Boolean {
        print("${id} ${fecha}")
        val writeDB = writableDatabase
        val valuesToUpdate = ContentValues()
        valuesToUpdate.put("fecha", fecha)

        val parametersUpdateQuery = arrayOf(id.toString())
        val updateResult = writeDB.update(
            "GrandPrix", // Table name
            valuesToUpdate,
            "id=?",
            parametersUpdateQuery
        )
        writeDB.close()

        return updateResult != -1
    }

    //TODO
    fun updateCar(
        id: Int,
        tiempoTotal:String,
        puntosObtenidos: Int,
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToUpdate = ContentValues()
        valuesToUpdate.put("tiempoTotal", tiempoTotal)
        valuesToUpdate.put("puntosObtenidos", puntosObtenidos)

        val parametersUpdateQuery = arrayOf(id.toString())
        val updateResult = writeDB.update(
            "Auto", // Table name
            valuesToUpdate,
            "numeroIdentificador=?",
            parametersUpdateQuery
        )
        writeDB.close()

        return updateResult != -1
    }

    fun deleteGrandPrix(id:Int): Boolean {
        val writeDB = writableDatabase
        // SQL query example: where .... ID=? AND NAME=? [?=1, ?=2]
        val parametersDeleteQuery = arrayOf(id.toString())
        val deleteResult = writeDB.delete(
            "GrandPrix",
            "id=?",
            parametersDeleteQuery
        )
        writeDB.close()

        return deleteResult != -1
    }

    fun deleteCar(id:Int): Boolean {
        val writeDB = writableDatabase
        // SQL query example: where .... ID=? AND NAME=? [?=1, ?=2]
        val parametersDeleteQuery = arrayOf(id.toString())
        val deleteResult = writeDB.delete(
            "Auto",
            "numeroIdentificador=?",
            parametersDeleteQuery
        )
        writeDB.close()

        return deleteResult != -1
    }

}