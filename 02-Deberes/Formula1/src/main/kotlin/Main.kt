fun leerEntero(prompt: String): Int {
    print(prompt)
    return readLine()?.toIntOrNull() ?: 0
}

fun leerDouble(prompt: String): Double {
    print(prompt)
    return readLine()?.toDoubleOrNull() ?: 0.0
}

fun leerString(prompt: String): String {
    print(prompt)
    return readLine() ?: ""
}

fun leerBooleano(prompt: String): Boolean {
    print(prompt)
    return readLine()?.toBoolean() ?: false
}

fun recolectarDatosAuto(): Auto {
    val id = leerEntero("Ingrese el ID del auto: ")
    val marca = leerString("Ingrese la marca del auto: ")
    val enCarrera = leerBooleano("¿Está en carrera? (true/false): ")
    val horas = leerEntero("Ingrese las horas del tiempo: ")
    val minutos = leerEntero("Ingrese los minutos del tiempo: ")
    val segundos = leerDouble("Ingrese los segundos del tiempo: ")
    val tiempo = Auto.Tiempo(horas, minutos, segundos)
    val puntos = leerEntero("Ingrese los puntos obtenidos del auto: ")
    val penalización = leerDouble("Ingrese la penalización del auto: ")

    return Auto(id, marca, enCarrera, tiempo, puntos, penalización)
}
fun recolectarDatosGrandPrix(): GrandPrix {
    val id = leerEntero("Ingrese el ID del Grand Prix: ")
    val nombre = leerString("Ingrese el nombre del Grand Prix: ")
    val año = leerEntero("Ingrese el año de la fecha: ")
    val mes = leerEntero("Ingrese el mes de la fecha: ")
    val día = leerEntero("Ingrese el día de la fecha: ")
    val fecha = Fecha(año, mes, día)
    val longitud = leerDouble("Ingrese la longitud del circuito: ")
    val autos = Auto.autos  // Aquí se asume que se usarán los autos ya creados

    return GrandPrix(id, autos, nombre, fecha, longitud)
}

fun main() {
    while (true) {
        println("Menú de Opciones:")
        println("1. Crear Auto")
        println("2. Actualizar Auto")
        println("3. Eliminar Auto")
        println("4. Mostrar Autos")
        println("5. Crear Grand Prix")
        println("6. Actualizar Grand Prix")
        println("7. Eliminar Grand Prix")
        println("8. Mostrar Grand Prix")
        println("9. Salir")
        print("Selecciona una opción: ")

        when (readLine()?.toInt()) {
            1 -> {
                // CREACION DE AUTOS
                val auto = recolectarDatosAuto()
                Auto.crear(auto)
                println("Autos creados.")
            }
            2 -> {
                // ACTUALIZACION
                val auto = recolectarDatosAuto()
                Auto.actualizar(auto)
                println("Auto actualizado.")
            }
            3 -> {
                // ELIMINACIÓN
                val id = leerEntero("Ingrese el ID del auto a eliminar: ")
                Auto.eliminar(id)
                println("Auto eliminado.")
            }
            4 -> {
                // Mostrar Autos
                Auto.autos.forEach { auto -> println(auto) }
            }
            5 -> {
                val grandPrix = recolectarDatosGrandPrix()
                GrandPrix.crear(grandPrix)
                println("Grand Prix creado.")
            }
            6 -> {
                val grandPrix = recolectarDatosGrandPrix()
                GrandPrix.actualizar(grandPrix)
                println("Grand Prix actualizado.")
            }
            7 -> {
                val id = leerEntero("Ingrese el ID del Grand Prix a eliminar: ")
                GrandPrix.eliminar(id)
                println("Grand Prix eliminado.")
            }
            8 -> {
                // Mostrar Grand Prix
                GrandPrix.grandPrixs.forEach { gp -> println(gp) }
            }
            9 -> {
                // Salir
                println("Saliendo...")
                break
            }
            else -> println("Opción no válida. Intenta de nuevo.")
        }
    }
}



