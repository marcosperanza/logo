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
 * Implementa i numeri Float
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class FloatNum extends Exp {

	/** numero Float */
	private Float num;

	/**
	 * Costruttore
	 * 
	 * @param f
	 *            Float
	 */
	public FloatNum(Float f) {
		this.num = f;
	}

	/**
	 * costruttore
	 * 
	 * @param f
	 *            double
	 */
	public FloatNum(double f) {
		this.num = new Float(f);
	}

	/**
	 * Torna il valore
	 * 
	 * @return il valore
	 */
	public Float getVal() {
		return this.num;
	}

	/**
	 * Valuta il numero Float
	 * 
	 * @return torna il valore
	 * @throws VariableNotFoundException
	 *             Variabile non trovata
	 */
	public Object val() throws VariableNotFoundException {
		return this.getVal();
	}

	@Override
	public String toString() {
		return "FloatNum [num=" + num + "]";
	}

}
