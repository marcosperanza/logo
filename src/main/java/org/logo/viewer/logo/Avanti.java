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

import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;
import org.logo.viewer.logo.graphic.Action;
import org.logo.viewer.logo.graphic.Movimento;

/**
 * Rappresenta il comando AVANTI del linguaggio LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */

public class Avanti extends AbstractStatement {

	/**
	 * Indica il numero di pasi da effettuare
	 */
	private Object avanti;

	/**
	 * Costruttore
	 * 
	 * @param avanti
	 *            Rappresenta il numero di passi da fare
	 */
	public Avanti(Object avanti) {
		this.avanti = avanti;
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
		// il parametro "avanti" puo essere una istanziazione di Vartibile
		Object valoreVar = evaluate(avanti);

		PrintMessage.printMessage("Avanti " + valoreVar);
		// PrintMessage.printMessage(""+spool.isGraphic());
		// Movimento p = ;
		Action action = new Movimento(((Number) valoreVar).doubleValue());
		spool.push(action);
	}
}
