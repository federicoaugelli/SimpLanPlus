package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class BoolNode implements Node {

	private Boolean val;
  
	public BoolNode(Boolean _val) {
		val = _val ;
	}

	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		return new ArrayList<SemanticError>();
 	}

	public Type typeCheck(ArrayList _e) {
		return new BoolType();
	}    
    
	public String codeGeneration() {
		return "storei A0 "+(val?1:0)+"\n";
	}
    
	public String toPrint(String s) {
		return s + String.valueOf(val) +"\n";  
	}

}  