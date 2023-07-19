// Generated from C:/Users/Lorenzo/IdeaProjects/CLP/src\SimpLanPlus.g4 by ANTLR 4.12.0
package gen.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.ArrayList;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpLanPlusParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpLanPlusVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code progExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgExp(SimpLanPlusParser.ProgExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code progFull}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgFull(SimpLanPlusParser.ProgFullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(SimpLanPlusParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asg}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsg(SimpLanPlusParser.AsgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunStm(SimpLanPlusParser.FunStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOr}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOr(SimpLanPlusParser.AndOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(SimpLanPlusParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(SimpLanPlusParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(SimpLanPlusParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusMin}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMin(SimpLanPlusParser.PlusMinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(SimpLanPlusParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code true}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(SimpLanPlusParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code false}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(SimpLanPlusParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExp(SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(SimpLanPlusParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDiv}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(SimpLanPlusParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#branchStm}
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
    T visitBranchStm(SimpLanPlusParser.BranchStmContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#branchExp}
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchExp(SimpLanPlusParser.BranchExpContext ctx);
}