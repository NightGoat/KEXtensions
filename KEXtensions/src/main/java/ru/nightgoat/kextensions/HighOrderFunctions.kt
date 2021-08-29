package ru.nightgoat.kextensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.nightgoat.kextensions.utils.Try
import java.util.*

/**
 * Shorter version of coroutine context switching to Default dispatcher
 */
suspend fun <T> doOnDefault(doFun: suspend () -> T) = withContext(Dispatchers.Default) {
    doFun()
}

/**
 * Shorter version of coroutine context switching to Main dispatcher
 */
suspend fun <T> doOnMain(doFun: suspend () -> T) = withContext(Dispatchers.Main) {
    doFun()
}

/**
 * Shorter version of coroutine context switching to IO dispatcher
 */
suspend fun <T> doOnIO(doFun: suspend () -> T) = withContext(Dispatchers.IO) {
    doFun()
}

/**
 * try default fun realisation
 */
fun <T : Any> tryOrDefault(
    defaultValue: T,
    tag: String = "tryOrDefault(): ",
    tryFunc: () -> T?
): T {
    return Try.of(tag) {
        tryFunc() ?: defaultValue
    }.getOrDefault {
        defaultValue
    }
}

fun <T : Any> tryOrNull(tag: String = "tryOrNull(): ", tryFunc: () -> T?): T? {
    return Try.of(tag) {
        tryFunc()
    }.getOrNull()
}

fun tryOrEmpty(tag: String = "tryOrEmpty(): ", tryFunc: () -> String) =
    tryOrDefault("", tag) { tryFunc() }

fun tryOrNow(tag: String = "tryOrNow(): ", tryFunc: () -> Date) =
    tryOrDefault(Date(), tag) { tryFunc() }