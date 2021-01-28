

interface Foo {
    fun Any.extFoo()
}

class CFoo1:Foo {
    override fun Any.extFoo() {
        when(this) {
            is CFoo1 -> println("CFoo1 -> extfoo")
            is CFoo2 -> println("CFoo2 -> extfoo")
            is Foo -> println("Foo -> extfoo")
            is Int -> println("Int -> extfoo")
            else -> println("Anknown -> extfoo")
        }
    }
    fun callMe(a:Any) {
        a.extFoo()
    }
}

class CFoo2:Foo {
    override fun Any.extFoo() {}
}

fun main() {
    CFoo1().callMe(CFoo2())
    CFoo1().callMe(1)

}