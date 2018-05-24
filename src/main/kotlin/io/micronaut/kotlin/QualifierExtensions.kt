package io.micronaut.kotlin

import io.micronaut.context.Qualifier
import io.micronaut.inject.BeanType
import java.util.Optional
import java.util.stream.Stream

/**
 * Extension for [Qualifier.reduce] providing a `reduce<Foo>(candidates)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, BT : BeanType<T>> Qualifier<T>.reduce(candidates: Stream<BT>): Stream<BT> = reduce(T::class.java, candidates)

/**
 * Extension for [Qualifier.qualify] providing a `qualify<Foo>(candidates)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, BT : BeanType<T>> Qualifier<T>.qualify(candidates: Stream<BT>): Optional<BT> = qualify(T::class.java, candidates)
