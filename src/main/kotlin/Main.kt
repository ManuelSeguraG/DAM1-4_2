package un4.eje4_2

// TIENDA
data class Tienda(val nombre: String, val clientes: List<Clientes>) {
    fun obtenerConjuntoDeClientes(): Set<Clientes> = clientes.toSet()

    fun obtenerCiudadesDeClientes(): Set<Ciudad> = clientes.map { it.ciudad }.toSet()

    fun obtenerClientesPor(ciudad: Ciudad): List<Clientes> = clientes.filter { it.ciudad == ciudad }

    fun checkTodosClientesSonDe(ciudad: Ciudad): Boolean = clientes.all { it.ciudad == ciudad }

    fun hayClientesDe(ciudad: Ciudad): Boolean = clientes.any { it.ciudad == ciudad }

    fun cuentaClientesDe(ciudad: Ciudad): Int = clientes.count { it.ciudad == ciudad }

    fun encuentraClienteDe(ciudad: Ciudad): Clientes? = clientes.find { it.ciudad == ciudad }

    fun obtenerClientesOrdenadosPorPedidos(): List<Clientes> = clientes.sortedByDescending { it.pedidos.size }

    fun obtenerClientesConPedidosSinEntregar(): Set<Clientes> =
        clientes.filter { it.pedidos.any { it.estaEntregado == false } }.toSet()

    fun obtenerProductosPedidos(): Set<Producto> =
        clientes.flatMap { it.pedidos.flatMap { it.productos } }.toSet()

    fun obtenerNumeroVecesProductoPedido(producto: Producto): Int = obtenerProductosPedidos().count { it.equals(producto) }

//        var cantidadSolicitada: Int = 0
//        clientes.forEach {
//            it.pedidos.forEach {
//                it.productos.forEach {
//                    if (it.equals(producto)) {
//                        cantidadSolicitada++
//                    }
//                }
//            }
//
//        }
//
//        return cantidadSolicitada
//
//    }

    fun agrupaClientesPorCiudad(): Map<Ciudad, List<Clientes>> = clientes.groupBy { it.ciudad }

}

// CLIENTES
data class Clientes(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {
    override fun toString() = "$nombre from ${ciudad.nombre}"

    fun obtenerProductosPedidos(): List<Producto> = pedidos.flatMap { it.productos }

    fun encuentraProductoMasCaro(): Producto? = obtenerProductosPedidos().sortedBy { it.precio }[0]

    fun dineroGastado(): Double {
        var cantidadGastada: Double = 0.0
        pedidos.forEach {
            if (it.estaEntregado) {
                it.productos.forEach { cantidadGastada += it.precio }
            }
        }
        return cantidadGastada
    }
}


// PEDIDO
data class Pedido(val productos: List<Producto>, val estaEntregado: Boolean)

// PRODUCTO
data class Producto(val nombre: String, val precio: Double) {
    override fun toString() = "'$nombre' for $precio"
}

// CIUDAD
data class Ciudad(val nombre: String) {
    override fun toString() = nombre
}

// MAIN
fun main() {

}