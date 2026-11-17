import java.util.Scanner
import kotlin.math.sqrt
class Funciones {
    private val scan = Scanner(System.`in`)
    var numeros = mutableListOf<Int>();
    fun iniciar() {
        var opcion: Int
        do {
            mostrarMenu()
            // Depuración
            if (!scan.hasNextInt()) {
                println("Opción no válida, introduce un número.")
                scan.next()
                opcion = 0
                continue
            }
            opcion = scan.nextInt()
            when (opcion) {
                1 -> {
                    generacionNumeros()
                }


                2 -> {
                    introduccionNumeros()
                }

                3 -> {

                    if (hayDatos()){ mostrarNumeros(numeros)}
                }
                4 -> {
                    if (hayDatos()) {
                        println("La media es: ${"%.2f".format(calcularMedia(numeros))}")
                    }
                }

                5 -> {
                    if (hayDatos()) {
                        mostrarModa()
                    }
                }

                6 -> {
                    if (hayDatos()) {
                        println("La desviación típica es: ${"%.2f".format(calcularDesviacionTipica(numeros))}")
                    }
                }

                7 -> {
                    if (hayDatos()) {
                        println("La media es: ${"%.2f".format(calcularMedia(numeros))}")
                        mostrarModa()
                        println("La desviación típica es: ${"%.2f".format(calcularDesviacionTipica(numeros))}")
                    }
                }

                0 -> {
                    println("Saliendo del programa. ¡Hasta pronto!")

                }

                else -> {
                    println("Opción no válida")
                }
            }
        } while (opcion != 0)
    }

    //Fase 7. Menú principal
    private fun mostrarMenu() {
        println("\n===== ESTADÍSTICAS CON KOTLIN =====")
        println("0. Salir")
        println("1. Generar 100 números aleatorios")
        println("2. Introducir 100 números manualmente")
        println("3. Mostrar números")
        println("4. Calcular media")
        println("5. Calcular moda")
        println("6. Calcular desviación típica")
        println("7. Calcular todas las estadísticas")
        print("Seleccione una opción: ")
    }

    //Fase2.A Introducción manual
    protected fun introduccionNumeros() {
        val x =mutableListOf<Int>()
        while (x.size <100){
            print("Introduce el número ${x.size +1} de 100:")
                if (!scan.hasNextInt()) {
                    println("Opción no válida, introduce un número.")
                    scan.next();
                    continue
                }
                x.add(scan.nextInt())
        }
        numeros.clear()
        numeros.addAll(x)
    }


    //Fase 2.B Generación automática
    protected fun generacionNumeros() {
        numeros.clear()
        numeros.addAll(List(100){ (1..100).random()})
      print ("Lista de números generada")
    }
    private fun mostrarNumeros(numeros: List<Int>) {
        println("Lista de números:")
        numeros.chunked(10).forEach { println(it.joinToString(" ")) }
    }

    //Fase 3. Media
    private fun calcularMedia(numeros: List<Int>): Double {
        return numeros.sum().toDouble() / numeros.size
    }

    //Fase4. Moda
    private fun calcularModa(numeros: List<Int>): List<Int> {
        // 1. Contar cuántas veces aparece cada número
        val frecuencias = mutableMapOf<Int, Int>()
        for (numero in numeros) {
            if (frecuencias.containsKey(numero)) {
                frecuencias[numero] =frecuencias[numero]!! + 1
            } else {
                frecuencias[numero] =1
            }
        }
        // 2. Buscar la frecuencia máxima
        var max =0
        for (veces in frecuencias.values) {
            if (veces >max) {
                max =veces
            }
        }
        // 3. Si todos aparecen una sola vez, no hay moda
        if (max ==1) {
            return emptyList()
        }
        // 4. Guardar los números que tienen esa frecuencia máxima
        val modas = mutableListOf<Int>()
        for ((numero, veces) in frecuencias) {
            if (veces == max) {
                modas.add(numero)
            }
        }
        modas.sort()
        return modas
    }
    private fun mostrarModa() {
        val modas = calcularModa(numeros)
        when {
            modas.isEmpty() -> println("No hay moda: todos los valores aparecen una sola vez.")
            modas.size == 1 -> println("La moda es: ${modas[0]}")
            else -> {
                val texto = modas.dropLast(1).joinToString(", ") + " y " + modas.last()
                println("Las modas son: $texto")
            }
        }
    }

    //Fase 5. Desviación típica
    private fun calcularDesviacionTipica(numeros: List<Int>): Double {
        val media = calcularMedia(numeros)
        val sumaCuadrados = numeros.sumOf { (it - media) * (it - media) }
        return sqrt(sumaCuadrados / numeros.size)
    }
    //Fase 8. Validación lista vacía
    private fun hayDatos(): Boolean {
        if (numeros.isEmpty()) {
            println("Primero tienes que generar o introducir los números (opciones 1 o 2).")
            return false
        }
        return true
    }
}
