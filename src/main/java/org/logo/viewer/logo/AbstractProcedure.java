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

import java.util.Iterator;
import java.util.List;

import org.logo.viewer.exp.Exp;
import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Rappresenta la super classe della definizione delle procedure in un programma
 * LOGO
 * 
 * @author Marco Speranza
 * @version 0.2
 */

public abstract class AbstractProcedure implements Procedure {

	/** Stack che contiene le definizxioni dei parametri */
	List<String> formalParams;
	List<Statement> body;

	/** Rappresenta il nome della procedura */
	String name;

	/** Creates a new instance of DefinizioneProcedura */
	public AbstractProcedure() {
	}

	/**
	 * Setta il nome della procedura
	 * 
	 * @param name
	 *            indica il nome dello procedura
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Torna il nome della procedura
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Torna il nome della procedura
	 * 
	 * @return Object
	 */
	List<String> getFormalParams() {
		return this.formalParams;
	}

	/* (non-Javadoc)
	 * @see org.logo.viewer.logo.Procedure#getBody()
	 */
	public List<Statement> getBody() {
		return body;
	}

	public void setBody(List<Statement> body) {
		this.body = body;
	}
	
	
	/* (non-Javadoc)
	 * @see org.logo.viewer.logo.Procedure#invoke(java.util.List, org.logo.viewer.logo.ScopedStatement)
	 */
	public void invoke(List<Exp> realParams, ScopedStatement invoker)
			throws ProcedureNotFoundException, ParameterException,
			VariableNotFoundException {
		
		invoker.createNewScope();

		try {

			if (realParams != null) {
				if (realParams.size() != getFormalParams().size()) {
					throw new ParameterException(
							"Parameters mismatch for procedure: " + getName());
				}

				// costruisco il nuovo stack delle def di variabili
				Iterator<Exp> paramsIt = realParams.iterator();
				Iterator<String> formalParamsIt = getFormalParams().iterator();

				while (formalParamsIt.hasNext() && paramsIt.hasNext()) {
					String formalParam = formalParamsIt.next();
					Exp realParam = paramsIt.next();
					Object valueVar = invoker.evaluate(realParam);

					// creo il nuovo stack delle variabili
					invoker.addewScopedVariable(new Assegna(formalParam, valueVar));
				}
			}

			Iterator<Statement> it = getBody().iterator();
			while (it.hasNext()) {
				Statement bodyStatements = it.next();
				bodyStatements.execute();
			}
		} catch (ProceduraTerminataException e) {
			// do nothing
		} finally {
			invoker.cleanScope();
		}

		
	}

	
}
