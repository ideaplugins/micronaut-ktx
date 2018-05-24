package io.micronaut.kotlin

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
