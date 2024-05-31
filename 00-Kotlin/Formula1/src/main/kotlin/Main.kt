

fun main() {
    // CREACION DE AUTOS
    Auto.crear(Auto(1,"RB",true,Auto.Tiempo(1,35,11.3),25,1.2))
    Auto.crear(Auto(11,"RB",false,Auto.Tiempo(1,36,3.5),18,0.0))
    Auto.crear(Auto(16,"Ferrari",true,Auto.Tiempo(1,14,165.0),25,0.0))
    Auto.crear(Auto(55,"Ferrari",false,Auto.Tiempo(1,14,214.2),15,0.0))
    Auto.crear(Auto(4,"McLaren",false,Auto.Tiempo(1,18,589.0),25,2.1))
    Auto.autos.forEach { auto -> println(auto)  }
    // ACTUALIZACION
    Auto.actualizar(Auto(55,"Ferrari",false,Auto.Tiempo(1,14,214.2),0,0.0))
    // ELIMINACIÓN
    Auto.eliminar(4)
    Auto.autos.forEach { auto -> println(auto)  }
    //CREACION DE GP
    GrandPrix.crear(GrandPrix(1,Auto.autos,"Barein",Fecha(2024,3,2),5.412))
    GrandPrix.crear(GrandPrix(2,Auto.autos,"Monaco",Fecha(2024,5,26) ,3.33))
    GrandPrix.crear(GrandPrix(3,Auto.autos,"Imola",Fecha(2024,5,17),4.909))
    GrandPrix.grandPrixs.forEach { gp-> println(gp.toString()) }
    // ACTUALIZACION
    GrandPrix.actualizar(GrandPrix(3,Auto.autos.copyOfRange(0,2),"Emilia-Romagna",Fecha(2024,5,17),4.909))
    // ELIMINACIÓN
    GrandPrix.eliminar(1)
    GrandPrix.grandPrixs.forEach { gp-> println(gp.toString()) }
}



