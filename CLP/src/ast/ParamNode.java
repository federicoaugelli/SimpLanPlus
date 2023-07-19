package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class ParamNode implements Node {

  private String id;
  private Type type;

  public ParamNode(String _id, Type _type) {
   id = _id ;
   type = _type ;
  }
  
  public String getId(){
	  return id;
  }
  
  public Type getType(){
	  return type;
  }
  
  @Override
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
	  return new ArrayList<SemanticError>();
	}
  
   //non utilizzato
  public Type typeCheck (ArrayList _e) {
     return null;
  }
  
  //non utilizzato
  public String codeGeneration() {
		return "";
  }
  
  public String toPrint(String s) {
	  return s+"Parameter " + id + ": " + type.toPrint(s) ;
  }
  

    
}  