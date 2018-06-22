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
import io.micronaut.context.BeanContext
import io.micronaut.context.BeanRegistration
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Parameter
import io.micronaut.context.annotation.Prototype
import io.micronaut.context.annotation.Requires
import io.micronaut.context.exceptions.NoSuchBeanException
import io.micronaut.inject.qualifiers.Qualifiers
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.stream.Collectors
import javax.annotation.Nullable
import javax.inject.Singleton

/**
 * @author Alejandro Gomez
 */
class BeanDefinitionRegistryTest {

    private lateinit var context: BeanContext

    @BeforeEach
    fun setUp() {
        context = ApplicationContext.build(TestFactory::class.java).start()
    }

    @Test
    fun getStereotypedBeanDefinition() {
        assertEquals(context.getBeanDefinition(TestFactory.Foo::class.java, Qualifiers.byStereotype(Prototype::class.java)),
            context.getStereotypedBeanDefinition<TestFactory.Foo, Prototype>())
    }

    @Test
    fun containsBean() {
        assertTrue(context.containsBean<TestFactory.Foo>())
        assertFalse(context.containsBean<String>())
    }

    @Test
    fun contains() {
        assertFalse(TestFactory.Qux::class in context)
        assertTrue(TestFactory.Foo::class in context)
    }

    @Test
    fun containsStereotypedBean() {
        assertEquals(context.containsBean(TestFactory.Foo::class.java, Qualifiers.byStereotype(Prototype::class.java)),
            context.containsStereotypedBean<TestFactory.Foo, Prototype>())
        assertEquals(context.containsBean(TestFactory.Foo::class.java, Qualifiers.byStereotype(Singleton::class.java)),
            context.containsStereotypedBean<TestFactory.Foo, Singleton>())
    }

    @Test
    fun containsStereotyped() {
        assertFalse((TestFactory.Qux::class to Prototype::class) in context)
        assertTrue((TestFactory.Foo::class to Prototype::class) in context)
    }

    @Test
    fun findStereotypedBeanDefinition() {
        assertEquals(context.findBeanDefinition(TestFactory.Foo::class.java, Qualifiers.byStereotype(Prototype::class.java)).orElse(null),
            context.findStereotypedBeanDefinition<TestFactory.Foo, Prototype>())
    }

    @Test
    fun getBeanDefinitions() {
        assertThat(context.getBeanDefinitions<TestFactory.Foo>()).containsAll(context.getBeanDefinitions(TestFactory.Foo::class.java))
    }

    @Test
    fun getStereotypedBeanDefinitions() {
        assertEquals(context.getBeanDefinitions(Qualifiers.byStereotype(Prototype::class.java)), context.getStereotypedBeanDefinitions<Prototype>())
    }

    @Test
    fun getStereotypedBeanRegistrations() {
        assertEquals(context.getBeanRegistrations(Qualifiers.byStereotype<Prototype>(Prototype::class.java)),
            context.getStereotypedBeanRegistrations<Prototype>())
    }

    @Test
    fun getBeanRegistrations() {
        assertEquals(context.getBeanRegistrations(TestFactory.Foo::class.java), context.getBeanRegistrations<TestFactory.Foo>())
    }

    @Test
    fun findProxiedBeanDefinition() {
        assertEquals(context.findProxiedBeanDefinition(TestFactory.Foo::class.java, Qualifiers.byStereotype<TestFactory.Foo>(Prototype::class.java))
            .orElse(null), context.findProxiedBeanDefinition<TestFactory.Foo, Prototype>())
    }

    @Test
    fun getProxiedBeanDefinition() {
        assertEquals(context.getProxiedBeanDefinition(TestFactory.Foo::class.java, Qualifiers.byStereotype<TestFactory.Foo>(Prototype::class.java)),
            context.getProxiedBeanDefinition<TestFactory.Foo, Prototype>())
    }

    @Test
    fun findBeanDefinition() {
        assertEquals(context.findBeanDefinition(TestFactory.Foo::class.java).orElse(null), context.findBeanDefinition<TestFactory.Foo>())
    }

    @Test
    fun registerStereotypedSingleton() {
        val singleton = TestFactory.Baz()
        context.registerStereotypedSingleton<TestFactory.Baz, Singleton>(singleton)
        assertSame(singleton, context.getBean(TestFactory.Baz::class.java))
        assertNull(context.getBean(TestFactory.Baz::class.java).foo)
    }

    @Test
    fun registerStereotypedSingletonWithInjectionDisabled() {
        val singleton = TestFactory.Baz()
        context.registerStereotypedSingleton<TestFactory.Baz, Singleton>(singleton, false)
        assertSame(singleton, context.getBean(TestFactory.Baz::class.java))
        // TODO
        assertNull(context.getBean(TestFactory.Baz::class.java).foo)
    }

    @Test
    fun registerStereotypedSingletonWithInjectionEnabled() {
        val singleton = TestFactory.Baz()
        context.registerStereotypedSingleton<TestFactory.Baz, Singleton>(singleton, true)
        assertSame(singleton, context.getBean(TestFactory.Baz::class.java))
        // TODO
        //assertNotNull(context.getBean(TestFactory.Baz::class.java).foo)
    }

    @Test
    fun registerNotStereotypedSingleton() {
        val singleton = TestFactory.Baz()
        context.registerNotStereotypedSingleton(singleton)
        assertSame(singleton, context.getBean(TestFactory.Baz::class.java))
    }

    @Factory
    class TestFactory {

        @Prototype
        class Foo

        @Prototype
        class Bar(@Parameter("baz") val baz: Int)

        class Baz(@Nullable val foo: Foo? = null)

        @Context
        @Requires(property = "qux.enabled")
        class Qux
    }
}
