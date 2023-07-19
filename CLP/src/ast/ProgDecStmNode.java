package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class ProgDecStmNode implements Node {
	
	private ArrayList<Node> dec ;
	private ArrayList<Node> stm;
	private Node exp ;
  
	public ProgDecStmNode(ArrayList<Node> _dec, ArrayList<Node> _stm, Node _exp) {
		dec = _dec ;
		stm = _stm ;
		exp = _exp ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {

	      HashMap<String,STentry> H = new HashMap<String, STentry>();
	      ST.add(H);

	      ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
	      
	      for (Node d : dec) {
			  errors.addAll(d.checkSemantics(ST, _nesting)) ;
	      }

		  for (Node s : stm) {
			  errors.addAll(s.checkSemantics(ST, _nesting));
		  }

		  if (exp != null) {
			  errors.addAll(exp.checkSemantics(ST, _nesting)) ;
		  }

		  //clean the scope, we are leaving a dec scope
		  ST.remove();

		  return errors;
	}
	public Type typeCheck (ArrayList _e) {
		for (Node d: dec)
		    d.typeCheck(_e);
		for (Node s: stm)
			s.typeCheck(_e);
		if (exp != null) {
			return exp.typeCheck(_e);
		} else {
			return new VoidType();
		}
	}
		  
	public String codeGeneration() {
		String decCode="";
		for (Node d: dec)
			decCode += d.codeGeneration();

		String stmCode = "";
		for (Node s: stm)
			stmCode += s.codeGeneration();

		String expCode="";
		if (exp != null) {
			expCode = exp.codeGeneration();
		}
		return  "move SP FP\n"
				+ "pushr FP\n"
				+ "move SP AL\n"
				+ "pushr AL\n"
				+ decCode
				+ stmCode
				+ expCode;
	} 
		  
	public String toPrint(String s) {
		String decstr="";
		for (Node d : dec)
			decstr += d.toPrint(s+"\t");
		String stmstr="";
		if (!stm.isEmpty()) {
			for (Node st : stm) {
				stmstr += st.toPrint(s+"\t");
			}
		}
		String expstr="";
		if (exp != null) {
			expstr = exp.toPrint(s+"\t");
		}
		return s+"Full Program\n" + decstr + "\n" + stmstr + "\n" + expstr ;
	}
     
}  