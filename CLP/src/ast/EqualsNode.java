package ast;

import evaluator.SimpLanPluslib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class EqualsNode implements Node {
    private Node left ;
    private Node right ;

    public EqualsNode(Node _left, Node _right) {
        left = _left ;
        right = _right ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    public Type typeCheck(ArrayList _e) {
        if (left.typeCheck(_e).getClass().equals(right.typeCheck(_e).getClass()))
            return new BoolType() ;
        else {
            System.out.println("Type Error: Different types in equality") ;
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
        }
    }

    public String codeGeneration() {
        String trueEq = SimpLanPluslib.freshLabel();
        String endEq = SimpLanPluslib.freshLabel();
        return	left.codeGeneration()
                + "pushr A0 \n"
                + right.codeGeneration()
                + "popr T1 \n"
                + "beq A0 T1 "+ trueEq +"\n"
                + "storei A0 0\n"
                + "b " + endEq + "\n"
                + trueEq + ":\n"
                + "storei A0 1\n"
                + endEq + ":\n";
    }

    public String toPrint(String s) {
        return s+"Equal\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ") ;
    }
}