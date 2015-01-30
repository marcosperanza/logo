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

import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Esegue l'AND logico tra due espressioni
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class And implements ExpBool {

	/** lato sx dell'espressione */
	private ExpBool a;

	/** Lato dx dell'espressione */
	private ExpBool b;

	/**
	 * Creates a new instance of And
	 * 
	 * @param a
	 *            lato sx
	 * @param b
	 *            lato dx
	 */
	public And(ExpBool a, ExpBool b) {
		this.a = a;
		this.b = b;
	}

	/**
	 * Valuta l'espressione
	 * 
	 * @throws VariableNotFoundException
	 *             Variabile non trovata
	 * @return true se ha trovato la variabile
	 */
	public boolean val() throws VariableNotFoundException {
		return (a.val() && b.val());
	}

}
