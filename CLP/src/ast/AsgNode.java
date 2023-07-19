package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class AsgNode implements Node {
    private String id;
    private Type type;
    private int asgNesting;
    private int varNesting;
    private String offset;
    private Node exp;
    private Boolean thenInit;
    private Boolean elseInit;

    public AsgNode(String _id, Node _exp) {
        id = _id ;
        exp = _exp;
        thenInit = false;
        elseInit = false;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        asgNesting = _nesting;

        errors.addAll(exp.checkSemantics(ST, _nesting)) ;

        if (ST.lookup(id) == null) {
            errors.add(new SemanticError("Var id " + id + " not declared"));
        } else {

            if(thenInit) {
                ST.lookup(id).setThenInit(true);
                type = ST.lookup(id).gettype();
                varNesting = ST.lookup(id).getnesting();
                offset = String.valueOf(ST.lookup(id).getoffset());
            }
            else if (elseInit) {
                ST.lookup(id).setElseInit(true);
                type = ST.lookup(id).gettype();
                varNesting = ST.lookup(id).getnesting();
                offset = String.valueOf(ST.lookup(id).getoffset());
            }
            else {
                type = ST.lookup(id).gettype();
                varNesting = ST.lookup(id).getnesting();
                offset = String.valueOf(ST.lookup(id).getoffset());

                if (!ST.lookup(id).getInit()) {
                    ST.lookup(id).setInit(true);
                }
            }
        }
        return errors ;
    }

    public void checkInit(SymbolTable ST, Boolean t) {
        if (ST.lookup(id).getThenInit() && ST.lookup(id).getElseInit()) {

            if (!ST.lookup(id).getInit()) {
                ST.lookup(id).setInit(true);
            }

            ST.lookup(id).setThenInit(false);
            ST.lookup(id).setElseInit(false);
        }

        if (t) {
            ST.lookup(id).setThenInit(false);
        }
        else {
            ST.lookup(id).setElseInit(false);
        }
    }

    public Type typeCheck (ArrayList _e) {
        if (exp.typeCheck(_e).getClass().equals(type.getClass()))
            return null ;
        else {
            System.out.println("Type Error: incompatible type of expression for variable "+id) ;
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
        }
    }


    public String codeGeneration() {
        String getAR="";
        for (int i=0; i < asgNesting - varNesting; i++)
            getAR += "store T1 0(T1)\n";
        return exp.codeGeneration()
                + "move AL T1\n"
                + getAR
                + "load A0 -" + offset + "(T1)\n";
    }

    public String toPrint(String s) {
        return s + "Assign to " + id + " with value " + exp.toPrint(s);
    }

    public void setThenInit(Boolean _init) {thenInit = _init;}
    public void setElseInit(Boolean _init) {elseInit = _init;}

}
