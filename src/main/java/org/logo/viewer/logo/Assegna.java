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

import org.logo.viewer.exp.FloatNum;
import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Rappresenta il comado ASSEGNA dellinguaggio LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class Assegna extends AbstractStatement {

	/** Indica il nome della variabile */
	private String nomeVar;

	/** Indica il valore della variabile */
	private Object value;

	/**
	 * Creates a new instance of Assegna
	 * 
	 * @param nomeVar
	 *            Nome della Variabile
	 * @param value
	 *            il valore della variabile
	 */
	public Assegna(String nomeVar, Object value) {
		this.nomeVar = nomeVar;
		this.value = value;
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

		Object valueVar = evaluate(value);
		Assegna oldVariable = findVariable(nomeVar);
		if (oldVariable != null) {
			PrintMessage.printMessage("Assegna: " + this.nomeVar + " " + value);
			oldVariable.setValueVar(new FloatNum(((Number) valueVar)
					.floatValue()));
		} else {
			// non c'e' unavar gia definita
			// la aggiungo
			addewScopedVariable(this);
		}
	}

	/**
	 * Torna il nome della variabile
	 * 
	 * @return String Nome della variabile
	 */
	String getNameVar() {
		return this.nomeVar;
	}

	/**
	 * Torna il valore della variabile
	 * 
	 * @return Object Nome della variabile
	 */
	Object getValue() {
		return this.value;
	}

	/**
	 * Setta il valore della variabile
	 * 
	 * @param value
	 *            E' il valore della variabile
	 */
	void setValueVar(Object value) {
		this.value = value;
	}

}
