// Generated from src/Calculator.g by ANTLR 4.8
package calculator;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalculatorParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalculatorParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CalculatorParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CalculatorParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#exprM}.
	 * @param ctx the parse tree
	 */
	void enterExprM(CalculatorParser.ExprMContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#exprM}.
	 * @param ctx the parse tree
	 */
	void exitExprM(CalculatorParser.ExprMContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#exprP}.
	 * @param ctx the parse tree
	 */
	void enterExprP(CalculatorParser.ExprPContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#exprP}.
	 * @param ctx the parse tree
	 */
	void exitExprP(CalculatorParser.ExprPContext ctx);
}