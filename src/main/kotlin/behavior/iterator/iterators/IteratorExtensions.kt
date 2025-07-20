package behavior.iterator.iterators

import behavior.iterator.profile.Profile

/**
 * Extension functions for more idiomatic Kotlin iteration
 */
suspend fun ProfileIterator.forEachAsync(action: suspend (Profile) -> Unit) {
    while (hasNextAsync()) {
        action(nextAsync())
    }
}

suspend fun ProfileIterator.mapAsync(transform: suspend (Profile) -> Profile): List<Profile> {
    val result = mutableListOf<Profile>()
    while (hasNextAsync()) {
        result.add(transform(nextAsync()))
    }
    return result
}

suspend fun ProfileIterator.filterAsync(predicate: suspend (Profile) -> Boolean): List<Profile> {
    val result = mutableListOf<Profile>()
    while (hasNextAsync()) {
        val profile = nextAsync()
        if (predicate(profile)) {
            result.add(profile)
        }
    }
    return result
}

// Convert to Sequence for lazy evaluation
fun ProfileIterator.asSequence(): Sequence<Profile> = sequence {
    while (hasNext()) {
        yield(next())
    }
}
