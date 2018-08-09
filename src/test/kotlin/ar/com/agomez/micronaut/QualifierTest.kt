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
        assertThat(result?.beanType).isEqualTo(TestFactory.Foo::class.java)
    }

    @Factory
    class TestFactory {

        @Context
        class Foo
    }
}