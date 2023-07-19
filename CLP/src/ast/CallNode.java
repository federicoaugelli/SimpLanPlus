package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class CallNode implements Node {
	private String id ;
	private STentry entry ; 
	private ArrayList<Node> parameters ; 
	private int nesting ;

	private Boolean isStm;

  	public CallNode(String _id, ArrayList<Node> _parameters, Boolean _isStm) {
		id = _id;
		parameters = _parameters ;
		isStm = _isStm;
	}

	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting ;
		STentry tmp = ST.lookup(id) ;
		if (tmp != null) {
			if(tmp.gettype() instanceof ArrowType ) {
				entry = tmp;
				for (Node par : parameters)
					errors.addAll(par.checkSemantics(ST, nesting));
			}else{
				System.out.println("Wrong usage of function");
			}
		} else {
			errors.add(new SemanticError("Id " + id + " not declared")) ;
		}
		return errors;
  	}
  
	public Type typeCheck(ArrayList _e) {
		Type _type = entry.gettype() ;
		if (_type instanceof ArrowType) {			 
			ArrayList<Type> _partype = ((ArrowType) _type).get_inputtype();
			if ( _partype.size() != parameters.size() ) {
				System.out.println("Wrong number of parameters in the invocation of "+id);
				return new ErrorType() ;
			} else {
				boolean ok = true ;
				for (int i = 0 ; i < parameters.size() ; i++) {
					Type par_i = (parameters.get(i)).typeCheck(_e) ;
					if ( !(par_i.getClass().equals(_partype.get(i).getClass()) )) {
							System.out.println("Wrong type for "+(i+1)+"-th parameter in the invocation of "+id);
							ok = false ;
					} 
				}
				if (ok) {
					if (isStm) {
						return null;
					}
					else {
						return ((ArrowType) _type).get_outputtype();
					}
				}
				else {
					ErrorType e = new ErrorType();
					_e.add(e);
					return e ;
				}
			} 
		} else {
				System.out.println("Invocation of a non-function "+id) ;
				ErrorType e = new ErrorType();
				_e.add(e);
				return e ;
		}
	}
  
  public String codeGeneration() {
	    String parCode="";
	    for (int i = 0; i < parameters.size() ; i = i+1)
	    		parCode += parameters.get(i).codeGeneration() + "pushr A0\n" ;
	    
	    String getAR="";
		for (int i=0; i < nesting - entry.getnesting() ; i++) 
		    	getAR+="store T1 0(T1) \n";

	  	return "pushr FP\n"
				+ "move SP FP \n"
				+ "addi FP 1 \n"
				+ "move AL T1\n"
				+ getAR
				+ "pushr T1\n"
				+ parCode
				+ "move FP AL \n"
				+ "subi AL 1\n"
				+ "jsub " + entry.getlabel() + "\n" ;

  }

	public String toPrint(String s) {  //
	    String parlstr="";
		for (Node par : parameters)
			parlstr+=par.toPrint(s+"  ");		
		return s+"Call:" + id + " at nestlev " + nesting  + " with parameters: " +  parlstr +"\n" + entry.gettype().toPrint(s+"  ") ;
	} 
}  