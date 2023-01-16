fun main () {
    t {
        a()
        b()
    }
}

val testImpl = TestImpl()

inline fun t(
    content: TestDsl.() -> Unit
) {
    testImpl.content().also { println("c") }
}


/*
val visible = mutableStateOf(true)
application(
        exitProcessOnExit = true
    ) {
        Window(
            visible = visible.value,
            title = "ee",
            onCloseRequest = ::exitApplication
        ) {

        }
    }
 */