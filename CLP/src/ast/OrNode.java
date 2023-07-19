package ast;

import java.util.ArrayList;

import evaluator.SimpLanPluslib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class OrNode implements Node {
    private Node left ;
    private Node right ;

    public OrNode (Node _left, Node _right) {
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
        if ((left.typeCheck(_e) instanceof BoolType) && (right.typeCheck(_e) instanceof BoolType) )
            return new BoolType() ;
        else {
            System.out.println("Type Error: Non Boolean type(s) in Or instruction") ;
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
        }
    }

    public String codeGeneration() {
        String lazytrue = SimpLanPluslib.freshLabel();
        String exitOr = SimpLanPluslib.freshLabel();
        return	left.codeGeneration()
                + "storei T1 1\n"
                + "beq A0 T1 " + lazytrue + "\n"
                + right.codeGeneration()
                + "storei T1 1\n"
                + "beq A0 T1 "+ lazytrue +"\n"
                + "storei A0 0\n"
                + "b " + exitOr + "\n"
                + lazytrue + ":\n"
                + "storei A0 1\n"
                + exitOr + ":\n";
    }

    public String toPrint(String s) {
        return s+"Or\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ") ;
    }
}
