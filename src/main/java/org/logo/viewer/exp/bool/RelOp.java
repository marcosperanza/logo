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

package org.logo.viewer.exp.bool;

import org.logo.viewer.exp.Exp;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Operatore Relazionale
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public abstract class RelOp implements ExpBool {

	/** Creates a new instance of ExpBool */
	public RelOp() {
	}

	/**
	 * Valuta l'espressione
	 * 
	 * @throws VariableNotFoundException
	 *             Variabile non trovata
	 * @return true se ha trovato la variabile
	 */
	abstract public boolean val() throws VariableNotFoundException;

	/**
	 * @param stackVar
	 *            TODO
	 * @param aValue
	 * @return
	 * @throws VariableNotFoundException
	 */
	protected Number retrieveValue(Object value)
			throws VariableNotFoundException {

		if (value instanceof Number) {
			return (Number) value;
		}

		if (value instanceof Exp) {
			return retrieveValue(((Exp) value).val());
		}

		throw new IllegalArgumentException("The value '"
				+ value.getClass().getName() + "' not allowed");
	}

}
