package com.example.composemvi.route

class NavigationRoute {
    companion object {
        const val ROUTE_MAIN = "route_main"
        const val ROUTE_EVENT = "route_event"
        const val ROUTE_EVENT_ID = "route_event_id"
    }
}

class NavigationArguments {
    companion object {
        const val ARGUMENT_EVENT_ID = "event_id"
        const val ARGUMENT_EVENT = "event"
    }
}

fun destinationString(route: String, vararg arguments: String): String {
    var dest = route
    var isFirstArg = true
    arguments.forEach {
        if (!isFirstArg) {
            dest += ","
        } else {
            isFirstArg = !isFirstArg
        }
        dest += "?$it={$it}"
    }
    return dest
}

fun navigateString(route: String, vararg arguments: Pair<String, Any>): String {
    var dest = route
    var isFirstArg = true
    arguments.forEach {
        if (!isFirstArg) {
            dest += ","
        } else {
            isFirstArg = !isFirstArg
        }
        dest += "?${it.first}=${it.second}"
    }
    return dest
}