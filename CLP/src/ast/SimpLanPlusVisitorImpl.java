package ast;

import gen.antlr.SimpLanPlusBaseVisitor;
import gen.antlr.SimpLanPlusParser;

import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    @Override
    public Node visitProgExp(SimpLanPlusParser.ProgExpContext ctx) {
        return new ProgNode(visit(ctx.exp()));
    }
    @Override
    public Node visitProgFull(SimpLanPlusParser.ProgFullContext ctx) {
        ArrayList<Node> _dec = new ArrayList<Node>() ;
        for (SimpLanPlusParser.DecContext d : ctx.dec())
            _dec.add( visit(d) );

        ArrayList<Node> _stm = new ArrayList<Node>() ;
        for (SimpLanPlusParser.StmContext s : ctx.stm())
            _stm.add( visit(s) );

        Node _exp = null;
        if (ctx.exp() != null) {_exp = visit(ctx.exp());}

        return new ProgDecStmNode(_dec, _stm, _exp);
    }
    @Override
    public Node visitVarDec(SimpLanPlusParser.VarDecContext ctx) {
        return new DecNode(ctx.ID().getText(), visit(ctx.type()));
    }
    @Override
    public Node visitFunDec(SimpLanPlusParser.FunDecContext ctx) {
        ArrayList<ParamNode> _param = new ArrayList<ParamNode>() ;

        for (SimpLanPlusParser.ParamContext vc : ctx.param())
            _param.add( new ParamNode(vc.ID().getText(), (Type) visit( vc.type() )) );

        return new FunNode(ctx.ID().getText(), (Type) visit(ctx.type()), _param, visit(ctx.body()));
    }

    @Override
    public Node visitBody(SimpLanPlusParser.BodyContext ctx) {

        ArrayList<Node> _dec = new ArrayList<Node>() ;
        for (SimpLanPlusParser.DecContext d : ctx.dec())
            _dec.add( visit(d) );

        ArrayList<Node> _stm = new ArrayList<Node>() ;
        for (SimpLanPlusParser.StmContext s : ctx.stm())
            _stm.add( visit(s) );

        Node _exp = null ;
        if (ctx.exp() != null) {_exp = visit(ctx.exp());}

        return new BodyNode(_dec, _stm,_exp);
    }
    @Override
    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        if(ctx.getText().equals("int"))
            return new IntType();
        else if(ctx.getText().equals("bool"))
            return new BoolType();
        else return new VoidType();
    }
    @Override
    public Node visitAsg(SimpLanPlusParser.AsgContext ctx) {
        return new AsgNode(ctx.ID().getText(), visit(ctx.exp()));
    }
    @Override public Node visitFunStm(SimpLanPlusParser.FunStmContext ctx) {
        ArrayList<Node> args = new ArrayList<Node>();

        for (SimpLanPlusParser.ExpContext exp : ctx.exp())
            args.add(visit(exp));

        return new CallNode(ctx.ID().getText(), args, true);

    }
    @Override public Node visitIfStm(SimpLanPlusParser.IfStmContext ctx) {
        ArrayList<Node> _then = new ArrayList<Node>();
        for (SimpLanPlusParser.StmContext s: ctx.thenBranch.stm()) {
            _then.add(visit(s));
        }

        ArrayList<Node> _else = new ArrayList<Node>();
        if (ctx.elseBranch != null) {
            for (SimpLanPlusParser.StmContext s: ctx.elseBranch.stm()) {
                _else.add(visit(s));
            }
        }


        return new IfNode(visit(ctx.cond), _then, _else, false);
    }
    @Override public Node visitAndOr(SimpLanPlusParser.AndOrContext ctx) {
        if (ctx.and != null)
            return new AndNode(visit(ctx.left), visit(ctx.right));
        else return new OrNode(visit(ctx.left), visit(ctx.right));
    }
    @Override public Node visitNot(SimpLanPlusParser.NotContext ctx) {
        return new NotNode(visit(ctx.right));
    }

    @Override public Node visitParen(SimpLanPlusParser.ParenContext ctx) { return new ParenNode(visit(ctx.exp())); }
    @Override public Node visitCompare(SimpLanPlusParser.CompareContext ctx) {
        if(ctx.great != null)
            return new GreaterNode(visit(ctx.left), visit(ctx.right));
        else if(ctx.minor != null)
            return new MinorNode(visit(ctx.left), visit(ctx.right));
        else if(ctx.greateq != null)
            return new GreatEqNode(visit(ctx.left), visit(ctx.right));
        else if(ctx.minoreq != null)
            return new MinEqNode(visit(ctx.left), visit(ctx.right));
        else return new EqualsNode(visit(ctx.left), visit(ctx.right));
    }
    @Override public Node visitPlusMin(SimpLanPlusParser.PlusMinContext ctx) {
        if (ctx.plus != null)
            return new PlusNode(visit(ctx.left), visit(ctx.right));
        else return new MinusNode(visit(ctx.left), visit(ctx.right));
    }
    @Override public Node visitVar(SimpLanPlusParser.VarContext ctx) {
        return new IdNode(ctx.ID().getText());
    }
    @Override public Node visitIfExp(SimpLanPlusParser.IfExpContext ctx) {
        ArrayList<Node> _then = new ArrayList<Node>();
        for (SimpLanPlusParser.StmContext s: ctx.thenBranch.stm()) {
            _then.add(visit(s));
        }
        _then.add(visit(ctx.thenBranch.exp()));

        ArrayList<Node> _else = new ArrayList<Node>();
        for (SimpLanPlusParser.StmContext s: ctx.elseBranch.stm()) {
            _else.add(visit(s));
        }
        _else.add(visit(ctx.elseBranch.exp()));

        return new IfNode(visit(ctx.cond), _then, _else, true);
    }
    @Override public Node visitTrue(SimpLanPlusParser.TrueContext ctx) {
        return new BoolNode(Boolean.TRUE);
    }
    @Override public Node visitFalse(SimpLanPlusParser.FalseContext ctx) {
        return new BoolNode(Boolean.FALSE);
    }
    @Override public Node visitFunExp(SimpLanPlusParser.FunExpContext ctx) {
        ArrayList<Node> args = new ArrayList<Node>();

        for (SimpLanPlusParser.ExpContext exp : ctx.exp())
            args.add(visit(exp));

        return new CallNode(ctx.ID().getText(), args, false);
    }
    @Override public Node visitInt(SimpLanPlusParser.IntContext ctx) {
        return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
    }
    @Override public Node visitMulDiv(SimpLanPlusParser.MulDivContext ctx) {
        if(ctx.mul != null)
            return new MultNode(visit(ctx.left), visit(ctx.right));
        else return new DivNode(visit(ctx.left), visit(ctx.right));
    }
}
