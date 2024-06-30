import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

class Auto(private val numeroIdentificador:Int,
           private val escuderia:String,
           private val participa:Boolean,
           private val tiempoTotal: Tiempo,
           private val puntosObtendidos: Int,
           private val penalizacion:Double?,) {

    companion object {
        val directorio = "C:\\Users\\sland\\OneDrive\\Documents\\App-Moviles\\SW-GR2-LANDAZURI-VALDIVIEZO-CARLOS-STEFANO\\00-Kotlin\\Formula1\\src\\main\\resources"
        val rutaCompleta = File(directorio, "Autos.json")
        private val gson = Gson()

        var autos: Array<Auto> = emptyArray()

        fun crear(auto: Auto) {
            autos = autos.plus(auto)
            guardar()
        }
        fun leer() {
            if (rutaCompleta.exists()) {
                val jsonString = rutaCompleta.readText()
                val dataType = object : TypeToken<Array<Auto>>() {}.type
                autos = gson.fromJson(jsonString, dataType)
            }
        }
        fun actualizar(auto: Auto){
            leer()
            val indice = autos.indexOfFirst { it.numeroIdentificador==auto.numeroIdentificador }
            if(indice!=-1){
                autos[indice] = auto
                guardar()
            }else{
                println("auto no encontrado")
            }
        }
        fun eliminar(numeroIdentificador: Int) {
            leer()
            val indice = autos.indexOfFirst { it.numeroIdentificador==numeroIdentificador }
            if(indice!=-1){
                autos = autos.filterIndexed { i, _ -> i != indice }.toTypedArray()
                guardar()
            }else{
                println("auto no encontrado")
            }

        }


        fun guardar() {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val json = gson.toJson(autos)
            rutaCompleta.writeText(json)
            println("El archivo JSON se guardó en: $rutaCompleta")
        }



    }
    class Tiempo(val horas:Int?,
                 val minutos: Int?,
                 val segundos: Double,) {
        init {
            this.horas
            this.minutos
            this.segundos
        }

    }

    init {
        this.numeroIdentificador
        this.escuderia
        this.participa
        this.tiempoTotal
        this.puntosObtendidos
        this.penalizacion
    }

    override fun toString(): String {
        return "Auto(numeroIdentificador=$numeroIdentificador, escuderia='$escuderia')"
    }


}