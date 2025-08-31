/*
Descripción: Clase Producto con precio y descuento. Permite calcular precio final después de aplicar el descuento.
Autor: Miguel Ocharan
Fecha creación: 31/08/2025
Última modificación: 31/08/2025
*/

class Producto(private var _precio: Double, private var _descuento: Double) {

    var precio: Double
        get() = _precio
        set(value) {
            if (value >= 0) _precio = value
            else println("El precio no puede ser negativo.")
        }

    var descuento: Double
        get() = _descuento
        set(value) {
            if (value in 0.0..100.0) _descuento = value
            else println("El descuento debe estar entre 0 y 100%.")
        }

    fun precioFinal(): Double {
        return _precio - (_precio * _descuento / 100)
    }
}

// Prueba
fun main() {
    val producto = Producto(200.0, 10.0)
    println("Precio final con descuento: ${producto.precioFinal()}")
    producto.descuento = 120.0
}
