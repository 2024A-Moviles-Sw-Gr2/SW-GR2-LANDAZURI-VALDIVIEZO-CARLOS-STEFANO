import Auto.Companion.autos
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter


class GrandPrix(private val identificadorGP:Int,
                private var autos: Array<Auto>,
                private val nombreCircuito: String,
                private val fechaGP: Fecha,
                private val longitudCarrera: Double,) {


    companion object {

        private val directorio = "C:\\Users\\sland\\OneDrive\\Documents\\App-Moviles\\SW-GR2-LANDAZURI-VALDIVIEZO-CARLOS-STEFANO\\00-Kotlin\\Formula1\\src\\main\\resources"
        private val rutaCompleta = File(directorio, "GrandPrix.json")
        private val gson = Gson()
        var grandPrixs:Array<GrandPrix> = emptyArray()

        fun crear(gp:GrandPrix){
            grandPrixs = grandPrixs.plus(gp)
            guardar()
        }
        fun leer(){
            if (rutaCompleta.exists()){
                val jsonString = rutaCompleta.readText()
                val dataType = object : TypeToken<Array<GrandPrix>>() {}.type
                grandPrixs = gson.fromJson(jsonString,dataType)
            }
        }

        fun actualizar(grandPrix: GrandPrix){
            leer()
            val indice = grandPrixs.indexOfFirst { it.identificadorGP==grandPrix.identificadorGP }
            if(indice!=-1){
                grandPrixs[indice] = grandPrix
                guardar()
            }else{
                println("GP no encontrado")
            }
        }
        fun eliminar(identificadorGP: Int) {

            leer()
            val indice = grandPrixs.indexOfFirst { it.identificadorGP==identificadorGP }
            if(indice!=-1){
                grandPrixs = grandPrixs.filterIndexed { i, _ -> i != indice }.toTypedArray()
                guardar()
            }else{
                println("GP no encontrado")
            }

        }


        fun guardar() {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val json = gson.toJson(grandPrixs)
            rutaCompleta.writeText(json)
            println("El archivo JSON se guardó en: $rutaCompleta")
        }
    }

    init {
        this.identificadorGP
        this.autos
        this.nombreCircuito
        this.fechaGP
        this.longitudCarrera
    }

    override fun toString(): String {
        return "GrandPrix(identificadorGP=$identificadorGP, autos=${autos.contentToString()}, nombreCircuito='$nombreCircuito', fechaGP=$fechaGP, longitudCarrera=$longitudCarrera) km"
    }


}