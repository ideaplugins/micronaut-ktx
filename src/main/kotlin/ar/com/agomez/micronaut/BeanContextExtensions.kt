/*
 * Copyright 2018-present by the authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at following link.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ar.com.agomez.micronaut

import io.micronaut.context.BeanContext

/**
 * Extension for [BeanContext.createBean] providing a `createBean<Foo>()` variant.
 *
 * @param T The bean type
 * @return The instance
 * @author Alejandro Gomez
 * @since 0.0.1
 */
inline fun <reified T> BeanContext.createBean(): T = createBean(T::class.java)

/**
 * Extension for [BeanContext.createBean] providing a `createStereotypedBean<Foo, Bar>()` variant.
 *
 * @param T The bean type
 * @param Q The stereotype type
 * @return The instance
 * @author Alejandro Gomez
 * @since 0.0.1
 */
inline fun <reified T, reified Q : Annotation> BeanContext.createStereotypedBean(): T = createBean(T::class.java, qualifierByStereotype<T, Q>())

/**
 * Extension for [BeanContext.createBean] providing a `createStereotypedBean<Foo, Bar>(args)` variant.
 *
 * @param T The bean type
 * @param Q The stereotype type
 * @param argumentValues The argument values
 * @return The instance
 * @author Alejandro Gomez
 * @since 0.0.1
 */
inline fun <reified T, reified Q : Annotation> BeanContext.createStereotypedBean(argumentValues: Map<String, Any>): T =
    createBean(T::class.java, qualifierByStereotype<T, Q>(), argumentValues)

/**
 * Extension for [BeanContext.createBean] providing a `createStereotypedBean<Foo, Bar>(args)` variant.
 *
 * @param T The bean type
 * @param Q The stereotype type
 * @param args The argument values
 * @return The instance
 * @author Alejandro Gomez
 * @since 0.0.1
 */
inline fun <reified T, reified Q : Annotation> BeanContext.createStereotypedBean(vararg args: Any): T =
    createBean(T::class.java, qualifierByStereotype<T, Q>(), *args)

/**
 * Extension for [BeanContext.createBean] providing a `createBean<Foo>(args)` variant.
 *
 * @param T The bean type
 * @param argumentValues The argument values
 * @return The instance
 * @author Alejandro Gomez
 * @since 0.0.1
 */
inline fun <reified T> BeanContext.createBean(argumentValues: Map<String, Any>): T = createBean(T::class.java, argumentValues)

/**
 * Extension for [BeanContext.createBean] providing a `createBean<Foo>(args)` variant.
 *
 * @param T The bean type
 * @param args The argument values
 * @return The instance
 * @author Alejandro Gomez
 * @since 0.0.1
 */
inline fun <reified T> BeanContext.createBean(vararg args: Any): T = createBean(T::class.java, *args)

/**
 * Extension for [BeanContext.destroyBean] providing a `destroyBean<Foo>()` variant.
 *
 * @param T The bean type
 * @return The destroy instance or null if no such bean exists
 * @author Alejandro Gomez
 * @since 0.0.1
 */
inline fun <reified T> BeanContext.destroyBean(): T = destroyBean(T::class.java)
