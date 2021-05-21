interface Foo {
    fun Any.extfoo()
}

class CFoo1:Foo {
    override fun Any.extfoo() {
        when(this) {
            is CFoo1 -> println("CFoo1 -> extfoo")
            is CFoo2 -> println("CFoo2 -> extfoo")
            is Foo -> println("Foo -> extfoo")
            is Int -> println("Int -> extfoo")
            else -> println("Unknown -> extfoo")
        }
    }
    fun callMe(a:Any) {
        a.extfoo()
    }
}

class CFoo2:Foo {
    override fun Any.extfoo() {
    }
}