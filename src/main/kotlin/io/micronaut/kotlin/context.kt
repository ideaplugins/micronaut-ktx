package io.micronaut.kotlin

/**
 * @author Alejandro Gomez
 */

import io.micronaut.context.BeanContext
import io.micronaut.context.BeanDefinitionRegistry
import io.micronaut.context.BeanRegistration
import io.micronaut.inject.BeanDefinition
import java.util.Optional

/**
 * @author Alejandro Gomez
 */

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.getBeanDefinitionBySteorotype(): BeanDefinition<T> =
    getBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanDefinitionRegistry.containsBean() = containsBean(T::class.java)

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.containsBeanStereotyped() =
    containsBean(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.findBeanDefinitionStereotyped(): Optional<BeanDefinition<T>> =
    findBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanDefinitionRegistry.getBeanDefinitions(): Collection<BeanDefinition<T>> = getBeanDefinitions(T::class.java)

inline fun <reified Q : Annotation> BeanDefinitionRegistry.getBeanDefinitionsStereotyped(): Collection<BeanDefinition<out Any>> =
    getBeanDefinitions(qualifierByStereotype<Any, Q>())

inline fun <reified Q : Annotation> BeanDefinitionRegistry.getBeanRegistrationsStereotyped(): Collection<BeanRegistration<out Any>> =
    getBeanRegistrations(qualifierByStereotype<Any, Q>())

inline fun <reified T> BeanDefinitionRegistry.getBeanRegistrations(): Collection<BeanRegistration<T>> = getBeanRegistrations(T::class.java)

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.findProxiedBeanDefinition(): Optional<BeanDefinition<T>> =
    findProxiedBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.getBeanDefinitionStereotyped(): BeanDefinition<T> =
    getBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.getProxiedBeanDefinition(): BeanDefinition<T> =
    getProxiedBeanDefinition(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanDefinitionRegistry.getBeanDefinition(): BeanDefinition<T> = getBeanDefinition(T::class.java)

inline fun <reified T> BeanDefinitionRegistry.findBeanDefinition(): Optional<BeanDefinition<T>> = findBeanDefinition(T::class.java)

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.registerSingleton(singleton: T, inject: Boolean): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton, qualifierByStereotype<T, Q>(), inject)

inline fun <reified T, reified Q : Annotation> BeanDefinitionRegistry.registerSingleton(singleton: T): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton, qualifierByStereotype<T, Q>())

inline fun <reified T> BeanDefinitionRegistry.registerSingletonNotStereotyped(singleton: T): BeanDefinitionRegistry =
    registerSingleton(T::class.java, singleton)

inline fun <reified T> BeanContext.createBean(): T = createBean(T::class.java)

inline fun <reified T, reified Q : Annotation> BeanContext.createBeanStereotyped(): T = createBean(T::class.java, qualifierByStereotype<T, Q>())

inline fun <reified T, reified Q : Annotation> BeanContext.createBeanStereotyped(argumentValues: Map<String, Any>): T =
    createBean(T::class.java, qualifierByStereotype<T, Q>(), argumentValues)

inline fun <reified T, reified Q : Annotation> BeanContext.createBeanStereotyped(vararg args: Any): T =
    createBean(T::class.java, qualifierByStereotype<T, Q>(), args)

inline fun <reified T> BeanContext.createBean(argumentValues: Map<String, Any>): T = createBean(T::class.java, argumentValues)

inline fun <reified T> BeanContext.createBean(vararg args: Any): T = createBean(T::class.java, args)

inline fun <reified T> BeanContext.destroyBean(): T = destroyBean(T::class.java)

// TODO ApplicationContext
