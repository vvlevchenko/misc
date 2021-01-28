class Node(val value:Int, var left:Node? = null, var right:Node? = null, var parent:Node? = null)

fun Node?.length():Int = this?.let{ (left?.run(Node::length) ?: 1) + (right?.run(Node::length) ?: 0)} ?: 0

fun Node.delete(child:Node?) {
    child?.let {
        val left = child.leftFind()
        left?.let {
            left.parent?.left = left.right
            left.right?.parent = left.parent?.left

            left.left = child.left
            left.right = child.right
            left.parent = child.parent
        }
        val right = child.rightFind()
        right?.let {
            right.parent?.right = right.right
            right.left?.parent = right.parent?.left

            right.left = child.left
            right.right = child.right
            right.parent = child.parent
        }
    } ?: return
}

fun Node?.leftFind():Node? = null
fun Node?.rightFind():Node? = null
/**
 * if (toDelete.Parent.Left == toDelete){
val left = left_finder(toDelete)
left?.let{
left.Parent.Left = left.Right
left.Right.Parent = left.Parent.Left

left.Left = toDelete.Left
left.Right = toDelete.Right
left.Parent = toDelete.Parent
}
}
 * */


//Node()