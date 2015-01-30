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

package org.logo.viewer.exp;

import org.logo.viewer.logo.PrintMessage;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Esegue una divisione tra due espressioni
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class Div extends Exp {

	/** espessione */
	private Exp a;

	/** espressione */
	private Exp b;

	/**
	 * Creates a new instance of Div
	 * 
	 * @param a
	 *            Espressione
	 * @param b
	 *            Espressione
	 */
	public Div(Exp a, Exp b) {
		this.a = a;
		this.b = b;
	}

	/**
	 * Valuta l'espressione
	 * 
	 * @return torna il valore
	 * @throws VariableNotFoundException
	 *             Lanciata quando non viene trovata la variabile
	 */
	public Object val() throws VariableNotFoundException {
		Object aValue = this.a.val();
		Object bValue = this.b.val();

		try {

			Number a = retrieveValue(aValue);
			Number b = retrieveValue(bValue);

			float res;
			res = a.floatValue() / b.floatValue();
			return new Float(res);
		} catch (ArithmeticException e) {
			PrintMessage.printMessage("Errore Divisione per Zero!");
			throw new IllegalArgumentException(e);
		}
	}

}
