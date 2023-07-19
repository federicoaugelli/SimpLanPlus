package ast;

import evaluator.SimpLanPluslib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class MinorNode implements Node{
    private Node left;
    private Node right;

    public MinorNode(Node _left, Node _right) {
        left = _left ;
        right = _right ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    @Override
    public Type typeCheck(ArrayList _e) {
        if ((left.typeCheck(_e) instanceof IntType) && (right.typeCheck(_e) instanceof IntType) )
            return new BoolType() ;
        else {
            System.out.println("Type Error: Non Integers in Minor instruction") ;
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
        }
    }

    @Override
    public String codeGeneration() {
        String falseLt = SimpLanPluslib.freshLabel();
        String endLt = SimpLanPluslib.freshLabel();
        return	left.codeGeneration()
                + "pushr A0 \n"
                + right.codeGeneration()
                + "popr T1 \n"
                + "beq A0 T1 " + falseLt + "\n"
                + "bleq A0 T1 "+ falseLt +"\n"
                + "storei A0 1\n"
                + "b " + endLt + "\n"
                + falseLt + ":\n"
                + "storei A0 0\n"
                + endLt + ":\n";
    }

    @Override
    public String toPrint(String s) {
        return s+"Minor\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ") ;
    }
}
