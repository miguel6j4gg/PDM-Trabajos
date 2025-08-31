/*
Descripción: Clases de figuras geométricas que heredan de una clase abstracta Shape. Calculan área y perímetro.
Autor: Miguel Ocharan
Fecha creación: 31/08/2025
Última modificación: 31/08/2025
*/

abstract class Shape {
    abstract fun calcularArea(): Double
    abstract fun calcularPerimetro(): Double

    fun imprimirResultados() {
        println("Área: ${calcularArea()}, Perímetro: ${calcularPerimetro()}")
    }
}

class Cuadrado(private val lado: Double) : Shape() {
    override fun calcularArea() = lado * lado
    override fun calcularPerimetro() = 4 * lado
}

class Rectangulo(private val base: Double, private val altura: Double) : Shape() {
    override fun calcularArea() = base * altura
    override fun calcularPerimetro() = 2 * (base + altura)
}

class Circulo(private val radio: Double) : Shape() {
    override fun calcularArea() = Math.PI * radio * radio
    override fun calcularPerimetro() = 2 * Math.PI * radio
}

// Prueba
fun main() {
    val cuadrado = Cuadrado(4.0)
    val rectangulo = Rectangulo(5.0, 3.0)
    val circulo = Circulo(2.5)

    cuadrado.imprimirResultados()
    rectangulo.imprimirResultados()
    circulo.imprimirResultados()
}
