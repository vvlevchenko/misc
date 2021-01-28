package alisa.tree

import alisa.tree.tests.dump

class AvlTree {
    var root:Node? = null

    fun insert(value: Int):AvlTree {
        val node = Node(value)
        root?.let {
            root = it.avlInsert(node)
        } ?: run {
            root = node
        }
        return this
    }

    fun delete(value: Int) {
        //root = root?.avlDelete(value) ?: return
    }
/*
    private fun Node.avlDelete(value: Int): Node? {
        val node = search(value) ?: return null
        val root = node.isRoot()

        val parent = node.Parent
        val newParent = node.delete(::avlInsert) ?: parent
        newParent


    }

 */

}

private fun Node.avlInsert(node:Node):Node {
    when {
        this.Value > node.Value -> Left?.avlInsert(node) ?: run {
            Left = node
            node.Parent = this
        }

        this.Value < node.Value -> Right?.avlInsert(node) ?: run {
            Right = node
            node.Parent = this
        }

        else -> error("dublication isn't allowed")
    }

    println("V($Value).balance: $balance")
    return when {
        balance > 1 && Value < Left!!.Value -> rightRotation()
        balance < -1 && Value > Right!!.Value -> leftRotation()
        balance > 1 && Value > Left!!.Value -> {
            Left!!.leftRotation()
            rightRotation()
        }

        balance < -1 && Value < Right!!.Value -> {
            Right!!.rightRotation()
            leftRotation()
        }
        else -> this
    }
}

fun Node.switchParent(parent:Node) {
    when {
        isRoot() -> {}
        isRight() -> Parent!!.Right = parent
        isLeft() -> Parent!!.Left = parent
    }
    parent.Parent = Parent
    Parent = parent
}

fun Node.rightRotation() = Left?.let { left ->
        switchParent(left)
        Left = null
        left.Right?.let {
            this.Left = it
            it.Parent = this
        }
        left.Right = this
        left
    } ?: error("right rotate expects left child")

fun Node.leftRotation() = Right?.let { right ->
        switchParent(right)
        Right = null
        right.Left?.let {
            it.Parent = this
            Right = it
        }
        right.Left = this
        right
    } ?: error("left rotation expects right child")
