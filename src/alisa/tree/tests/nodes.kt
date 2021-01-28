package alisa.tree.tests

import alisa.tree.*
import kotlin.test.assertEquals

/**
 *                   8
 *                 /  \
 *                 3  10
 *               / |     \
 *              1  6     14
 *                / \    /
 *               4  7    13
 */
val tree: Node = with(Node(8)) {
    insert(3)
    insert(10)
    insert(1)
    insert(6)
    insert(14)
    insert(4)
    insert(7)
    insert(13)
}

val avlTree:AvlTree = with(AvlTree()){
    insert(3)
    insert(10)
    insert(1)
    insert(6)
    insert(14)
    insert(4)
    insert(7)
    insert(13)
}

fun Node.dump():String = buildString {
    append('(').append(Value)
    Left?.let {
        append(", ")
        append("L").append(it.dump())
        Right?.let{append(", ")
        }
    } ?: append(' ')
    Right?.let { append("R").append(it.dump()) }
    append(')')
}

inline fun Node.stage(action:Node.()->Unit): Pair<String, String> {
    val dump1st = dump()
    action()
    return dump1st to dump()
}

inline fun Node.printlnStages(msg:String? = null, action:Node.()->Unit) {
    val (a,b) = stage(action)
    msg?.let(::println)
    println(a)
    println(b)
}

fun Node.copy():Node = accept(Node(Value)) { root ->
    Left?.run{ root.insert(Value) }
    Right?.run { root.insert(Value) }
    root
}

fun test() {
    println(tree.dump())
    assertEquals(3, tree.min_finder(2)?.Value)
    assertEquals(10, tree.min_finder(9)?.Value)
    assertEquals(13, tree.min_finder(11)?.Value)
    assertEquals(4, tree.max_finder(5)?.Value)

    tree.copy().printlnStages {
        delete(14)
    }

    tree.copy().printlnStages("rotate left") {
        search(3)!!.leftRotation()
    }

    tree.copy().printlnStages("rotate left and right") {
        search(3)!!.leftRotation()
        search(6)!!.rightRotation()
    }

    tree.copy().printlnStages("delete 10") {
        delete(10)
    }
}

fun main() {
    test()
    avlTree.root!!.dump().let(::println)
}