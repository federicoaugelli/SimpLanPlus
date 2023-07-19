package ast;

import evaluator.SimpLanPluslib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class AndNode implements Node {
    private Node left;
    private Node right;

    public AndNode(Node _left, Node _right) {
        left = _left ;
        right = _right;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    public Type typeCheck(ArrayList _e) {
        if ((left.typeCheck(_e) instanceof BoolType) && (right.typeCheck(_e) instanceof BoolType) )
            return new BoolType() ;
        else {
            System.out.println("Type Error: Non Boolean type(s) in And instruction") ;
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
        }
    }

    public String codeGeneration() {
        String lazyfalse = SimpLanPluslib.freshLabel();
        String exitAnd = SimpLanPluslib.freshLabel();
        return	left.codeGeneration()
                + "storei T1 0\n"
                + "beq A0 T1 " + lazyfalse + "\n"
                + right.codeGeneration()
                + "storei T1 0\n"
                + "beq A0 T1 " + lazyfalse + "\n"
                + "storei A0 1\n"
                + "b " + exitAnd + "\n"
                + lazyfalse + ":\n"
                + "storei A0 0\n"
                + exitAnd + ":\n";
    }

    public String toPrint(String s) {
        return s+"And\n" + left.toPrint(s+"  ")  + right.toPrint(s+"  ") ;
    }


}
