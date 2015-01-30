/*
 *
 * Copyright 2003-2011 Simple Logo Viewer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

lexer grammar LogoScanner;

options {
  language = Java;
}


@lexer::header {
  package org.logo.parser.antlr3;

}

@lexer::members {
  List<RecognitionException> exceptions = new ArrayList<RecognitionException>();

  public List<RecognitionException> getExceptions() {
    return exceptions;
    }

    @Override
    public void reportError(RecognitionException e) {
      exceptions.add(e);
      super.reportError(e);

  }
  
 @Override
  public void emitErrorMessage(String msg) {
       throw new RuntimeException(msg, getExceptions().get(0));
   }

}


fragment LETTER : ('a'..'z' | 'A'..'Z') ;
fragment DIGIT : '0'..'9';
fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');
  

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

LT:     '<';
GT:     '>';
EQ:     '=';
GE:     '>=';
LE:     '<=';
NOTEQ :  '!=';

AND     : A N D ;
OR      : O R;

TRUE    : T R U E ;
FALSE   : F A L S E;

IF      : S E    
        | I F 
        ;
        
FOR     : R I P E T I   
        | R E P E A T
        ;
        
FORWARD : A V A N T I   
        | F O R W A R D 
        ;
        
BACK    : I N D I E T R O  
        | B A C K 
        ;
        
RIGHT   : D E S T R A  
        | R I G H T T U R N   
        | R T
        ;
        
LEFT    : S I N I S T R A
        | L E F T T U R N
        | L T
        ;
        
PENUP   : S U 
        | P E N U P
        ;
        
PENDOWN : G I U 
        | P E N D O W N
        ;
        
PRINT   : S T A M P A
        | P R I N T
        ;
        
SETXY   : P O S I Z I O N E
        | S E T X Y
        ;
        
EXIT    : T E R M I N A
        | E X I T
        ;
        
TO      : P E R  
        | T O 
        ;
        
END     : F I N E
        | E N D
        ;
        
MAKE    : A S S E G N A 
        | M A K E
        ;

REPCOUNT: R E P C O U N T;


P_OPEN:    '(';
P_CLOSE:    ')';
SQUERE_BR_OPEN:    '[';
SQUERE_BR_CLOSE:   ']';
DOUBLE_QUOTE:       '"';
COLON:             ':';
DOT:             '.';


 
NUMBER: DIGIT+ (DOT DIGIT)*;
IDENT: LETTER (LETTER | DIGIT)*;
VARIABLE: COLON IDENT;
ASSIGN_VARIABLE: DOUBLE_QUOTE IDENT;

WS : (' ' | '\t' | '\n' | '\r' | '\f')+ {$channel = HIDDEN;};
COMMENT : '//' .* ('\n'|'\r') {$channel = HIDDEN;};
MultiLineComment :
  '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;};
  
  

