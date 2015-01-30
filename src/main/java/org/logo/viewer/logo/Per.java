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

import java.util.List;

import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Rappresenta la dichiarazione di un Procedura nel programma LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class Per extends AbstractProcedure {

	/**
	 * Creates a new instance of Per
	 * 
	 * @param nomeProcedura
	 *            Nome della procedura
	 * @param body
	 *            body della procedura
	 */
	public Per(String nomeProcedura, List<Statement> body) {
		this(nomeProcedura, null, body);
	}

	/**
	 * Contiene l'implementazione dello statement
	 * 
	 * @param stackProc
	 *            e' lo stack delle definizione Procedure
	 * @param stackVar
	 *            e' lo stack delle definizione di Variabili
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
	public Per(String nomeProcedura, List<String> formalParams,
			List<Statement> body) {
		this.setName(nomeProcedura);
		this.setBody(body);
		this.formalParams = formalParams;
	}
}
