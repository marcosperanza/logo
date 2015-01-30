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

import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Implementa i numeri Interi
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class Int extends Exp {

	/** e' il numero intero */
	private Integer num;

	/**
	 * Costruttore
	 * 
	 * @param i
	 *            Integer
	 */
	public Int(Integer i) {
		this.num = i;
	}

	/**
	 * Costruttore
	 * 
	 * @param i
	 *            int
	 */
	public Int(int i) {
		this.num = new Integer(i);
	}

	/**
	 * Torena il valore
	 * 
	 * @return tornail valore
	 */
	public Integer getVal() {
		return this.num;
	}

	/**
	 * Valuta l'intero
	 * 
	 * @return Torna il valore valutato
	 * @throws VariableNotFoundException
	 *             Variabile non trovata
	 * 
	 */
	public Object val() throws VariableNotFoundException {
		return this.getVal();
	}

}
