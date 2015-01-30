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

import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Rappresenta il comando RIPETI del linguaggio LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class Ripeti extends AbstractStatement {

	/** Rappresenta il Body del comando RIPETI */
	private List<Statement> body;

	/**
	 * Rappresenta il parametro E' il numero di volte che deve essere eseguito
	 * il body
	 */
	private Object parametro;

	/**
	 * Creates a new instance of Ripeti
	 * 
	 * @param parametro
	 *            indica ilparametro
	 * @param body
	 *            indica il body
	 */
	public Ripeti(Object parametro, List<Statement> body) {
		this.body = body;
		this.parametro = parametro;
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
		/**
		 * Ripete il body per un numero fissato di volte
		 * 
		 * Se parametro e' una istanziazione di Variablie() devo recuperare il
		 * valore dallo stackVar
		 */

		Object valueVar = evaluate(parametro);
		if (!(valueVar instanceof Number)) {
			throw new ParameterException("Il parametro passato a RIPETI '"
					+ valueVar.getClass().getSimpleName()
					+ "' non e' un Integer!");
		}

		createNewScope();
		try {
			Number intValueVar = ((Number) valueVar).intValue();
			for (int ripeti = 0; ripeti < intValueVar.intValue(); ripeti++) {
				Iterator<Statement> it = this.body.iterator();
				while (it.hasNext()) {
					Statement bodyStatements = it.next();
					bodyStatements.execute();
				}
			}
		} finally {
			cleanScope();
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
