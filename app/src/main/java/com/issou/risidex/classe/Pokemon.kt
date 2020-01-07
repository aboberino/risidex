package com.issou.risidex.classe

data class Pokemon(val id: Int, val name: String, val image: String, val types: List<PokemonType>)

data class PokemonType(val name: String, val image: String)