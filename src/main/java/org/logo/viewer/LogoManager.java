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

package org.logo.viewer;

import java.io.IOException;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.logo.parser.antlr3.LogoParser;
import org.logo.viewer.logo.Program;
import org.logo.viewer.logo.graphic.Action;
import org.logo.viewer.logo.graphic.LogoViewer;

/**
 * @author Marco Speranza
 * @version 0.1
 */
public class LogoManager {

	private final static Logger logger = Logger.getLogger(LogoManager.class.getName());

	private LogoViewer logoLogoViewerInterface;

	private Spool<Action> spool = Spool.getSpool();

	private Thread programTh;
	private Program logoProgram;

	public LogoManager() {
		this.makeGraphicInterface();
	}

	protected void makeGraphicInterface() {
		this.logoLogoViewerInterface = new LogoViewer(this);
	}

	/**
	 * Start del programma (Lancia un thread)
	 * 
	 * @throws RecognitionException
	 * @throws IOException
	 */
	public void start() throws Exception {
		if (logoProgram == null) {
			throw new IllegalArgumentException("Please load a LOGO program.");
		}
		if (programTh != null) {
			programTh.interrupt();
			programTh = null;
		}
		spool.setRunning(true);
		programTh = new Thread((Runnable) logoProgram);
		programTh.start();
	}

	/**
	 * @param logoProgramFileName2
	 * @throws IOException
	 * @throws RecognitionException
	 */
	private Program parseSourceFile(String file) throws Exception {
		CharStream stream = new ANTLRFileStream(file);
		LogoParser parser = new LogoParser(stream);
		Program programma = parser.program();

		if (parser.getExceptions().size() > 0) {
			RecognitionException e = parser.getExceptions().get(0);
			String hdr = parser.getErrorHeader(e);
			String msg = parser.getErrorMessage(e, parser.getTokenNames());
			throw new Exception("Error parsing file:\n  [" + hdr + " " + msg
					+ "]", parser.getExceptions().get(0));
		}
//		if (lexer.getExceptions().size() > 0) {
//			RecognitionException e = parser.getExceptions().get(0);
//			String hdr = lexer.getErrorHeader(e);
//			String msg = lexer.getErrorMessage(e, parser.getTokenNames());
//			throw new Exception("Error parsing file:\n  [" + hdr + " " + msg
//					+ "]", lexer.getExceptions().get(0));
//		}
		return programma;
	}

	/**
     * 
     */
	public void stop() {
		// this.logoProgram.stop();
		if (programTh != null) {
			programTh.interrupt();
			programTh = null;
		}
		spool.setRunning(false);
		logoProgram = null;

	}

	/**
	 * @param program
	 * @throws Exception
	 * @throws IOException
	 */
	public void loadProgram(String program) throws Exception {
		this.logoProgram = parseSourceFile(program);
	}

}
