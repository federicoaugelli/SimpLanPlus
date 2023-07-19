package semanticanalysis;

import ast.Type;

public class STentry {
	private Type type ;
	private int offset ;
	private int nesting ;
	private String label ;
	private Boolean init;
	private Boolean thenInit;
	private Boolean elseInit;
	
	public STentry(Type _type, int _offset, int _nesting, String  _label) {
		type = _type ;
		offset = _offset ;
		nesting = _nesting ;
		label = _label ;
		init = false;
		thenInit = false;
		elseInit = false;
	}
	
	public Type gettype() {
		return type ;
	}

	public int getoffset() {
		return offset ;
	}
	
	public int getnesting() {
		return nesting ;
	}
	
	public String getlabel() {
		return label ;
	}
	public void setInit(Boolean _init) {init = _init;}
	public Boolean getInit() {return init;}
	public void setThenInit(Boolean _init) {thenInit = _init;}
	public Boolean getThenInit() {return thenInit;}
	public void setElseInit(Boolean _init) {elseInit = _init;}
	public Boolean getElseInit() {return elseInit;}

}
