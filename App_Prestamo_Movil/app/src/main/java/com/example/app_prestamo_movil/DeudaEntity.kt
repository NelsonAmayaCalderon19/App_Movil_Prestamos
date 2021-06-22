package com.example.app_prestamo_movil

data class DeudaEntity(
    val id: String="",
    val idcliente: String="",
    val nameCliente: String="",
    val fechaPrestamo: String="",
    val primerCobro: String="",
    val credito: String="",
    val interes: String="",
    val tipopago: String="",
    val totalpagar: String="",
    val valorcuota: String="",
    val cuotas: String=""
)
