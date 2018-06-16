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

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.Micronaut

/**
 *  Top level function acting as a Kotlin shortcut allowing to write `mnRun<Foo>(args)`
 *  instead of `Micronaut.run(Foo::class.java, *args)`.
 *
 * @param T The application class
 * @param args The arguments
 * @return The [ApplicationContext]
 * @author Alejandro Gomez
 * @since 0.0.3
 */
inline fun <reified T> mnRun(vararg args: String): ApplicationContext = Micronaut.run(T::class.java, *args)
