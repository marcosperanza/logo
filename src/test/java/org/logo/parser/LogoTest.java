/**
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

package org.logo.parser;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.junit.Assert;
import org.junit.Test;
import org.logo.parser.antlr3.LogoParser;
import org.logo.viewer.logo.LogoProgram;
import org.logo.viewer.logo.Program;
import org.logo.viewer.logo.Statement;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class LogoTest {


    @Test
    public void minimumProgram() throws Throwable {
        LogoParser parser = createParser("");
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
    }

    @Test
    public void procedureDeclaration() throws Throwable {
        String pr = "Per Triangolo :taglia "+
        "   Avanti :taglia "+
        "   Destra 120 "+
        "   Avanti :taglia "+
        "   Destra 120 "+
        "Fine " +
        "Per Triangolo2 :pop "+
        "   Avanti :pop "+
        "   Destra 120 "+
        "Fine "+
        "Avanti 1";

        LogoParser parser = createParser(pr);
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
    }
    
    
    @Test
    public void procedureDeclarationMultiVar() throws Throwable {
        String pr = "Per Triangolo :taglia :cuci "+
        "   Avanti :taglia "+
        "   Destra 120 "+
        "   Avanti :taglia "+
        "   Avanti :cuci "+
        "   Destra 120 " +
        "   Stampa :taglia " +
        "   Stampa :cuci "+
        "Fine " +
       
        "Triangolo 1 2";

			
        	LogoParser parser = createParser(pr);
        	Program p = parser.program();
        	assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        	((LogoProgram) p).start();
    }

    @Test(expected=RuntimeException.class)
    public void procedureDeclarationFailed() throws Throwable {
        LogoParser parser = createParser(
                "Per Triangolo :taglia "+
                    "Per Inner :var Fine "+
                    "Fine "
                   );
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 2);
        ((LogoProgram) p).start();


    }

    @Test
    public void procedureDeclarationRecursion() throws Throwable {
        LogoParser parser = createParser(
                "Per Triangolo :taglia " +
                "  Se :taglia <= 10 [ " +
                "		Avanti 1 " +
                "	  Termina " +
                " ]" +
                "  Triangolo :taglia / 2"+
                " Fine " +
                "" +
                " Triangolo 20"
                   );
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
    }
    
    @Test
    public void commandsInvocation() throws Throwable {
        LogoParser parser = createParser(
                "Assegna \"variabile 1\n"+
                "Assegna \"var 10\n"+
                "Avanti 1\n"+
                "Avanti :variabile\n "+
                "Posizione 12 :var "+
                "Indietro (10-:var/20) "+
                "Destra 1\n"+
                "Sinistra :variabile\n"+
                "Su\n"+
                "Giu\n"
                );
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();

    }

   
    
    @Test
    public void assignment() throws Throwable {
        LogoParser parser = createParser(
                "Assegna \"var 1\n"
                );
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
    }
    
    
    @Test
    public void assignment2() throws Throwable {
        LogoParser parser = createParser(
                "Assegna \"var  0\n"+
                "Assegna \"var :var + 1\n"
                +
                
                "Stampa :var"
                
                );
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
    }
    
    @Test
    public void assignment3() throws Throwable {
        LogoParser parser = createParser(
                " Assegna \"var 0"+
                " Ripeti 10 [ " +
                "  Assegna \"var :var + 1" +
                "  Stampa :var "+
                " ]"
                );
        

        Program p = parser.program();
        Iterator<Statement> s = p.getBody().iterator();
        
			System.out.println("LogoTest.assignment3() " + p);
			
        ((LogoProgram) p).start();
    }


    @Test
    public void loopStatement() throws Throwable {
        LogoParser parser = createParser(
                "Ripeti :var [ " +
                " Casa 10" +
                " ]\n"+

                "Ripeti (10+101)-1/10*101+(19) [ " +
                " Casa 10" +
                " ]\n"
                );
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
    }
    
    @Test
    public void loopRepCountStatement() throws Throwable {
        LogoParser parser = createParser(
                 
                "Ripeti 10 [ " +
                " Stampa REPCOUNT" +
                " Stampa REPCOUNT" +
                " Stampa 9999999" +
                " ]\n"+
                
                "Ripeti 5 [ " +
                " Stampa REPCOUNT" +
                " Stampa 8888" +
                " ]\n" 
               
                );
        Program p = parser.program();
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
    }

    @Test
    public void loopRepCountStatement2() throws Throwable {
        LogoParser parser = createParser(
        		 " Per PlusArt \n" +
                 "Ripeti 11 [\n" +
                 "	Stampa (REPCOUNT -1 *10) " +
                 "] \n" +
                 "Fine\n" +
                 "PlusArt"
        		);
         Program p = parser.program();
	        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
	        ((LogoProgram) p).start();
	    }
    
    
    @Test(expected=VariableNotFoundException.class)
    public void ifStatementScope() throws Throwable {
        LogoParser parser = createParser(
                "Se 1<9 [ " +
                " Assegna \"var 10 " +
                " ] " +
                " Stampa :var"
                
                
                );
        Program p = parser.program();
        ((LogoProgram) p).start();
    }
    
    
    @Test(expected=VariableNotFoundException.class)
    public void ifStatementNestedFail() throws Throwable {
        LogoParser parser = createParser(
                "Se 1<9 [ " +
                " Assegna \"var 10 " +
                " Assegna \"var2 2 " +
                
                "	Se :var = 10 [ " +
                "		Se :var2 > 1 [ " +
                "			Se :var2 > 1 [ " +
                "				Se :var2 > 1 [ " +
                "					Se :var2 > 1 [ " +
                "						Assegna \"nestedVar 0 " +
                " 					]" +
                " 				]" +
                " 			]" +
                " 		]" +
                "   Stampa :nestedVar" +
                "   ]" +
                
                " ] " 
                
                );
        Program p = parser.program();
        ((LogoProgram) p).start();
    }
    
    
    @Test
    public void ifStatementNestedOK() throws Throwable {
        LogoParser parser = createParser(
        		 "Se 1<9 [ " +
                 " Assegna \"var 10 " +
                 " Assegna \"var2 2 " +
                 
                 "	Se :var = 10 [ " +
                 "		Se :var2 > 1 [ " +
                 "			Se :var2 > 1 [ " +
                 "				Se :var2 > 1 [ " +
                 "					Se :var2 > 1 [ " +
                 "						Assegna \"nestedVar 0 " +
                 " 					]" +
                 " 				]" +
                 " 			]" +
                 " 		]" +
                 "   Stampa :var2" +
                 "   ]" +
                 
                 " ] " 
                
                );
        Program p = parser.program();
        ((LogoProgram) p).start();
    }
    
    @Test
    public void ifStatementSimpleBool() throws Throwable {
        LogoParser parser = createParser(
                "Se 1<9 [ " +
                " Avanti 10 " +
                " ] "
                );
        Program p = parser.program();
        ((LogoProgram) p).start();
        
        
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
        
        parser = createParser(
                "Se 1 > 19 [ " +
                " Avanti 10" +
                " ]\n" 
                );
        p = parser.program();
        
        
        
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
        
        parser = createParser(
                "Se true=true and false = false [ " +
                " Avanti 10" +
                " ]\n"
                );
        p = parser.program();
        
        
        
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
        
        parser = createParser(
                "Assegna \"var 0 " +
                " Se :var < 1  [ " +
                " Avanti 1 " +
                " ] "
                );
        p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();


        parser = createParser(
                "Assegna \"var 0 " +
                "Se :var = true  [ " +
                " Avanti 1 " +
                " ] "
                );
        p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();

        
        
        parser = createParser(
                "Assegna \"var 0 " +
                "Se :var >= true  [ " +
                " Avanti 1 " +
                " ] "
                );
        p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();

        

        parser = createParser(
                "Assegna \"var 0 " +
                "Se :var <= true  [ " +
                " Avanti 1 " +
                " ] "
                );
        p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();

        
        parser = createParser(
                "Assegna \"var 0 " +
                "Se :var != true  [ " +
                " Avanti 1 " +
                " ] "
                );
        p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();
        
        
        
        parser = createParser(
                "Assegna \"var true " +
                "Se :var != true  [ " +
                " Avanti 1 " +
                " ] "
                );
        p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        ((LogoProgram) p).start();

    }
    
    @Test
    public void evalLogo() throws Throwable {
        LogoParser parser = createParser(
                "Assegna \"piccolo 10 * 1 *2 " +
                "Avanti :piccolo " 
                );
        Program p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        
        
        ((LogoProgram) p).start();
    }         
    
    
    @Test
    public void eval2Logo() throws Throwable {
        LogoParser parser = createParser(
                "Assegna \"var2 20 "+
                "Assegna \"var 10+10*(:var2 - 1) " +
                "Stampa :var + 1 "+
                "Avanti :var " 
                );
        Program p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        
        
        ((LogoProgram) p).start();
    }        

    @Test
    public void translate() throws Throwable {
        LogoParser parser = createParser(
                "Assegna \"var2 20 "+
                "Assegna \"var 10+10*(:var2 - 1) " +
                "Stampa :var + 1 "+
                "Forward :var " +
                "RT :var " +
                " se 1 = 1 [ Avanti 1 ]" +
                " iF 1 = 1 [ Avanti 1 ]" +
                " MaKe \"pippo 10 " +
                " LT :pippo" 
                );
        Program p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        
        
        ((LogoProgram) p).start();
    }        

    
    @Test
    public void fileProgram() throws Throwable {
        CharStream stream = new ANTLRFileStream("src/test/resources/program.logo");
        LogoParser parser = new LogoParser(stream);
        Program p = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            parser.getExceptions().get(0).printStackTrace();
        }
        assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        
        
    }

    @Test
    public void fileProgram2()  {
        try{
            CharStream stream = new ANTLRFileStream("src/test/resources/program2.logo");
            LogoParser parser = new LogoParser(stream);
            Program p = parser.program();
            if (parser.getNumberOfSyntaxErrors() > 0) {
                parser.getExceptions().get(0).printStackTrace();
            }
            assertEquals(parser.getNumberOfSyntaxErrors(), 0);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        
        
    }

    
    @Test
    public void fileProgrammAll()  {
        try{
            CharStream stream = new ANTLRFileStream("src/test/resources/all.logo");
            LogoParser parser = new LogoParser(stream);
            Program p = parser.program();
            if (parser.getNumberOfSyntaxErrors() > 0) {
                parser.getExceptions().get(0).printStackTrace();
            }
            assertEquals(parser.getNumberOfSyntaxErrors(), 0);
            ((LogoProgram) p).start();
        }catch (Throwable e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        
    }
    
    @Test
    public void fileProgrammAllIntoDir()  {
        try{
        	
        	File dir = new File("src/test/resources/examples");
        	File[] logoFile = dir.listFiles(new FilenameFilter() {
				
				public boolean accept(File dir, String name) {
					return name.endsWith(".logo");
				}
			});


            for (File aLogoFile : logoFile) {
                try {

                    CharStream stream = new ANTLRFileStream(aLogoFile.getAbsolutePath());
                    LogoParser parser = new LogoParser(stream);
                    Program p = parser.program();
                    if (parser.getNumberOfSyntaxErrors() > 0) {
                        parser.getExceptions().get(0).printStackTrace();
                    }
                    assertEquals(parser.getNumberOfSyntaxErrors(), 0);
                    ((LogoProgram) p).start();
                } catch (Throwable e) {
                    Assert.fail("Error in file " + aLogoFile.getName() + ": " + e.getMessage());
                }
            }
        }catch (Throwable e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }



    private LogoParser createParser(String testString) throws IOException {
        CharStream stream = new ANTLRStringStream(testString);
        LogoParser parser = new LogoParser(stream);
        return parser;
    }

    
    
    

    @Test(expected=ProcedureNotFoundException.class)
    public void procedureNotFound() throws Throwable {
        LogoParser parser = createParser(
                "Se 1<9 [ " +
                " Casa 10 " +
                " ] "
                );
        Program p = parser.program();
        ((LogoProgram) p).start();
    }
    
    @Test(expected=VariableNotFoundException.class)
    public void variableNotFound() throws Throwable {
        LogoParser parser = createParser(
                " Avanti :var " 
                );
        Program p = parser.program();
        ((LogoProgram) p).start();
    }
    

}