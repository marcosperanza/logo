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

package org.logo.viewer.logo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Rappresentazione di un programma LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class LogoProgram implements Runnable, Program {

	/** contiene tutte del definizioni di funzione */
	final static protected List<Procedure> procedures = new ArrayList<Procedure>(1);

	/**
	 * contienne tutte le definizioni di Variabili visibili in quresto programma
	 */
	final static protected Stack<List<Statement>> scopedVariables = new Stack<List<Statement>>();

	/** Contiene gli statements */
	private List<Statement> body;

	/**
	 * Creates a new instance of Programma
	 * 
	 */
	public LogoProgram() {
		this(null, new ArrayList<Statement>(1));
	}

	/**
	 * Creates a new instance of Programma
	 * 
	 * @param body
	 *            E' il body del programma LOGO
	 */
	public LogoProgram(List<Statement> body) {
		this(null, body);

	}

	/**
	 * Creates a new instance of Programma
	 * 
	 * @param defProc
	 *            Contiene tutte le definizioni di procedura del programma LOGO
	 * @param body
	 *            Contiene tutti gli statemets del programma LOGO
	 */
	public LogoProgram(List<Procedure> defProc, List<Statement> body) {
		setProcedures(defProc);
		setBody(body);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.logo.viewer.logo.Program#setProcedures(java.util.List)
	 */
	public List<Procedure> getProcedures() {
		return procedures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.logo.viewer.logo.Program#setProcedures(java.util.List)
	 */
	public void setProcedures(List<Procedure> proc) {
		procedures.addAll(proc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.logo.viewer.logo.Program#setBody(java.util.List)
	 */
	public void setBody(List<Statement> body) {
		this.body = body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.logo.viewer.logo.Program#getBody()
	 */
	public List<Statement> getBody() {
		return body;
	}

	/**
	 * Simula il run del prgramma LOGO
	 * 
	 * @throws Throwable
	 * 
	 */
	public void start() throws Throwable {
		try {
			Iterator<Statement> it = this.body.iterator();
			scopedVariables.push(new ArrayList<Statement>(1));
			while (it.hasNext()) {
				Statement bodyStatements = it.next();
				bodyStatements.execute();
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			scopedVariables.pop();
			procedures.clear();
		}
	}

	public void run() {
		try {
			this.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Programma [stackProcedure=" + procedures + ", defVar="
				+ scopedVariables + ", body=" + body + "]";
	}

}
