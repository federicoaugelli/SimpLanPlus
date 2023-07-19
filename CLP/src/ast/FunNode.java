package ast;

import evaluator.SimpLanPluslib;
import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class FunNode implements Node {
	private String id;
	private Type returntype ; 
	private ArrayList<ParamNode> parlist ;
	private Node body ;
	private ArrowType type ;
	private String flabel ;
  
	public FunNode(String _id, Type _type, ArrayList<ParamNode> _parlist, Node _body) {
		id = _id ;
		returntype = _type;
		parlist = _parlist ;
		body = _body ;
	}

	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {

		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		
		if (ST.lookup(id) != null)
			errors.add(new SemanticError("Identifier " + id + " already declared"));
		else {
			HashMap<String,STentry> HM = new HashMap<String,STentry>() ;
			ArrayList<Type> partypes = new ArrayList<Type>() ;
		
			ST.add(HM);
			
			for (ParamNode arg : parlist){
    	  			partypes.add(arg.getType());
    	  			if (ST.top_lookup(arg.getId()))
    	  					errors.add(new SemanticError("Parameter id " + arg.getId() + " already declared")) ;
    	  			else {
						  ST.insert(arg.getId(), arg.getType(), _nesting+1, "") ;
						  ST.lookup(arg.getId()).setInit(true);
					}
    	  		}

			type = new ArrowType(partypes, returntype) ;

			//implementazione della ricorsione interna
			flabel = SimpLanPluslib.freshFunLabel() ;

			ST.insert(id, type, _nesting+1, flabel) ;

			errors.addAll(body.checkSemantics(ST, _nesting+1));
			ST.remove();

			ST.insert(id, type, _nesting, flabel) ;
		}
		return errors ;
	}
  
 	public Type typeCheck (ArrayList _e) {

		if ( (body.typeCheck(_e)).getClass().equals(returntype.getClass()))
    			return returntype ;
		else {
			System.out.println("Wrong return type for function "+id);
			ErrorType e = new ErrorType();
			_e.add(e);
			return e ;
		}  
  	}
  
  public String codeGeneration() {

	  SimpLanPluslib.putCode(
	    			flabel + ":\n"
	    			+ "pushr RA \n"
	    			+ body.codeGeneration()
	    			+ "popr RA \n"
	    			+ "addi SP " + 	parlist.size() + "\n"
					+ "pop\n"
	    			+ "store FP 0(FP) \n"
	    			+ "move FP AL \n"
	    			+ "subi AL 1 \n"
					+ "pop\n"
	    			+ "rsub RA \n" 
	    		);
	    
		return "push "+ flabel +"\n";
  }
  
  public String toPrint(String s) {
		String parlstr="";
		for (Node par:parlist){
		  parlstr += par.toPrint(s);
		}
	    return s+"Fun:" + id +"\n"
			   +parlstr
		   	   + "\n" 
	           +body.toPrint(s+"  ") ; 
	  }
	  
}  