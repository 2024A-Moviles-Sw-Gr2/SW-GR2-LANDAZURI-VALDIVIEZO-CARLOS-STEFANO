import java.lang.reflect.Constructor
import java.util.StringJoiner

fun main() {
    println("Hola mundo")

    /*
    Mutables
    var mutable: String = "Vicente"
    mutable = "Adrian"

    var ejemploVariable = " Adrian Eguez "
    var edadEjemplo: Int = 12
    ejemploVariable.trim()
    */


    /*
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") ->{
            println("Casada")
        }
        "S" ->{
            println("soltero")
        }
        else ->{
            println("Desconocido")
        }
    }

    */


    /*
    // void -> unit
    fun imprimirNombre(nombre:String): Unit{ //Unit por que no retorna nada
        println("Nombre: ${nombre}")
    }

    fun calcularSueldo(
        sueldo: Double, // requerido
        tasa: Double = 12.00, //opcional
        bonoEspecial: Double? = null // opcional(nullable)
    ): Double{ // porque retorna un double
        if(bonoEspecial == null){
            return sueldo * (100/tasa)
        } else {
            return sueldo * (100/tasa) * bonoEspecial;
        }
    }


    println(calcularSueldo(10.00));

    val sueldoTotal: Double = calcularSueldo(10.00, bonoEspecial = 20.00);
    println("El sueldo total de la segunda prueba es: $sueldoTotal") ;


    val sueldoTotal3: Double =calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00);
    println("El sueldo total de la segunda prueba es: $sueldoTotal3") ;
    */

    val sumaUno = suma(1, 1); // instanciando la clase
    val sumaDos = suma(null, 1);
    val sumaTres = suma(null, null);
    val sumaCuatro = suma(null, null);

    println(sumaDos.sumar()); // usando los metodos
    println(sumaUno.sumar());
    println(sumaTres.sumar());

    // Aregglos
    // arreglos staticos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)
    // dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    // FOR EACH => Unit
    // Iterar un arreglo
    val respuestaForEach1: Unit = arregloDinamico
        .forEach {
            println("Valor iterado: $it")
        }
    val respuestaForEach2: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor iterado: $valorActual")
        }
    // "it" => variable implicita
    arregloDinamico.forEach { println("Valor iterado: $it") }
    // MAP -> Muta el arreglo (cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve un NUEVO ARREGLO con los valores modificados
    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMap2: List<Double> = arregloDinamico
        .map { it.toDouble() + 15.00 }


    // 17/05/2024

    // Filter - > Filtrar el ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo con los valores filtrados
        val respuestaFilter: List<Int> = arregloDinamico
            .filter { valorActual: Int ->
                val mayoresACinco: Boolean = valorActual > 5
                return@filter mayoresACinco
            }

        val respuestaFilter2: List<Int> = arregloDinamico.filter { it <= 5} //it es el valor que va a recorrer todo el arreglo
        println(respuestaFilter)
        println(respuestaFilter2)


    // OR AND
    // OR -> ANY (Alguno cumple?)
    // AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual>5)
        }
    println(respuestaAny);


    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll)


    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (siempre empiezo en 0)
    // [1, 2, 3, 4, 5] -> Acumular "SUMAR" estos valores del arreglo
    // valorIteracion1 = valorInicial + 1 = 0 + 1 = 1 -> iteracion1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> iteracion2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> iteracion3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> iteracion4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> iteracion5
        val respuestaReduce: Int = arregloDinamico
            .reduce { acumulado: Int, valorActual: Int ->
                return@reduce acumulado + valorActual
            }
        println(respuestaReduce)









}



abstract class Numeros( //sintaxis kotlin
    /* caso 1: Parametro normal
    uno: Int, (paramtero sin modificar el acceso)

    caso 2: Parametro y propiedad (atributo)(private)
    private var uno: Int (propiedad "instancia.1")
    */

    protected val numeroUno: Int, //instancia.numeroUno; // val es inmutable
    protected val numeroDos: Int,
){
    init {
        this.numeroUno
        this.numeroDos
        println("inicializando")
    }
}

abstract class NumerosJava{ //sintaxis java
    //constructor dentro mas lineas de codigo
    protected val numeroUno: Int
    protected val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){
        this.numeroUno = uno;
        this.numeroDos = dos;
        println("Inicializando")
    }
}



/*
-   En Numeros, las propiedades numeroUno y numeroDos están definidas directamente como argumentos del constructor,
    lo que las convierte en propiedades de solo lectura accesibles desde cualquier lugar dentro de la clase.

-   En NumerosJava, las propiedades numeroUno y numeroDos son campos privados que se inicializan en el constructor.
    Solo son accesibles dentro de la clase y sus subclases.
*/


class suma(
    unoParametro: Int,
    dosParametro: Int
): Numeros(
    unoParametro,
    dosParametro
) {
    public val soyPublicoExplicito: String = "Explicito"
    val soyPublicoImplicito: String = "Implícito"
    init {
        this.numeroUno
        this.numeroDos
        numeroUno
        numeroDos
        this.soyPublicoExplicito
        soyPublicoImplicito // this. OPCIONAL
    }

    // constructores secundarios: tienen una firma diferente
    constructor(
        uno:Int?,
        dos:Int
    ): this(
        if (uno==null) 0 else uno, // Si uno es nulo, se devuelve 0; de lo contrario, se devuelve el valor de uno
        dos
    )
    constructor(
        uno:Int,
        dos:Int?
    ): this(
        uno,
        if (dos==null) 0 else dos
    )
    constructor(
        uno:Int?,
        dos:Int?
    ): this(
        if (uno==null) 0 else uno,
        if (dos==null) 0 else dos
    )


    fun sumar(): Int {
        val total = this.numeroUno + this.numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{// comparte entre todas las instancias
    val pi = 3.1416
        fun elevarAlCuadrado(numero: Int): Int {
            return numero * numero
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(nuevaSuma: Int) {
            this.historialSumas.add(nuevaSuma)
        }
    }
}



