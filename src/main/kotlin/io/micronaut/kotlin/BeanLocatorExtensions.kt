package io.micronaut.kotlin

import io.micronaut.context.BeanLocator
import java.util.Optional
import java.util.stream.Stream

/**
 * Extension for [BeanLocator.getBean] providing a `getBean<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanLocator.getBean(): T = getBean(T::class.java)

// TODO doc
inline fun <reified T, reified Q : Annotation> BeanLocator.getStereotypedBean(): T =
    getBean(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanLocator.findBean(): Optional<T> = findBean(T::class.java)

inline fun <reified T, reified Q : Annotation> BeanLocator.findStereotypedBean(): Optional<T> =
    findBean(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanLocator.getBeansOfType(): Collection<T> = getBeansOfType(T::class.java)

inline fun <reified T, reified Q : Annotation> BeanLocator.getStereotypedBeansOfType(): Collection<T> =
    getBeansOfType(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanLocator.streamOfType(): Stream<T> = streamOfType(T::class.java)

inline fun <reified T, reified Q : Annotation> BeanLocator.streamOfStereotypedType(): Stream<T> =
    streamOfType(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T, reified Q : Annotation> BeanLocator.getProxyTargetBean(): T =
    getProxyTargetBean(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanLocator.findOrInstantiateBean(): Optional<T> = findOrInstantiateBean(T::class.java)
