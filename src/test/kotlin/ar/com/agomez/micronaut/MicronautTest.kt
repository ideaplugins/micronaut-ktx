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

import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Parameter
import io.micronaut.context.annotation.Prototype
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Singleton

/**
 * @author Alejandro Gomez
 */
class MicronautTest {

    @Test
    fun mnRun() {
        val context = mnRun<TestFactory>("-baz=1")
        // TODO
        val foo = context.createBean(TestFactory.Foo::class.java, mapOf("baz" to 1))
        assertNotNull(foo)
        assertEquals(1, foo.baz)
        assertNotNull(context.getBean(TestFactory.Bar::class.java))
    }

    @Factory
    class TestFactory {

        @Prototype
        class Foo(@Parameter("baz") val baz: Int)

        @Singleton
        class Bar
    }
}
