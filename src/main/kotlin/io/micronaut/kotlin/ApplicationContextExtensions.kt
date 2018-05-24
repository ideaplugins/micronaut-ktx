package io.micronaut.kotlin

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.PropertySource

/**
 *  Top level function acting as a Kotlin shortcut allowing to write `run<Foo>()`
 *  instead of `ApplicationContext.run(Foo::class.java)`.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> run() = ApplicationContext.run(T::class.java)

/**
 *  Top level function acting as a Kotlin shortcut allowing to write `run<Foo>("env")`
 *  instead of `ApplicationContext.run(Foo::class.java, "env")`.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> run(vararg environments: String) = ApplicationContext.run(T::class.java, *environments)

/**
 *  Top level function acting as a Kotlin shortcut allowing to write `run<Foo>(props, "env")`
 *  instead of `ApplicationContext.run(Foo::class.java, props, "env")`.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> run(properties: Map<String, Any?>, vararg environments: String) = ApplicationContext.run(T::class.java, properties, *environments)

/**
 *  Top level function acting as a Kotlin shortcut allowing to write `run<Foo>(props, "env")`
 *  instead of `ApplicationContext.run(Foo::class.java, props, "env")`.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> run(properties: PropertySource, vararg environments: String) = ApplicationContext.run(T::class.java, properties, *environments)
