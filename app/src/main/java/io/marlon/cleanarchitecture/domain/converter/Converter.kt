package io.marlon.cleanarchitecture.domain.converter

interface Converter<I, O> {
    fun convert(input: I): O
    fun convert(listInput: List<I>): List<O>
    fun revert(output: O): I
    fun revert(listOutput: List<O>): List<I>
}