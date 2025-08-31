/*
Descripción: Clase CuentaBancaria con saldo y límite de retiro. Permite establecer/obtener saldo y retirar con validación.
Autor: Miguel Ocharan
Fecha creación: 31/08/2025
Última modificación: 31/08/2025
*/

class CuentaBancaria(private var _saldo: Double, private var _limiteRetiro: Double) {

    var saldo: Double
        get() = _saldo
        set(value) {
            if (value >= 0) _saldo = value
            else println("El saldo no puede ser negativo.")
        }

    var limiteRetiro: Double
        get() = _limiteRetiro
        set(value) {
            if (value > 0) _limiteRetiro = value
            else println("El límite de retiro debe ser positivo.")
        }

    fun retirar(monto: Double) {
        if (monto > _limiteRetiro) {
            println("El monto excede el límite de retiro.")
        } else if (monto > _saldo) {
            println("Saldo insuficiente.")
        } else {
            _saldo -= monto
            println("Retiro exitoso: $monto. Saldo restante: $_saldo")
        }
    }
}

// Prueba
fun main() {
    val cuenta = CuentaBancaria(1000.0, 500.0)
    cuenta.retirar(300.0)
    cuenta.retirar(600.0)
    cuenta.saldo = -200.0
    println("Saldo final: ${cuenta.saldo}")
}
