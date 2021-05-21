interface Foo {
    fun extfoo(a: Any)
}

class CFoo1:Foo {
    override fun extfoo(a: Any) {
        when(a) {
            is CFoo1 -> println("CFoo1 -> extfoo")
            is CFoo2 -> println("CFoo2 -> extfoo")
            is Foo -> println("Foo -> extfoo")
            is Int -> println("Int -> extfoo")
            else -> println("Unknown -> extfoo")
        }
    }
    fun callMe(a:Any) {
        extfoo(a)
    }
}

class CFoo2:Foo {
    override fun extfoo(a: Any) {}
}
