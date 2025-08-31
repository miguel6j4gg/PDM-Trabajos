/*
Descripción: Sistema de Biblioteca con materiales, usuarios y operaciones de préstamo y devolución.
Autor: Miguel Ocharan
Fecha creación: 31/08/2025
Última modificación: 31/08/2025
*/

abstract class Material(val titulo: String, val autor: String, val anioPublicacion: Int) {
    abstract fun mostrarDetalles()
}

class Libro(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    private val genero: String,
    private val numPaginas: Int
) : Material(titulo, autor, anioPublicacion) {
    override fun mostrarDetalles() {
        println("Libro: $titulo, Autor: $autor, Año: $anioPublicacion, Género: $genero, Páginas: $numPaginas")
    }
}

class Revista(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    private val issn: String,
    private val volumen: Int,
    private val numero: Int,
    private val editorial: String
) : Material(titulo, autor, anioPublicacion) {
    override fun mostrarDetalles() {
        println("Revista: $titulo, Autor: $autor, Año: $anioPublicacion, ISSN: $issn, Volumen: $volumen, Número: $numero, Editorial: $editorial")
    }
}

data class Usuario(val nombre: String, val apellido: String, val edad: Int)

interface IBiblioteca {
    fun registrarMaterial(material: Material)
    fun registrarUsuario(usuario: Usuario)
    fun prestarMaterial(usuario: Usuario, material: Material)
    fun devolverMaterial(usuario: Usuario, material: Material)
    fun mostrarMaterialesDisponibles()
    fun mostrarMaterialesReservados(usuario: Usuario)
}

class Biblioteca : IBiblioteca {
    private val materialesDisponibles = mutableListOf<Material>()
    private val prestamos = mutableMapOf<Usuario, MutableList<Material>>()
    private val usuarios = mutableListOf<Usuario>()

    override fun registrarMaterial(material: Material) {
        materialesDisponibles.add(material)
    }

    override fun registrarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    override fun prestarMaterial(usuario: Usuario, material: Material) {
        if (materialesDisponibles.contains(material)) {
            materialesDisponibles.remove(material)
            prestamos.computeIfAbsent(usuario) { mutableListOf() }.add(material)
            println("Material prestado a ${usuario.nombre}")
        } else {
            println("El material no está disponible.")
        }
    }

    override fun devolverMaterial(usuario: Usuario, material: Material) {
        prestamos[usuario]?.remove(material)
        materialesDisponibles.add(material)
        println("Material devuelto por ${usuario.nombre}")
    }

    override fun mostrarMaterialesDisponibles() {
        println("Materiales disponibles:")
        materialesDisponibles.forEach { it.mostrarDetalles() }
    }

    override fun mostrarMaterialesReservados(usuario: Usuario) {
        println("Materiales prestados a ${usuario.nombre}:")
        prestamos[usuario]?.forEach { it.mostrarDetalles() } ?: println("Ninguno")
    }
}

// Prueba
fun main() {
    val biblioteca = Biblioteca()
    val libro = Libro("Kotlin Avanzado", "Juan Pérez", 2023, "Programación", 350)
    val revista = Revista("Ciencia Hoy", "Varios", 2024, "1234-5678", 12, 3, "Editorial XYZ")
    val usuario = Usuario("Miguel", "Ocharan", 21)

    biblioteca.registrarMaterial(libro)
    biblioteca.registrarMaterial(revista)
    biblioteca.registrarUsuario(usuario)

    biblioteca.mostrarMaterialesDisponibles()
    biblioteca.prestarMaterial(usuario, libro)
    biblioteca.mostrarMaterialesDisponibles()
    biblioteca.mostrarMaterialesReservados(usuario)
    biblioteca.devolverMaterial(usuario, libro)
    biblioteca.mostrarMaterialesDisponibles()
}
