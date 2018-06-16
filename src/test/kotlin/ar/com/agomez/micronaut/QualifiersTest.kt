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

import io.micronaut.context.annotation.Context
import io.micronaut.core.annotation.AnnotationMetadata
import io.micronaut.core.naming.Named
import io.micronaut.inject.qualifiers.Qualifiers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Alejandro Gomez
 */
class QualifiersTest {

    @Test
    fun qualifierByStereotype() {
        // given
        // when
        val result = qualifierByStereotype<Any, Context>()
        // then
        assertThat(result::class).isEqualTo(Qualifiers.byStereotype<Any>(Context::class.java)::class)
    }

    @Test
    fun qualifierByAnnotation() {
        // given
        val metadata = AnnotationMetadata.EMPTY_METADATA
        // when
        val result = qualifierByAnnotation<Any, Context>(metadata)
        // then
        assertThat(result::class).isEqualTo(Qualifiers.byAnnotation<Context>(metadata, Context::class.java)::class)
        assertThat((result as Named).name).isEqualTo((Qualifiers.byAnnotation<Context>(metadata, Context::class.java) as Named).name)
    }
}
