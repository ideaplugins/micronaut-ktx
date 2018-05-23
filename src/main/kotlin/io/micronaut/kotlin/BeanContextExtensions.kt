package io.micronaut.kotlin

import io.micronaut.context.BeanContext

/**
 * Extension for [BeanContext.createBean] providing a `createBean<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanContext.createBean(): T = createBean(T::class.java)

/**
 * Extension for [BeanContext.createBean] providing a `createBeanStereotyped<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanContext.createBeanStereotyped(): T = createBean(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanContext.createBean] providing a `createBeanStereotyped<Foo, Bar>(args)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanContext.createBeanStereotyped(argumentValues: Map<String, Any>): T =
    createBean(T::class.java, qualifierByStereotype<T, Q>(), argumentValues)

/**
 * Extension for [BeanContext.createBean] providing a `createBeanStereotyped<Foo, Bar>(args)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanContext.createBeanStereotyped(vararg args: Any): T =
    createBean(T::class.java, qualifierByStereotype<T, Q>(), args)

/**
 * Extension for [BeanContext.createBean] providing a `createBean<Foo>(args)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanContext.createBean(argumentValues: Map<String, Any>): T = createBean(T::class.java, argumentValues)

/**
 * Extension for [BeanContext.createBean] providing a `createBean<Foo>(args)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanContext.createBean(vararg args: Any): T = createBean(T::class.java, args)

/**
 * Extension for [BeanContext.destroyBean] providing a `destroyBean<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanContext.destroyBean(): T = destroyBean(T::class.java)
