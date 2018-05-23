package io.micronaut.kotlin

import io.micronaut.context.BeanDefinitionRegistry
import io.micronaut.context.BeanRegistration
import io.micronaut.inject.BeanDefinition
import java.util.Optional

/**
 * Extension for [BeanDefinitionRegistry.getBeanDefinition] providing a `getBeanDefinitionStereotyped<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.getBeanDefinitionStereotyped(): BeanDefinition<T> =
    getBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.containsBean] providing a `containsBean<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.containsBean() = containsBean(T::class.java)

/**
 * Extension for [BeanDefinitionRegistry.containsBean] providing a `containsBeanStereotyped<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.containsBeanStereotyped() =
    containsBean(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.findBeanDefinition] providing a `findBeanDefinitionStereotyped<Foo, Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.findBeanDefinitionStereotyped(): Optional<BeanDefinition<T>> =
    findBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.getBeanDefinitions] providing a `getBeanDefinitions<Foo>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.getBeanDefinitions(): Collection<BeanDefinition<T>> = getBeanDefinitions(T::class.java)

/**
 * Extension for [BeanDefinitionRegistry.getBeanDefinitions] providing a `getBeanDefinitionsStereotyped<Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified Q : Annotation> BeanDefinitionRegistry.getBeanDefinitionsStereotyped(): Collection<BeanDefinition<out Any>> =
    getBeanDefinitions(qualifierByStereotype<Any, Q>())

/**
 * Extension for [BeanDefinitionRegistry.getBeanRegistrations] providing a `getBeanRegistrationsStereotyped<Bar>()` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified Q : Annotation> BeanDefinitionRegistry.getBeanRegistrationsStereotyped(): Collection<BeanRegistration<out Any>> =
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
 * Extension for [BeanDefinitionRegistry.registerSingleton] providing a `registerSingletonStereotyped<Foo, Bar>(singleton, true)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.registerSingletonStereotyped(singleton: T, inject: Boolean): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton, qualifierByStereotype<T, Q>(), inject)

/**
 * Extension for [BeanDefinitionRegistry.registerSingleton] providing a `registerSingletonStereotyped<Foo, Bar>(singleton)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.registerSingletonStereotyped(singleton: T): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanDefinitionRegistry.registerSingleton] providing a `registerSingletonNotStereotyped<Foo>(singleton)` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <reified T> BeanDefinitionRegistry.registerSingletonNotStereotyped(singleton: T): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton)
