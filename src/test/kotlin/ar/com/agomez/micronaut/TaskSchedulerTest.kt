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

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.micronaut.scheduling.TaskScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.Duration
import java.util.concurrent.Callable
import java.util.concurrent.ScheduledFuture

/**
 * @author Alejandro Gomez
 */
@ExtendWith(MockitoExtension::class)
class TaskSchedulerTest {

    @Mock
    private lateinit var scheduler: TaskScheduler
    @Mock
    private lateinit var future: ScheduledFuture<*>

    private lateinit var callableCaptor: ArgumentCaptor<Callable<*>>

    @BeforeEach
    internal fun setUp() {
        callableCaptor = ArgumentCaptor.forClass(Callable::class.java)
        whenever(future.get()).thenAnswer { callableCaptor.value.call() }
    }

    @AfterEach
    internal fun tearDown() {
        verify(future).get()
        verifyNoMoreInteractions(scheduler, future)
    }

    @Test
    fun scheduleCallableWithCron() {
        // given
        whenever(scheduler.schedule(anyString(), callableCaptor.capture())).thenReturn(future)
        // when
        val result = scheduler.scheduleCallable("0 0 12 * * ?") { 1 }
        // then
        verify(scheduler).schedule(eq("0 0 12 * * ?"), any<Callable<*>>())
        assertThat(result.get()).isEqualTo(1)
    }

    @Test
    fun scheduleCallableWithDelay() {
        // given
        whenever(scheduler.schedule(any<Duration>(), callableCaptor.capture())).thenReturn(future)
        // when
        val result = scheduler.scheduleCallable(Duration.ofSeconds(5)) { 2 }
        // then
        verify(scheduler).schedule(eq(Duration.ofSeconds(5)), any<Callable<*>>())
        assertThat(result.get()).isEqualTo(2)
    }
}
