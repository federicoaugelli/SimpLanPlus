grammar SimpLanPlus ;

prog   : exp                        #progExp
       | (dec)+ (stm)* (exp)?       #progFull
       ;

dec    : type ID ';'                                                #varDec
       | type ID '(' ( param ( ',' param)* )? ')' '{' body '}'      #funDec
       ;

param  : type ID ;

body   : (dec)* (stm)* (exp)?
       ;

type   : 'int'
       | 'bool'
       | 'void'
       ;

stm    : ID '=' exp ';'                                                                 #asg
       | ID '(' (exp (',' exp)* )? ')' ';'                                              #funStm
       | 'if' '(' cond=exp ')' thenBranch=branchStm ('else' elseBranch=branchStm)?      #ifStm
       ;

exp    :  INTEGER                                                                                   #int
       | 'true'                                                                                     #true
       | 'false'                                                                                    #false
       | ID                                                                                         #var
       | not='!' right=exp                                                                          #not
       | left=exp (mul='*' | div='/') right=exp                                                     #mulDiv
       | left=exp (plus='+' | minus='-') right=exp                                                  #plusMin
       | left=exp (great='>' | minor='<' | greateq='>=' | minoreq='<=' | equal='==') right=exp      #compare
       | left=exp (and='&&' | or='||') right=exp                                                    #andOr
       | 'if' '(' cond=exp ')' thenBranch=branchExp 'else' elseBranch=branchExp                     #ifExp
       | '(' exp ')'                                                                                #paren
       | ID '(' (exp (',' exp)* )? ')'                                                              #funExp
       ;

branchStm  : '{' (stm)+ '}' ;

branchExp : '{' (stm)* exp '}' ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

//Numbers
fragment DIGIT  : '0'..'9';
INTEGER         : DIGIT+;

//IDs
fragment CHAR   : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS     : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;

ERR             : .  -> channel(HIDDEN);