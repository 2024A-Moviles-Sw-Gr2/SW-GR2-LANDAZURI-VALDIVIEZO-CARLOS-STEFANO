package com.example.proyecto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.proyecto.modelo.Comentario
import com.example.proyecto.modelo.Foro
import com.example.proyecto.modelo.Publicacion

class SQLiteHelper(context: Context?) : SQLiteOpenHelper(context, "AndroidApp", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createForoTable = """
    CREATE TABLE Foro (
        id_foro INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre TEXT NOT NULL,
        descripcion TEXT NOT NULL
    );
""".trimIndent()

        val createPublicacionTable = """
    CREATE TABLE Publicacion (
        id_publicacion INTEGER PRIMARY KEY AUTOINCREMENT,
        id_foro INTEGER NOT NULL,
        titulo TEXT NOT NULL,
        contenido TEXT NOT NULL,
        fecha DATE NOT NULL,
        likes INTEGER DEFAULT 0,
        FOREIGN KEY (id_foro) REFERENCES Foro(id_foro)
    );
""".trimIndent()

        val createComentarioTable = """
    CREATE TABLE Comentario (
    id_comentario INTEGER PRIMARY KEY AUTOINCREMENT,
    id_publicacion INTEGER NOT NULL,
    contenido TEXT NOT NULL,
    fecha DATE NOT NULL,
    likes INTEGER DEFAULT 0,
    id_comentario_padre INTEGER,
    FOREIGN KEY (id_publicacion) REFERENCES Publicacion(id_publicacion),
    FOREIGN KEY (id_comentario_padre) REFERENCES Comentario(id_comentario)
);
""".trimIndent()

        db?.execSQL(createComentarioTable)
        db?.execSQL(createForoTable)
        db?.execSQL(createPublicacionTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getAllForos(): ArrayList<Foro> {
        val db = readableDatabase
        val queryScript = """
        SELECT * FROM Foro
    """.trimIndent()
        val queryResult = db.rawQuery(queryScript, null)
        val response = arrayListOf<Foro>()
        if (queryResult.moveToFirst()) {
            do {
                response.add(
                    Foro(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getString(2)
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        db.close()
        return response
    }

    fun insertForo(nombre: String, descripcion: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("descripcion", descripcion)
        }
        val result = db.insert("Foro", null, values)
        db.close()
        return result
    }

    fun updateForo(idForo: Int, nombre: String, descripcion: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("descripcion", descripcion)
        }
        val result = db.update("Foro", values, "id_foro = ?", arrayOf(idForo.toString()))
        db.close()
        return result
    }

    fun deleteForo(idForo: Int): Int {
        val db = writableDatabase
        val result = db.delete("Foro", "id_foro = ?", arrayOf(idForo.toString()))
        db.close()
        return result
    }

    fun getAllPublicaciones(): ArrayList<Publicacion> {
        val db = readableDatabase
        val queryScript = """
        SELECT * FROM Publicacion
    """.trimIndent()
        val queryResult = db.rawQuery(queryScript, null)
        val response = arrayListOf<Publicacion>()
        if (queryResult.moveToFirst()) {
            do {
                response.add(
                    Publicacion(
                        queryResult.getInt(0),
                        queryResult.getInt(1),
                        queryResult.getString(2),
                        queryResult.getString(3),
                        queryResult.getString(4),
                        queryResult.getInt(5)
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        db.close()
        return response
    }

    fun insertPublicacion(idForo: Int, titulo: String, contenido: String, fecha: String, likes: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_foro", idForo)
            put("titulo", titulo)
            put("contenido", contenido)
            put("fecha", fecha)
            put("likes", likes)
        }
        val result = db.insert("Publicacion", null, values)
        db.close()
        return result
    }

    fun updatePublicacion(idPublicacion: Int, idForo: Int, titulo: String, contenido: String, fecha: String, likes: Int): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_foro", idForo)
            put("titulo", titulo)
            put("contenido", contenido)
            put("fecha", fecha)
            put("likes", likes)
        }
        val result = db.update("Publicacion", values, "id_publicacion = ?", arrayOf(idPublicacion.toString()))
        db.close()
        return result
    }

    fun deletePublicacion(idPublicacion: Int): Int {
        val db = writableDatabase
        val result = db.delete("Publicacion", "id_publicacion = ?", arrayOf(idPublicacion.toString()))
        db.close()
        return result
    }
    fun getAllComentarios(): ArrayList<Comentario> {
        val db = readableDatabase
        val queryScript = """
        SELECT * FROM Comentario
    """.trimIndent()
        val queryResult = db.rawQuery(queryScript, null)
        val response = arrayListOf<Comentario>()
        if (queryResult.moveToFirst()) {
            do {
                response.add(
                    Comentario(
                        queryResult.getInt(0),
                        queryResult.getInt(1),
                        queryResult.getString(2),
                        queryResult.getString(3),
                        queryResult.getInt(4)
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        db.close()
        return response
    }

    fun insertComentario(idPublicacion: Int, contenido: String, fecha: String, likes: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_publicacion", idPublicacion)
            put("contenido", contenido)
            put("fecha", fecha)
            put("likes", likes)
        }
        val result = db.insert("Comentario", null, values)
        db.close()
        return result
    }
    fun insertRespuesta(idPublicacion: Int, contenido: String, fecha: String, likes: Int,id_comentario_padre:Int):Long{
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_publicacion", idPublicacion)
            put("contenido", contenido)
            put("fecha", fecha)
            put("likes", likes)
            put("id_comentario_padre",id_comentario_padre)
        }
        val result = db.insert("Comentario", null, values)
        db.close()
        return result
    }

    fun updateComentario(idComentario: Int, idPublicacion: Int, contenido: String, fecha: String, likes: Int): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_publicacion", idPublicacion)
            put("contenido", contenido)
            put("fecha", fecha)
            put("likes", likes)
        }
        val result = db.update("Comentario", values, "id_comentario = ?", arrayOf(idComentario.toString()))
        db.close()
        return result
    }

    fun deleteComentario(idComentario: Int): Int {
        val db = writableDatabase
        val result = db.delete("Comentario", "id_comentario = ?", arrayOf(idComentario.toString()))
        db.close()
        return result
    }

    fun updateRespuesta(idComentario: Int, idPublicacion: Int, contenido: String, fecha: String, likes: Int, idComentarioPadre: Int): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_publicacion", idPublicacion)
            put("contenido", contenido)
            put("fecha", fecha)
            put("likes", likes)
            put("id_comentario_padre", idComentarioPadre)
        }
        val result = db.update("Comentario", values, "id_comentario = ?", arrayOf(idComentario.toString()))
        db.close()
        return result
    }
    fun deleteRespuesta(idComentario: Int): Int {
        val db = writableDatabase
        val result = db.delete("Comentario", "id_comentario = ?", arrayOf(idComentario.toString()))
        db.close()
        return result
    }




}