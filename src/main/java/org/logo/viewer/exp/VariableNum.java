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

import org.logo.viewer.logo.Variabile;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Una <b>Variabile</b> e' una Espressione
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class VariableNum extends Exp {

	/** E' la variabile */
	private Variabile v;

	/**
	 * Costruttore
	 * 
	 * @param v
	 *            Variabile
	 */
	public VariableNum(Variabile v) {
		this.v = v;
	}

	/**
	 * Valuta la variabile
	 * 
	 * @param stackVar
	 *            E' lo stack delle variabili
	 * @return torena il valore
	 * @throws VariableNotFoundException
	 *             VAriabile non trovata
	 */
	public Object val() throws VariableNotFoundException {
		return v.evaluate(v);
	}

}
