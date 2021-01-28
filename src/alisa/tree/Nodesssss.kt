package alisa.tree
import java.lang.Integer.max
import java.util.Scanner;
/*
class main{
    fun main(){
        val scan = Scanner(System.`in`)
        print("А введите-ка корешок")
        var c : Int = scan.nextInt()
        var root : Node? = Node(scan.nextInt(), null,null,null)
        var queue : Int = 1;
        var stat : String = "some"
        while (stat != "exit"){
            print("Что делать будете?" + "" + "find - найти, exit - выйти, delete - удалить, create - создать, findmin - найти наименьший сверху, findmax - найти наибольший снизу. Если хотите серию запросов - queue")
            print("Вывод дерева не завезли :с")
            stat = scan.nextLine()
            if (stat == "queue"){
                print("Введите количество запросов")
                queue = scan.nextInt()
            }
            for (i in 0..queue) {
                if (stat == "find") {
                    var findet = root?.search(scan.nextInt())
                    when{
                        findet == null -> print("Нет, его нет")
                        else -> print("Ого, он есть")
                    }
                }

                if (stat == "delete") {
                    root?.delete(scan.nextInt())
                }

                if (stat == "create") {
                    root?.insert(scan.nextInt())
                }

                if (stat == "findmin") {
                    var c = scan.nextInt()
                    var findet: Node? = root?.min_finder(c)
                    when{
                        findet == null -> print("Ой, его, похоже, не существует :(")
                        else -> print("Смотрите, вот он -> " + findet.Value)
                    }
                }

                if (stat == "findmax") {
                    var c = scan.nextInt()
                    var findet: Node? = root?.max_finder(c)
                    when{
                        findet == null -> print("Ой, его, похоже, не существует :(")
                        else -> print("Смотрите, вот он -> " + findet.Value)
                    }
                }
            }
            queue = 1
        }

    }
}
*/
class Node(val Value: Int = 0, var Left: Node? = null, var Right: Node? = null, var Parent: Node? = null) {


    fun insert(value: Int) :Node = apply {
        when {
            (value > Value) -> Right?.insert(value) ?: run {
                Right = Node(value, null, null, this)
            }
            (value < Value) -> Left?.insert(value) ?: run {
                Left = Node(value, null, null, this)
            }
        }
    }

    fun delete(value : Int):Node? {
        return null
        //return search(value)?.delete()
    }
/*
    fun delete(ins:Node.(Node) -> Unit = ::insert):Node? = when {
        isLeaf() && isLeft() -> {Parent!!.Left = null; null}
        isLeaf() && isRight() -> {Parent!!.Right = null ; null}
        !isRoot() -> {
            if (isRight()) Parent!!.Right = null
            if (isLeft()) Parent!!.Left = null
            Left?.let{Parent!!.ins(it)}
            Right?.let{Parent!!.ins(it)}
            null
        }
        else -> Left?.let { left -> Right?.let{ left.insert(it)} } ?: Right
    } as Node?
*/
    fun insert(node: Node):Unit = when{
        node.Value > Value -> {
            Right?.let {
                it.insert(node)
            } ?: run {
                Right = node
                node.Parent = this
            }
        }
        node.Value < Value -> {
            Left?.let {
                it.insert(node)
            } ?: run {
                Left = node
                node.Parent = this
            }
        }
        else -> TODO("already have this node")
    }

    fun isLeaf() = Left == null && Right == null
    fun isLeft() = Parent?.Left == this
    fun isRight() = Parent?.Right == this
    fun isRoot() = Parent == null



    fun<T> acceptChildren(data:T, visitor:Node.(T)->T):T {
        Left?.accept(data, visitor)
        Right?.accept(data, visitor)
        return data
    }

    fun<T> accept(data:T, visitor:Node.(T)->T):T {
        visitor(data)
        return acceptChildren(data, visitor)
    }

    fun children():List<Int> {
        val data = mutableListOf<Int>()
        acceptChildren(data) {it.also {  it.add(Value) } }
        return data
    }

    fun search(value : Int) : Node? = when {
                value > Value -> Right?.search(value)
                value < Value -> Left?.search(value)
                value == Value -> this
                else -> null
            }

    fun min_finder(value : Int) = search(value) ?: upperMinimum(value)

    private fun upperMinimum(value: Int):Node? = when {
        value > Value -> Right?.upperMinimum(value)
        value < Value -> Left?.upperMinimum(value) ?: this
        else -> this
    }

    fun max_finder( value : Int) = search(value) ?: bottomMaximum(value)

    private fun bottomMaximum(value: Int): Node? = when {
        value < Value -> Left?.bottomMaximum(value)
        value > Value -> Right?.bottomMaximum(value) ?: this
        else -> this
    }
}

val Node.height : Int
  get() = max(Left?.height ?:0 , Right?.height ?: 0) + 1

val Node.balance : Int
    get() = (Left?.height ?: 0) - (Right?.height ?: 0)

