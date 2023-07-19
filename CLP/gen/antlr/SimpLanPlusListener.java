// Generated from C:/Users/Lorenzo/IdeaProjects/CLP/src\SimpLanPlus.g4 by ANTLR 4.12.0
package gen.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpLanPlusParser}.
 */
public interface SimpLanPlusListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code progExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProgExp(SimpLanPlusParser.ProgExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code progExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProgExp(SimpLanPlusParser.ProgExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code progFull}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProgFull(SimpLanPlusParser.ProgFullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code progFull}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProgFull(SimpLanPlusParser.ProgFullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code asg}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterAsg(SimpLanPlusParser.AsgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code asg}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitAsg(SimpLanPlusParser.AsgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterFunStm(SimpLanPlusParser.FunStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitFunStm(SimpLanPlusParser.FunStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andOr}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAndOr(SimpLanPlusParser.AndOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andOr}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAndOr(SimpLanPlusParser.AndOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNot(SimpLanPlusParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNot(SimpLanPlusParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterParen(SimpLanPlusParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitParen(SimpLanPlusParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compare}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCompare(SimpLanPlusParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCompare(SimpLanPlusParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMin}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPlusMin(SimpLanPlusParser.PlusMinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMin}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPlusMin(SimpLanPlusParser.PlusMinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code var}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterVar(SimpLanPlusParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code var}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitVar(SimpLanPlusParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code true}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterTrue(SimpLanPlusParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code true}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitTrue(SimpLanPlusParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code false}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFalse(SimpLanPlusParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code false}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFalse(SimpLanPlusParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFunExp(SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFunExp(SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterInt(SimpLanPlusParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitInt(SimpLanPlusParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDiv}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(SimpLanPlusParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDiv}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(SimpLanPlusParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#branchStm}.
	 * @param ctx the parse tree
	 */
	void enterBranchStm(SimpLanPlusParser.BranchStmContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#branchStm}.
	 * @param ctx the parse tree
	 */
	void exitBranchStm(SimpLanPlusParser.BranchStmContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#branchExp}.
	 * @param ctx the parse tree
	 */
	void enterBranchExp(SimpLanPlusParser.BranchExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#branchExp}.
	 * @param ctx the parse tree
	 */
	void exitBranchExp(SimpLanPlusParser.BranchExpContext ctx);
}