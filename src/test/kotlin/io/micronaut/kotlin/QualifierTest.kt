package io.micronaut.kotlin

import io.micronaut.context.ApplicationContext
import io.micronaut.context.BeanContext
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Factory
import io.micronaut.inject.qualifiers.Qualifiers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.stream.Stream

/**
 * @author Alejandro Gomez
 */
class QualifierTest {

    private lateinit var context: BeanContext

    @BeforeEach
    fun setUp() {
        //val context = DefaultBeanContext().start()
        //val foo = TestFactory.Foo()
        //context.registerSingleton(foo)
        context = ApplicationContext.build(TestFactory::class.java).start()
    }

    @Test
    fun reduce() {
        // given
        val qualifier = Qualifiers.byStereotype<TestFactory.Foo>(Context::class.java)
        val beanDefinition = context.getBeanDefinition(TestFactory.Foo::class.java)
        val candidates = Stream.of(beanDefinition)
        // when
        val result = qualifier.reduce(candidates)
        // then
        assertThat(result).containsExactly(beanDefinition)
    }

    @Test
    fun qualify() {
        // given
        val qualifier = Qualifiers.byStereotype<TestFactory.Foo>(Context::class.java)
        val candidates = Stream.of(context.getBeanDefinition(TestFactory.Foo::class.java))
        // when
        val result = qualifier.qualify(candidates)
        // then
        assertThat(result).hasValueSatisfying {
            assertThat(it.beanType).isEqualTo(TestFactory.Foo::class.java)
        }
    }

    @Factory
    class TestFactory {

        @Context
        class Foo
    }
}
