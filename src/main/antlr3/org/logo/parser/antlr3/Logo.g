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

grammar Logo;

options {
  language = Java;
  superClass=AbstractLogoParser;
  tokenVocab=LogoScanner;
}

import LogoScanner;

scope body {
 List<Statement> body;
 String randomScopeId;
}

scope Procedures {
 List<Procedure> proc;
}
scope procedure_formal_params {
 List<String> vars;
}

scope procedure_real_params {
 List<Exp> exps;
}
@lexer::header {
  package org.logo.parser.antlr3;
  }

@header {
  package org.logo.parser.antlr3;

  import java.util.Vector;
  import java.util.HashMap;
  import java.util.Map;
  import java.util.Map.Entry;
  import java.util.logging.Level;
  import java.util.logging.Logger;

  import org.logo.parser.AbstractLogoParser;
  import org.logo.parser.LogoProxy;
  import org.logo.viewer.exp.Div;
  import org.logo.viewer.exp.Exp;
  import org.logo.viewer.exp.FloatNum;
  import org.logo.viewer.exp.Minus;
  import org.logo.viewer.exp.Mult;
  import org.logo.viewer.exp.Plus;
  import org.logo.viewer.exp.VariableNum;
  import org.logo.viewer.exp.bool.And;
  import org.logo.viewer.exp.bool.Eq;
  import org.logo.viewer.exp.bool.ExpBool;
  import org.logo.viewer.exp.bool.False;
  import org.logo.viewer.exp.bool.Ge;
  import org.logo.viewer.exp.bool.Gt;
  import org.logo.viewer.exp.bool.Le;
  import org.logo.viewer.exp.bool.Not;
  import org.logo.viewer.exp.bool.Lt;
  import org.logo.viewer.exp.bool.Or;
  import org.logo.viewer.exp.bool.True;
  import org.logo.viewer.logo.Assegna;
  import org.logo.viewer.logo.Termina;
  
  import org.logo.viewer.logo.Avanti;
  import org.logo.viewer.logo.Destra;
  import org.logo.viewer.logo.Procedure;
  import org.logo.viewer.logo.Giu;
  import org.logo.viewer.logo.Indietro;
  import org.logo.viewer.logo.Per;
  import org.logo.viewer.logo.Posizione;
  import org.logo.viewer.logo.ProcedureCall;
  import org.logo.viewer.logo.Program;
  import org.logo.viewer.logo.LogoProgram;
  import org.logo.viewer.logo.Ripeti;
  import org.logo.viewer.logo.Se;
  import org.logo.viewer.logo.Sinistra;
  import org.logo.viewer.logo.Stampa;
  import org.logo.viewer.logo.Statement;
  import org.logo.viewer.logo.Su;
  import org.logo.viewer.logo.Variabile;

}

@members {
    public LogoParser(CharStream stream) {
        super(stream);
    }
}

public program returns [Program program]
	scope body, Procedures;
	@init{
	  $body::body = new ArrayList<Statement>(1);
	  $body::randomScopeId = ""+System.nanoTime();
	  $Procedures::proc = new ArrayList<Procedure>(1);
	}
	@after{
	  for ( Entry<String, Statement> entry : autoGenerateVars.entrySet()) {
	    $body::body.add(0, entry.getValue());
	  }
	  
	  program = new LogoProgram($Procedures::proc, $body::body);
	}
	    :  p=procedure*
	       statement*
  ;






procedure
scope body,procedure_formal_params;
@init{
  $body::body = new ArrayList<Statement>(1);
  $body::randomScopeId = ""+System.nanoTime();
  $procedure_formal_params::vars = new ArrayList<String>(1);
}
  : TO IDENT  formal_parameters*        
      statement+ 
    END                     {
                                  //String var = $VARIABLE.text; 
                                  Per p =  new Per($IDENT.text, $procedure_formal_params::vars, $body::body);
                                  getLogger().log(Level.FINE, "Found procedure: " + p);
                                  $Procedures::proc.add(p); 
                               }
  ;


 

statement
  : EXIT                                         { getLogger().log(Level.FINE, "Found statement 'Termina'");$body::body.add(new Termina());}
  | s=assignmentStatement                         { getLogger().log(Level.FINE, "Found statement '" + s +"'");$body::body.add(s);}
  | s=commandStatement                            { getLogger().log(Level.FINE, "Found statement '" + s +"'");$body::body.add(s);}
  | s=loopStatement                               { getLogger().log(Level.FINE, "Found statement '" + s +"'");$body::body.add(s);}
  | s=ifStatement                                 { getLogger().log(Level.FINE, "Found statement '" + s +"'");$body::body.add(s);}
  | s=procedureCallStatement                      { getLogger().log(Level.FINE, "Found statement '" + s +"'");$body::body.add(s);}
  ;

  
procedureCallStatement returns [Statement s]
scope procedure_real_params;
@init{
  $procedure_real_params::exps = new ArrayList<Exp>(1);
}
  : id=IDENT  exp=expression*                    {s = LogoProxy.newInstance(new ProcedureCall(id.getText(), $procedure_real_params::exps));}
  ;


assignmentStatement returns [Statement assegna]
  : MAKE ASSIGN_VARIABLE exp=expression              {  getLogger().log(Level.FINE, "Assign variable '" + $ASSIGN_VARIABLE.text.substring(1) +"' :"+ $exp.value);
                                                      assegna = LogoProxy.newInstance(new Assegna($ASSIGN_VARIABLE.text.substring(1), exp));}
  ;

commandStatement returns [Statement baseCommand]
  : FORWARD     exp=expression                   {baseCommand = LogoProxy.newInstance(new Avanti(exp));}
  | BACK        exp=expression                   {baseCommand = LogoProxy.newInstance(new Indietro(exp));}
  | RIGHT       exp=expression                   {baseCommand = LogoProxy.newInstance(new Destra(exp));}
  | LEFT        exp=expression                   {baseCommand = LogoProxy.newInstance(new Sinistra(exp));}
  | PENUP                                        {baseCommand = LogoProxy.newInstance(new Su());}
  | PENDOWN                                       {baseCommand = LogoProxy.newInstance(new Giu());}
  | PRINT       exp=expression                   {baseCommand = LogoProxy.newInstance(new Stampa(exp));}
  | SETXY       x=expression y=expression       {baseCommand = LogoProxy.newInstance(new Posizione(x, y));}
  ;




loopStatement returns [Statement s]
scope body;
@init{
  $body::body = new ArrayList<Statement>(1);
  $body::randomScopeId = ""+System.nanoTime();
  
}
  : FOR exp=expression
      SQUERE_BR_OPEN
          statement+
      SQUERE_BR_CLOSE                     {s=LogoProxy.newInstance(new Ripeti(exp, $body::body)); }
  ;

ifStatement returns [Statement s]
scope body;

@init{
  $body::body =  new ArrayList<Statement>(1);
  $body::randomScopeId = ""+System.nanoTime();
  
}
  : IF exp=booleanExpression
      SQUERE_BR_OPEN
          statement+ 
      SQUERE_BR_CLOSE                     {s=LogoProxy.newInstance(new Se(exp, $body::body)); }
  ;





// expressions -- fun time!

expression returns [Exp value]
    :    exp=additionExp {$value = $exp.value; if(!$procedure_real_params.empty()) $procedure_real_params::exps.add($exp.value);}
    ;

booleanExpression returns [ExpBool value]
    :    exp=logicalExpression {$value = $exp.value;}
    ;

logicalExpression  returns [ExpBool value]
    :   m1=relationExp {$value = $m1.value;}
         ( AND m2=relationExp {$value = new And(m1, m2);}
         | OR m2=relationExp {$value = new Or(m1, m2);}
         )*
    ;

relationExp returns [ExpBool value]
    : m1=additionExp      
         ( LT m2=additionExp   {$value = new Lt(m1, m2);}
         | GT m2=additionExp   {$value = new Gt(m1, m2);}
         | EQ m2=additionExp   {$value = new Eq(m1, m2);}
         | GE m2=additionExp  {$value = new Ge(m1, m2);}
         | LE m2=additionExp  {$value = new Le(m1, m2);}
         | NOTEQ m2=additionExp  {$value = new Not(new Eq(m1, m2));}
         )+
    ;

additionExp returns [Exp value]
    :    m1=multiplyExp       {$value =  $m1.value;}
         ( PLUS m2=multiplyExp {$value = new Plus(m1, m2);}
         | MINUS m2=multiplyExp {$value = new Minus(m1, m2);}
         )*
    ;

multiplyExp returns [Exp value]
    :    a1=atomExp       {$value =  $a1.value;}
         ( MULT a2=atomExp {$value = new Mult(a1, a2);}
         | DIV a2=atomExp {$value = new Div(a1, a2);}
         )*
    ;

atomExp returns [Exp value]
    :    NUMBER                   {$value = new FloatNum(Float.parseFloat($NUMBER.text));}
    |    VARIABLE                 {$value = new VariableNum(new Variabile($VARIABLE.text.substring(1)));}
    |    TRUE                   {$value = new FloatNum(Float.MAX_VALUE);}
    |    FALSE                  {$value = new FloatNum(Float.MIN_VALUE);}
    |    exp=repCount       {$value = $exp.value;}
    |    P_OPEN exp=additionExp  P_CLOSE {$value = $exp.value;}
    ;

formal_parameters returns [String value]
  : VARIABLE            {$value = $VARIABLE.text.substring(1); 
                          $procedure_formal_params::vars.add($value);
                        }
  ;

repCount returns [Exp value]
  : REPCOUNT          {
                  String randomVar = $body::randomScopeId;
                  if ( autoGenerateVars.get(randomVar) == null ) {
                    addAutoGenerateVar(randomVar);
                    Exp exp = new Plus(new VariableNum(new Variabile(randomVar)), new FloatNum(new Float(1.0f)) );
                    $body::body.add(LogoProxy.newInstance(new Assegna(randomVar, exp)));
                  } 
                      $value = new VariableNum(new Variabile(randomVar));
                  }
  ;

  
  
  
  

  
  