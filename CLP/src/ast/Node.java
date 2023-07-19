package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public interface Node {

	ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting);
	Type typeCheck(ArrayList _e);
	String codeGeneration();

	String toPrint(String s);

}  