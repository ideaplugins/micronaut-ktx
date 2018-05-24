package io.micronaut.kotlin

import io.micronaut.context.ApplicationContext
import io.micronaut.context.BeanContext
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Parameter
import io.micronaut.context.annotation.Prototype
import io.micronaut.context.annotation.Requires
import io.micronaut.context.exceptions.NoSuchBeanException
import io.micronaut.inject.qualifiers.Qualifiers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.stream.Collectors

/**
 * @author Alejandro Gomez
 */
class BeanContextTest {

    private lateinit var context: BeanContext

    @BeforeEach
    fun setUp() {
        context = ApplicationContext.build(TestFactory::class.java).start()
    }

    @Test
    fun createBean() {
        val foo = context.createBean<BeanContextTest.TestFactory.Foo>()
        assertSame(TestFactory.Foo::class, foo::class)
    }

    @Test
    fun createStereotypedBean() {
        val foo = context.createStereotypedBean<TestFactory.Foo, Prototype>()
        assertSame(TestFactory.Foo::class, foo::class)
    }

    @Test
    fun createStereotypedBeanWithArguments() {
        val bar = context.createStereotypedBean<TestFactory.Bar, Prototype>(mapOf("baz" to 1))
        assertSame(TestFactory.Bar::class, bar::class)
        assertEquals(1, bar.baz)
    }

    @Test
    fun createStereotypedBeanWithVarArg() {
        val bar = context.createStereotypedBean<TestFactory.Bar, Prototype>(2)
        assertSame(TestFactory.Bar::class, bar::class)
        assertEquals(2, bar.baz)
    }

    @Test
    fun createBeanWithArguments() {
        val bar = context.createBean<TestFactory.Bar>(mapOf("baz" to 1))
        assertSame(TestFactory.Bar::class, bar::class)
        assertEquals(1, bar.baz)
    }

    @Test
    fun createBeanWithVarArg() {
        val bar = context.createBean<TestFactory.Bar>(2)
        assertSame(TestFactory.Bar::class, bar::class)
        assertEquals(2, bar.baz)
    }

    @Test
    fun destroyBean() {
        val baz = context.destroyBean<TestFactory.Baz>()
        assertSame(TestFactory.Baz::class, baz::class)
    }

    @Test
    fun contains() {
        assertFalse(TestFactory.Qux::class in context)
        assertTrue(TestFactory.Baz::class in context)
    }

    @Test
    fun containsStereotyped() {
        assertFalse((TestFactory.Qux::class to Context::class) in context)
        assertTrue((TestFactory.Baz::class to Context::class) in context)
    }

    @Test
    fun getBean() {
        assertThrows<NoSuchBeanException> { context.getBean<TestFactory.Qux>() }
        assertNotNull(context.getBean<TestFactory.Foo>())
    }

    @Test
    fun getStereotypedBean() {
        assertThrows<NoSuchBeanException> { context.getStereotypedBean<TestFactory.Qux, Context>() }
        assertNotNull(context.getStereotypedBean<TestFactory.Foo, Prototype>())
    }

    @Test
    fun findBean() {
        assertFalse(context.findBean<TestFactory.Qux>().isPresent)
        assertTrue(context.findBean<TestFactory.Foo>().isPresent)
    }

    @Test
    fun findStereotypedBean() {
        assertFalse(context.findStereotypedBean<TestFactory.Qux, Context>().isPresent)
        assertTrue(context.findStereotypedBean<TestFactory.Foo, Prototype>().isPresent)
    }

    @Test
    fun getBeansOfType() {
        assertIterableEquals(context.getBeansOfType(TestFactory.Baz::class.java), context.getBeansOfType<TestFactory.Baz>())
        assertIterableEquals(context.getBeansOfType(TestFactory.Qux::class.java), context.getBeansOfType<TestFactory.Qux>())
    }

    @Test
    fun getStereotypedBeansOfType() {
        assertIterableEquals(context.getBeansOfType(TestFactory.Baz::class.java, Qualifiers.byStereotype(Context::class.java)),
            context.getStereotypedBeansOfType<TestFactory.Baz, Context>())
        assertIterableEquals(context.getBeansOfType(TestFactory.Qux::class.java, Qualifiers.byStereotype(Context::class.java)),
            context.getStereotypedBeansOfType<TestFactory.Qux, Context>())
    }

    @Test
    fun streamOfType() {
        val bazes = context.streamOfType(TestFactory.Baz::class.java).collect(Collectors.toList())
        assertIterableEquals(bazes, context.streamOfType<TestFactory.Baz>().collect(Collectors.toList()))
        val quxes = context.streamOfType(TestFactory.Qux::class.java).collect(Collectors.toList())
        assertIterableEquals(quxes, context.streamOfType<TestFactory.Qux>().collect(Collectors.toList()))
    }

    @Test
    fun streamOfStereotypedType() {
        val bazes = context.streamOfType(TestFactory.Baz::class.java, Qualifiers.byStereotype(Context::class.java)).collect(Collectors.toList())
        assertIterableEquals(bazes, context.streamOfStereotypedType<TestFactory.Baz, Context>().collect(Collectors.toList()))
        val quxes = context.streamOfType(TestFactory.Qux::class.java, Qualifiers.byStereotype(Context::class.java)).collect(Collectors.toList())
        assertIterableEquals(quxes, context.streamOfStereotypedType<TestFactory.Qux, Context>().collect(Collectors.toList()))
    }

    @Test
    fun getProxyTargetBean() {
        assertSame(context.getProxyTargetBean(TestFactory.Baz::class.java, Qualifiers.byStereotype(Context::class.java))::class,
            context.getProxyTargetBean<TestFactory.Baz, Context>()::class)
    }

    @Test
    fun findOrInstantiateBean() {
        assertSame(context.findOrInstantiateBean(TestFactory.Foo::class.java)::class, context.findOrInstantiateBean<TestFactory.Foo>()::class)
        assertSame(context.findOrInstantiateBean(TestFactory.Baz::class.java)::class, context.findOrInstantiateBean<TestFactory.Baz>()::class)
    }

    @Factory
    class TestFactory {

        @Prototype
        class Foo

        @Prototype
        class Bar(@Parameter("baz") val baz: Int)

        @Context
        class Baz

        @Context
        @Requires(property = "qux.enabled")
        class Qux
    }
}
