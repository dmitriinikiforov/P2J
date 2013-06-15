grammar Prolog;
program: statement*;
statement: structure (ASSIGN structure (AND structure)*)? DOT NEWLINE?;
structure: FUNCTOR (LEFT_BRACE argument (AND argument)* RIGHT_BRACE)?;
argument: (VARIABLE|NUMBER|STRING)|list|structure;
list: LIST_BEGIN (argument (AND argument)* tail?)? LIST_END;
tail: LIST_LINE (VARIABLE|list);

VARIABLE: (UPPERCASE|'_') (LOWERCASE|UPPERCASE|DIGIT|'_')*; //1
FUNCTOR: LOWERCASE (LOWERCASE|UPPERCASE|DIGIT|'_')*;        //2
NUMBER: DIGIT+ (DOT DIGIT+)? EXPONENT? 
        | DOT DIGIT+ EXPONENT?;                             //3
EXPONENT: ('e'('x''p')?|'E'('x''p')?)('+'|'-')? DIGIT+;     //4
DOT: '.';                                                   //5
AND: ',';                                                   //6
OR: ';';                                                    //7
ASSIGN: ':-';                                               //8
LOWERCASE: [a-z];                                           //9
UPPERCASE: [A-Z];                                           //10
DIGIT: [0-9];                                               //11
ANONIMUS: '_';                                              //12
STRING:  '"' (~('"'))*  '"'
      | '\'' (~('\''))* '\'';                               //13
LEFT_BRACE: '(';             
RIGHT_BRACE: ')';
LIST_BEGIN: '[';
LIST_LINE: '|';
LIST_END: ']';
NEWLINE: '\r'? '\n' ;
WS  :   [ \t]+ -> skip ;