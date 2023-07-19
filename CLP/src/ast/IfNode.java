package ast;

import java.util.ArrayList;

import evaluator.SimpLanPluslib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class IfNode implements Node {
	private Node guard ;
	private ArrayList<Node> thenbranch ;
	private ArrayList<Node> elsebranch ;
	private ArrayList<AsgNode> checkinit;
	private ArrayList<Boolean> checkbool;
	private Boolean isExp;
  
	public IfNode (Node _guard, ArrayList<Node> _thenbranch, ArrayList<Node> _elsebranch, Boolean _isExp) {
    	guard = _guard ;
    	thenbranch = _thenbranch ;
    	elsebranch = _elsebranch ;
		isExp = _isExp;
		//checkinit = new ArrayList<AsgNode>();
		//checkbool = new ArrayList<Boolean>();
  }
  
   @Override
  public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
	   ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
	  
	   errors.addAll(guard.checkSemantics(ST, _nesting));

	   checkinit = new ArrayList<AsgNode>();
	   checkbool = new ArrayList<Boolean>();

	   //entra ramo then
	   ST.setThenBranch(true);

	   for(Node tb : thenbranch){
		   if (tb instanceof AsgNode) {
			   ((AsgNode) tb).setThenInit(true);
		 	   checkinit.add((AsgNode) tb);
		 	   checkbool.add(true);
		   }
		   errors.addAll(tb.checkSemantics(ST, _nesting));
	   }

	   for(Node tb : thenbranch) {
		   if (tb instanceof AsgNode) {
			   ((AsgNode) tb).setThenInit(false);
		   }
	   }

	  //passa a ramo else
	   ST.setThenBranch(false);
	   ST.setElseBranch(true);


	  for(Node eb : elsebranch){
		  if (eb instanceof AsgNode) {
			  ((AsgNode) eb).setElseInit(true);
			  checkinit.add((AsgNode) eb);
			  checkbool.add(false);
		  }
		  errors.addAll(eb.checkSemantics(ST, _nesting));
	  }

	   for(Node eb : elsebranch) {
		   if (eb instanceof AsgNode) {
			   ((AsgNode) eb).setElseInit(false);
		   }
	   }

	  //fine if
	   ST.setElseBranch(false);

	  int i = 0;
	  for(AsgNode c : checkinit){
		  c.checkInit(ST, checkbool.get(i));
		  i++;
	  }

	  return errors;
  }

	@Override
	public Type typeCheck(ArrayList _e) {
		if (guard.typeCheck(_e) instanceof BoolType ) {
			Type thentype = null;
			Type elsetype = null;
			for (Node tb: thenbranch) {
				thentype = tb.typeCheck(_e);
			}
			for (Node eb: elsebranch) {
				elsetype = eb.typeCheck(_e);
			}

			if (isExp) {
				if ((thentype != null) && (elsetype != null)) {
					if (elsetype.getClass().equals(thentype.getClass())) {
						return thentype;
					} else {
						System.out.println("Type Error: If branches have different types");
						ErrorType e = new ErrorType();
						_e.add(e);
						return e;
					}
				}
				else {
					ErrorType e = new ErrorType();
					if (thentype == null) {
						System.out.println("Type Error: invalid type for Then branch in If statement");
						_e.add(e);
					}
					if (elsetype == null) {
						System.out.println("Type Error: invalid type for Else branch in If statement");
						_e.add(e);
					}
					return e;
				}
			}
			else {
				return null;
			}
		}
		else {
			System.out.println("Type Error: If condition is not a Boolean") ;
			ErrorType e = new ErrorType();
			_e.add(e);
			return e ;
		}
	}

	@Override
	public String codeGeneration() {
		String lthen = SimpLanPluslib.freshLabel();
		String lend = SimpLanPluslib.freshLabel();
		String thenCode = "";
		for(Node tb : thenbranch){
			thenCode += tb.codeGeneration();
		}
		String elseCode = "";
		for(Node eb : elsebranch){
			elseCode += eb.codeGeneration();
		}
		return  guard.codeGeneration()
				+ "storei T1 1 \n"
				+ "beq A0 T1 "+ lthen + "\n"
				+ elseCode
				+ "b " + lend + "\n"
				+ lthen + ":\n"
				+ thenCode
				+ lend + ":\n" ;
	}

	@Override
	public String toPrint(String s) {
		String thenstr = "";
		for (Node tb: thenbranch) {
			thenstr += tb.toPrint(s+"  ");
		}
		String elsestr = "";
		for (Node eb: elsebranch) {
			elsestr += eb.toPrint(s+"  ");
		}
		return s+"If\n" + guard.toPrint(s+"  ") + thenstr + elsestr ;
	}

}  