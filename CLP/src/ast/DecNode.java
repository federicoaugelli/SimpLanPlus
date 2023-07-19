package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class DecNode implements Node {
	private String id;
	private Node type;
	
	public DecNode(String _id, Node _type) {
		id = _id ;
		type = _type ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
   		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        Boolean noErrors = true;

		if (type instanceof VoidType) {
			noErrors = false;
			errors.add(new SemanticError("Type of id " + id + " cannot be void"));
		}
        if (ST.top_lookup(id)) {
			noErrors = false;
			errors.add(new SemanticError("Var id " + id + " already declared"));
		}

        if (noErrors) {
			ST.insert(id, (Type) type, _nesting, "");
		}
 
        return errors ;
	}
  
	public Type typeCheck (ArrayList _e) {
		return null ;
	}


	public String codeGeneration() {
		String offset = "1"; //l'offset in ST viene sempre incrementato di 1
		return	"subi SP " + offset + "\n";
	}


    
	public String toPrint(String s) {
		return s + "Var:" + id + type.toPrint(" ") + "\n" ;
	}

}  