package io.micronaut.kotlin

import io.micronaut.context.BeanDefinitionRegistry
import io.micronaut.context.BeanRegistration
import io.micronaut.inject.BeanDefinition
import io.micronaut.inject.qualifiers.Qualifiers
import java.util.Optional
import kotlin.reflect.KClass

/**
 * Extension for [BeanDefinitionRegistry.getBeanDefinition] providing a `getStereotypedBeanDefinition<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.getStereotypedBeanDefinition(): BeanDefinition<T> =
    getBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.containsBean] providing a `containsBean<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.containsBean() = containsBean(T::class.java)

// TODO add doc
operator fun BeanDefinitionRegistry.contains(t: KClass<out Any>) = containsBean(t.java)

/**
 * Extension for [BeanDefinitionRegistry.containsBean] providing a `containsStereotypedBean<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.containsStereotypedBean() =
    containsBean(T::class.java, qualifierByStereotype<T, Q>())

// TODO add doc
operator fun BeanDefinitionRegistry.contains(t: Pair<KClass<out Any>, KClass<out Annotation>>) =
    containsBean(t.first.java, Qualifiers.byStereotype(t.second.java))

/**
 * Extension for [BeanDefinitionRegistry.findBeanDefinition] providing a `findStereotypedBeanDefinition<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.findStereotypedBeanDefinition(): Optional<BeanDefinition<T>> =
    findBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.getBeanDefinitions] providing a `getBeanDefinitions<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.getBeanDefinitions(): Collection<BeanDefinition<T>> = getBeanDefinitions(T::class.java)

/**
 * Extension for [BeanDefinitionRegistry.getBeanDefinitions] providing a `getStereotypedBeanDefinitions<Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified Q : Annotation> BeanDefinitionRegistry.getStereotypedBeanDefinitions(): Collection<BeanDefinition<out Any>> =
    getBeanDefinitions(qualifierByStereotype<Any, Q>())

/**
 * Extension for [BeanDefinitionRegistry.getBeanRegistrations] providing a `getStereotypedBeanRegistrations<Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified Q : Annotation> BeanDefinitionRegistry.getStereotypedBeanRegistrations(): Collection<BeanRegistration<out Any>> =
    getBeanRegistrations(qualifierByStereotype<Any, Q>())

/**
 * Extension for [BeanDefinitionRegistry.getBeanRegistrations] providing a `getBeanRegistrations<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.getBeanRegistrations(): Collection<BeanRegistration<T>> = getBeanRegistrations(T::class.java)

/**
 * Extension for [BeanDefinitionRegistry.findProxiedBeanDefinition] providing a `findProxiedBeanDefinition<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.findProxiedBeanDefinition(): Optional<BeanDefinition<T>> =
    findProxiedBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.getProxiedBeanDefinition] providing a `getProxiedBeanDefinition<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.getProxiedBeanDefinition(): BeanDefinition<T> =
    getProxiedBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.getBeanDefinition] providing a `getBeanDefinition<Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.getBeanDefinition(): BeanDefinition<T> = getBeanDefinition(T::class.java)

/**
 * Extension for [BeanDefinitionRegistry.findBeanDefinition] providing a `findBeanDefinition<Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.findBeanDefinition(): Optional<BeanDefinition<T>> = findBeanDefinition(T::class.java)

/**
 * Extension for [BeanDefinitionRegistry.registerSingleton] providing a `registerStereotypedSingleton<Foo, Bar>(singleton, true)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.registerStereotypedSingleton(singleton: T, inject: Boolean): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton, qualifierByStereotype<T, Q>(), inject)

/**
 * Extension for [BeanDefinitionRegistry.registerSingleton] providing a `registerStereotypedSingleton<Foo, Bar>(singleton)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.registerStereotypedSingleton(singleton: T): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.registerSingleton] providing a `registerNotStereotypedSingleton<Foo>(singleton)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.registerNotStereotypedSingleton(singleton: T): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton)
