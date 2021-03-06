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
 * Rappresenta l'operatore di uguaglianza tra due espressioni
 * 
 * 
 * @author Marco Speranza
 * @version 0.1
 */
public class Eq extends RelOp {

	/** Lato sx dell'espressione */
	private Exp a;

	/** Lato dx dell'espressione */
	private Exp b;

	/**
	 * Creates a new instance of eq
	 * 
	 * @param a
	 *            Lato sx dell'espressione
	 * @param b
	 *            Lato dx dell'espressione
	 */
	public Eq(Exp a, Exp b) {
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
		Number aValue = retrieveValue(this.a.val());
		Number bValue = retrieveValue(this.b.val());
		return aValue.floatValue() == bValue.floatValue();

	}

}
