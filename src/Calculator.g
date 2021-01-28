// antlr4 src/Calculator.g -o src/calculator -package calculator -Xexact-output-dir
grammar Calculator;

program: expr EOF;
expr : exprM ((PLUS| MINUS) exprM)*;
exprM: exprP ((DIV|TIMES) exprP)*;
exprP: Digit+;
Digit: [0-9];
PLUS: '+';
MINUS: '-';
DIV: '/';
TIMES: '*';


WS : [ \t\r\n]+ -> skip ;