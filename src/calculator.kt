import calculator.CalculatorBaseVisitor
import calculator.CalculatorLexer
import calculator.CalculatorParser
import calculator.CalculatorParser.*
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

sealed class CalculatorNode
class ProgramNode:CalculatorNode() {
    val expressions = mutableListOf<EExpression>()
}
class EExpression(val parent: ProgramNode):CalculatorNode() {
    val operands = mutableListOf<MExpression>()
}

class MExpression(val parent:EExpression):CalculatorNode() {
    val operands = mutableListOf<CalculatorNode>()
}

class PExpression(val parent: MExpression):CalculatorNode() {}

class ValueNode(val value:Long): CalculatorNode()
abstract class Operation(): CalculatorNode()
open class BinaryOperation(): Operation() {
    var left: CalculatorNode? = null
    var right:CalculatorNode? = null
}

class PlusOperation(): BinaryOperation()

fun main() {
    val lexer = CalculatorLexer(ANTLRInputStream("1 + 2 + 4"))
    val parser = CalculatorParser(CommonTokenStream(lexer))
    val v = parser.program().accept(object : CalculatorBaseVisitor<CalculatorNode>() {
        private val program = ProgramNode()
        private var currentEExpression: EExpression? = null
        override fun visitProgram(ctx: ProgramContext?) = program
        override fun visitExpr(ctx: ExprContext?):CalculatorNode  {
            currentEExpression = EExpression(program)
            return currentEExpression as CalculatorNode
        }
        override fun visitExprM(ctx: ExprMContext?): CalculatorNode {
            currentEExpression?.run {
                //ctx.
            }
            TODO()
        }
    })
    /*
    val v = parser.program().accept(object : AbstractParseTreeVisitor<CalculatorNode>() {
        val visitor = this
        private var result:CalculatorNode? = null
        private var program:ProgramNode? = null
        override fun defaultResult() = result

        override fun aggregateResult(aggregate: CalculatorNode?, nextResult: CalculatorNode?): CalculatorNode {
            println("aggregateResult($aggregate, $nextResult)")
            return when {
                aggregate is MExpression && nextResult is ValueNode -> aggregate.apply { operands.add(nextResult) }
                aggregate is EExpression && nextResult is MExpression -> aggregate.apply { operands.add(nextResult) }
                aggregate is ProgramNode && nextResult is EExpression -> aggregate.apply { expressions.add(nextResult) }
                else -> TODO("$aggregate $nextResult")
            }
        }


        override fun shouldVisitNextChild(node: RuleNode?, currentResult: CalculatorNode?): Boolean {
            println("shouldVisitNextChild: ${node!!::class.java} ${currentResult}")
            when(node) {
                is ProgramContext -> {
                    //program = ProgramNode()
                    //result = program
                }
                is ExprContext -> {
                    //result = EExpression()
                    //program?.expressions?.add(result as EExpression) ?: TODO("error here")
                }
                is ExprMContext -> {
                    //val eExpression = result as EExpression
                    //val mExpression = MExpression(eExpression)
                    //(eExpression).operands.add(mExpression)
                    //result = mExpression
                }
                is ExprPContext -> {
                    //val mExpression = result as MExpression
                    //val pExpression = PExpression(mExpression)
                    //mExpression.operands.add(pExpression)
                    //result = pExpression
                }
                is TerminalNode -> {
                    println("terminal node: ${node.javaClass}")
                    when (node.symbol.type) {
                        EOF -> return false
                    }
                }
            }
            return true
        }

        override fun visitTerminal(node: TerminalNode?): CalculatorNode {
            return when (node?.symbol?.type) {
                Digit -> return ValueNode(node.symbol.text.toLong())
                PLUS -> PlusOperation()
                else -> TODO()
            }
        }
    })*/
    println (v)
}

private fun <E> MutableList<E>.push(v: E) = add(v)
private fun <E> MutableList<E>.pop(): E? = lastOrNull()?.apply {
    this@pop.removeAt(this@pop.size - 1)
}
