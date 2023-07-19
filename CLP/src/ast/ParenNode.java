package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class ParenNode implements Node {
    private Node parbody ;

    public ParenNode(Node _parbody) {
        parbody = _parbody ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(parbody.checkSemantics(ST, _nesting));

        return errors;
    }

    public Type typeCheck(ArrayList _e) {
        return parbody.typeCheck(_e) ;
    }

    public String codeGeneration() {
        return	parbody.codeGeneration() ;
    }

    public String toPrint(String s) {
        return s+"Parenthesis\n" + parbody.toPrint(s+"  ") ;
    }


}