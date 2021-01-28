// Generated from src/Calculator.g by ANTLR 4.8
package calculator;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalculatorParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CalculatorParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CalculatorParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculatorParser#exprM}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprM(CalculatorParser.ExprMContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculatorParser#exprP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprP(CalculatorParser.ExprPContext ctx);
}