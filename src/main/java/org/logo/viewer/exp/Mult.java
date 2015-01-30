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
 * Implementa l'operazione di <B>Moltiplicazione</B>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class Mult extends Exp {

	/** Espressione */
	private Exp a;

	/** Espressione */
	private Exp b;

	/**
	 * costruttore
	 * 
	 * @param a
	 *            espressione
	 * @param b
	 *            espressione
	 */
	public Mult(Exp a, Exp b) {
		this.a = a;
		this.b = b;
	}

	/**
	 * VAluta l'espressione
	 * 
	 * @return torna il valore
	 * @throws VariableNotFoundException
	 *             VAriabile non trovata
	 */
	public Object val() throws VariableNotFoundException {
		Number aValue = retrieveValue(this.a.val());
		Number bValue = retrieveValue(this.b.val());

		float res;
		res = aValue.floatValue() * bValue.floatValue();
		return new Float(res);

		/*
		 * 
		 * 
		 * if ((aValue instanceof Float) && (bValue instanceof Float)) { float
		 * res; res = ((Float)aValue).floatValue() *
		 * ((Float)bValue).floatValue(); return new Float(res); } if ((aValue
		 * instanceof Float) && (bValue instanceof Integer)) { float res; res =
		 * ((Float)aValue).floatValue() * ((Integer)bValue).intValue(); return
		 * new Float(res); } else { float res; res =
		 * ((Integer)aValue).intValue() * ((Float)bValue).intValue(); return new
		 * Float(res); }
		 */
	}

}
