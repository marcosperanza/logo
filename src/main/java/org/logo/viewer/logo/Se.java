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

import java.util.Iterator;
import java.util.List;

import org.logo.viewer.exp.bool.ExpBool;
import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Rappresenta il comando SE del linguaggio LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class Se extends AbstractStatement {

	/** Rappresenta il body */
	private List<Statement> body;

	/** Contiene l'espressione Booleana */
	private ExpBool exp;

	/**
	 * Creates a new instance of Se
	 * 
	 * @param exp
	 *            Espressione bolleana della condizione
	 * @param body
	 *            il body dello statement
	 */
	public Se(ExpBool exp, List<Statement> body) {
		this.body = body;
		this.exp = exp;
	}

	/**
	 * Contiene l'implementazione dello statement
	 * 
	 * @throws ProceduraTerminataException
	 *             lanciata se si incontra il comando <code>Termina()</CODE>
	 * @throws ProcedureNotFoundException
	 *             lanciata se non si riesce a risolvere il nome di un procedura
	 * @throws ParameterException
	 *             lanciata se c'e' un parametro errato
	 * @throws VariableNotFoundException
	 *             lanciata se se non si riesce a risolvere il nome di una
	 *             variabile
	 */
	@Override
	public void execute() throws ProceduraTerminataException,
			ProcedureNotFoundException, ParameterException,
			VariableNotFoundException {
		if (this.exp.val()) {
			createNewScope();
			try {
				Iterator<Statement> it = this.body.iterator();
				while (it.hasNext()) {
					Statement bodyStatements = it.next();
					bodyStatements.execute();

				}
			} catch (ArrayIndexOutOfBoundsException e) {

			} finally {
				cleanScope();
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.parser.logo.viewer.logo.Statements#clear()
	 */
	public void clear() {
		this.body.clear();
	}
}
