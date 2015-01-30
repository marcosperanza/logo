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

import org.logo.viewer.exp.Exp;
import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * La classe procedura rappresenta una "Procedura" all'interno del programma
 * LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class ProcedureCall extends AbstractStatement {


	/** Contiene lostack dei parametri */
	private List<Exp> params;

	/**
	 * Creates a new instance of Procedura
	 * 
	 * @param nomeProcedura
	 *            Rappresenta il nome della proicedura
	 * 
	 */
	public ProcedureCall(String nomeProcedura) {
		this(nomeProcedura, null);
	}

	/**
	 * Creates a new instance of Procedura
	 * 
	 * @param nomeProcedura
	 *            Rappresenta il nome della proicedura
	 * @param parametro
	 *            Rappesenta il parametro in input alla procedura
	 */
	public ProcedureCall(String nomeProcedura, List<Exp> params) {
		this.setName(nomeProcedura);
		this.params = params;
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

		Procedure procedura;
		procedura = super.findProcedure(getName());
		procedura.invoke(params, this);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Procedura [nomeProcedura=" + getName() + ", parametro="
				+ params + "]";
	}

}
