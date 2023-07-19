package ast;

import evaluator.SimpLanPluslib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class MinEqNode implements Node{
    private Node left;
    private Node right;
    public MinEqNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST,_nesting));
        errors.addAll(right.checkSemantics(ST,_nesting));

        return errors;
    }
    @Override
    public Type typeCheck(ArrayList _e) {
        if ((left.typeCheck(_e) instanceof IntType) && (right.typeCheck(_e) instanceof IntType) )
            return new BoolType() ;
        else {
            System.out.println("Type Error: Non Integers in Minor Equal instruction") ;
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
        }
    }

    @Override
    public String codeGeneration() {
        String trueLeq = SimpLanPluslib.freshLabel();
        String endLeq = SimpLanPluslib.freshLabel();

        return	left.codeGeneration()
                + "pushr A0 \n"
                + right.codeGeneration()
                + "popr T1 \n"
                + "bleq T1 A0 "+ trueLeq +"\n"
                + "storei A0 0\n"
                + "b " + endLeq + "\n"
                + trueLeq + ":\n"
                + "storei A0 1\n"
                + endLeq + ":\n";
    }

    @Override
    public String toPrint(String s) {
        return s+"Minor Equal\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ") ;
    }
}
