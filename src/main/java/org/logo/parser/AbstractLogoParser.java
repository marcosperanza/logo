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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;
import org.logo.parser.antlr3.LogoScanner;
import org.logo.viewer.exp.FloatNum;
import org.logo.viewer.logo.Assegna;
import org.logo.viewer.logo.Statement;

/**
 * 
 * 
 * @author Marco Speranza
 * @version $Id: AbstractLogoParser.java 9 2011-05-10 21:34:52Z marco.speranza79@gmail.com $
 */
abstract public class AbstractLogoParser extends Parser {

    protected List<RecognitionException> exceptions = new ArrayList<RecognitionException>();
    protected Map<String, Statement> autoGenerateVars = new HashMap<String, Statement>(1);

    /**
     * @param input
     * @param state
     */
    protected AbstractLogoParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    /**
     * @param input
     */
    protected AbstractLogoParser(TokenStream input) {
        super(input);
    }

    /**
     * 
     */
    public AbstractLogoParser(CharStream stream) {
        super(new CommonTokenStream());
        init(stream);
        
    }
    
    private void init(CharStream stream) {
        Lexer lexer = new LogoScanner(stream);
        TokenStream tokens = new CommonTokenStream(lexer);   
        setTokenStream(tokens);
    }
    

    protected void addAutoGenerateVar(String randomScopeVar) {
        if (!autoGenerateVars.containsKey(randomScopeVar)) {
            Statement s = LogoProxy.newInstance(new Assegna(randomScopeVar,
                    new FloatNum(new Float(0.0f))));
            autoGenerateVars.put(randomScopeVar, s);
        }
    }

    public List<RecognitionException> getExceptions() {
        return exceptions;
    }

    @Override
    public void reportError(RecognitionException e) {
        exceptions.add(e);
        super.reportError(e);

    }

    protected Logger getLogger() {
        return Logger.getLogger(this.getClass().getName());
    }

    @Override
    public void emitErrorMessage(String msg) {
        throw new RuntimeException(msg, getExceptions().get(0));
    }
}
