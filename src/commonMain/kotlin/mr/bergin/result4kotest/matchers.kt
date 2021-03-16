package mr.bergin.result4kotest

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun <T> beSuccess(r: T): Matcher<Result<T, *>> = matcher {
    MatcherResult(
        this == Success(r),
        "$this should be Success($r)",
        "$this should not be Success($r)",
    )
}

fun beSuccess(): Matcher<Result<*, *>> = matcher {
    MatcherResult(
        this is Success<*>,
        "$this should be Success",
        "$this should not be Success",
    )
}


@OptIn(ExperimentalContracts::class)
fun <T> Result<T, *>.shouldBeSuccess() {
    contract {
        returns() implies (this@shouldBeSuccess is Success<*>)
    }
    this should beSuccess()
}

fun <R> Result<R, *>.shouldBeSuccess(block: (Success<R>) -> Unit) {
    this.shouldBeSuccess()
    block(this as Success<R>)
}

infix fun <T> Result<T, *>.shouldBeSuccess(value: T) = this should beSuccess(value)


fun <F> beFailure(r: F): Matcher<Result<*, F>> = matcher {
    MatcherResult(
        this == Failure(r),
        "$this should be Failure($r)",
        "$this should not be Failure($r)",
    )
}

fun beFailure(): Matcher<Result<*, *>> = matcher {
    MatcherResult(
        this is Failure<*>,
        "$this should be Failure",
        "$this should not be Failure",
    )
}


@OptIn(ExperimentalContracts::class)
fun <E> Result<*, E>.shouldBeFailure() {
    contract {
        returns() implies (this@shouldBeFailure is Failure<*>)
    }
    this should beFailure()
}

fun <E> Result<*, E>.shouldBeFailure(block: (Failure<E>) -> Unit) {
    this.shouldBeFailure()
    block(this as Failure<E>)
}

infix fun <E> Result<*, E>.shouldBeFailure(value: E) = this should beFailure(value)


private fun <T, E> matcher(block: Result<T, E>.() -> MatcherResult) = object : Matcher<Result<T, E>> {
    override fun test(value: Result<T, E>) = block(value)
}