package ast;

import evaluator.SimpLanPluslib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class NotNode implements Node {
    private Node right;

    public NotNode(Node _right) {
        right = _right;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    public Type typeCheck(ArrayList _e) {
        if (right.typeCheck(_e) instanceof BoolType)
            return new BoolType() ;
        else {
            System.out.println("Type Error: Not a Boolean in Not instruction") ;
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
        }
    }

    public String codeGeneration() {
        String lNot = SimpLanPluslib.freshLabel();
        String exitNot = SimpLanPluslib.freshLabel();
        return	right.codeGeneration()
                + "storei T1 0"
                + "beq A0 T1 " + lNot + "\n"
                + "storei A0 0\n"
                + "b " + exitNot + "\n"
                + lNot + ":\n"
                + "storei A0 1\n"
                + exitNot + ":\n";
    }

    public String toPrint(String s) {
        return s+"Not\n" + right.toPrint(s+"  ") ;
    }


}
