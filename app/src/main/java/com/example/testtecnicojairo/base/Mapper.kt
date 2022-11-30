package com.example.testtecnicojairo.base

interface Mapper <I, O> {
    fun map(input: I): O
}