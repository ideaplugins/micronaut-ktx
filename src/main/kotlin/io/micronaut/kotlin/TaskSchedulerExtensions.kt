package io.micronaut.kotlin

import io.micronaut.scheduling.TaskScheduler
import java.time.Duration
import java.util.concurrent.Callable

/**
 * Extension for [TaskScheduler.schedule] providing a `scheduleCallable("0 0 12 * * ?") { doWork() }` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <V> TaskScheduler.scheduleCallable(cron: String, crossinline command: () -> V) = schedule(cron, Callable<V> { command.invoke() })

/**
 * Extension for [TaskScheduler.schedule] providing a `scheduleCallable(delay) { doWork() }` variant.
 *
 * @author Alejandro Gomez
 * @since 1.0
 */
inline fun <V> TaskScheduler.scheduleCallable(delay: Duration, crossinline command: () -> V) = schedule(delay, Callable<V> { command.invoke() })
