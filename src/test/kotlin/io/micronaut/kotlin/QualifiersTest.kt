package io.micronaut.kotlin

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
