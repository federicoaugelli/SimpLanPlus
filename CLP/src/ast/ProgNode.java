package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class ProgNode implements Node {
	private Node exp;
  
	public ProgNode(Node _exp) {
		exp = _exp ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {		
		return exp.checkSemantics(ST, _nesting);
	}

	public Type typeCheck(ArrayList _e) {
		return exp.typeCheck(_e);
	}  
  
	public String codeGeneration() {
		return  "move SP FP\n"
				+ "pushr FP\n"
				+ "move SP AL\n"
				+ "pushr AL\n"
				+ exp.codeGeneration();
	}  
  
	public String toPrint(String s) {
		return "Simple Program\n" + exp.toPrint("  ") ;
	}

}  