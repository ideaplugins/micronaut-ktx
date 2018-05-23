package io.micronaut.kotlin

import io.micronaut.context.Qualifier
import io.micronaut.core.annotation.AnnotationMetadata
import io.micronaut.inject.qualifiers.Qualifiers

/**
 * @author Alejandro Gomez
 */

inline fun <reified T, reified Q : Annotation> qualifierByAnnotation(metadata: AnnotationMetadata): Qualifier<T> = Qualifiers.byAnnotation(metadata, Q::class.java)

inline fun <reified T, reified Q : Annotation> qualifierByStereotype(): Qualifier<T> = Qualifiers.byStereotype(Q::class.java)
