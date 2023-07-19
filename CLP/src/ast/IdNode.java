package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class IdNode implements Node {
	private String id ;
	private STentry type ;
	private String offset;
	private int nesting ;
  
	public IdNode(String _id) {
		id = _id ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting ;
		
		STentry st_type = ST.lookup(id) ;

		if (st_type == null)
			errors.add(new SemanticError("Id " + id + " not declared"));

			//controllo inizializzazione
		else {
			if (!ST.lookup(id).getInit()) {

				if (ST.getThenBranch()) {
					if (ST.lookup(id).getThenInit()) {
						type = st_type ;
						offset = String.valueOf(type.getoffset());
					}
					else {
						errors.add(new SemanticError("Id " + id + " not initialized in then branch"));
					}
				}
				else if (ST.getElseBranch()) {
					if (ST.lookup(id).getElseInit()) {
						type = st_type ;
						offset = String.valueOf(type.getoffset());
					}
					else {
						errors.add(new SemanticError("Id " + id + " not initialized in else branch"));
					}
				}

				else {
					errors.add(new SemanticError("Id " + id + " not initialized"));
				}
			}
			else {
				type = st_type ;
				offset = String.valueOf(type.getoffset());
			}

		}

		return errors;
	}
  
	public Type typeCheck (ArrayList _e) {
		if (type.gettype() instanceof ArrowType) { //
			System.out.println("Wrong usage of function identifier");
            ErrorType e = new ErrorType();
            _e.add(e);
            return e ;
		} else return type.gettype() ;
	}
  
	public String codeGeneration() {
		String getAR="";
		for (int i=0; i < nesting - type.getnesting(); i++) 
	    	 getAR += "store T1 0(T1) \n";
	    return "move AL T1\n"
		       + getAR
		       + "store A0 -" + offset + "(T1)\n";

	}

	public String toPrint(String s) {
		return s+"Id:" + id + " at nestlev " + type.getnesting() +"\n" ;  
	}
  
}  