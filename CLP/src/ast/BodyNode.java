package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class BodyNode implements Node {

    private ArrayList<Node> dec ;
    private ArrayList<Node> stm;
    private Node exp ;

    public BodyNode(ArrayList<Node> _dec, ArrayList<Node> _stm, Node _exp) {
        dec = _dec ;
        stm = _stm ;
        exp = _exp ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {

        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        for (Node d : dec) {
            errors.addAll(d.checkSemantics(ST, _nesting));
        }

        for (Node s : stm) {
            errors.addAll(s.checkSemantics(ST, _nesting));
        }

        if (exp != null) {
            errors.addAll(exp.checkSemantics(ST, _nesting)) ;
        }

        return errors;
    }

    public Type typeCheck (ArrayList _e) {
        for (Node d: dec)
            d.typeCheck(_e);
        for (Node s: stm)
            s.typeCheck(_e);
        if (exp != null) { return exp.typeCheck(_e); }
        else {return new VoidType();}
    }


    public String codeGeneration() {
        String decCode="";
        for (Node d: dec)
            decCode += d.codeGeneration();
        String stmCode = "";
        for (Node s: stm)
            stmCode += s.codeGeneration();
        String expCode="";
        if (exp != null) {
            expCode = exp.codeGeneration();
        }
        return  decCode
                + stmCode
                + expCode
                + "addi SP " + dec.size() +"\n";
    }



    public String toPrint(String s) {
        String decstr="";
        if (dec != null) {
            for (Node d : dec) {
                decstr += d.toPrint(s + "\t");
            }
            decstr += "\n";
        }
        String stmstr="";
        if (stm != null) {
            for (Node st : stm) {
                stmstr += st.toPrint(s + "\t");
            }
            stmstr += "\n";
        }
        String expstr = "";
        if (exp != null) {
            expstr = exp.toPrint(s+"\t");
        }
        return s+"Body\n" + decstr + stmstr + expstr ;
    }
}
