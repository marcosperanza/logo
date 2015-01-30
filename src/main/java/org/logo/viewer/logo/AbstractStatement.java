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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logo.viewer.Spool;
import org.logo.viewer.exp.Exp;
import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProceduraTerminataException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

/**
 * Rappresenta l asuper classe degli statemets
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public abstract class AbstractStatement implements ScopedStatement {

	final static Logger logger = Logger
			.getLogger(AbstractStatement.class.getName());

	final protected Stack<List<Statement>> scopedVariablesStack = LogoProgram.scopedVariables;
	final protected List<Procedure> precedures = LogoProgram.procedures;
	final protected Spool spool = Spool.getSpool();

	String name;

	/** Costruttore */
	public AbstractStatement() {
		setName(this.getClass().getSimpleName());
	}

	/**
	 * Setta il nome dello statement
	 * 
	 * @param name
	 *            indica il nome dello statemts
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Torna il nome dello statement
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	Assegna getVariable(Variabile parametro)
			throws VariableNotFoundException {

		String nomeVarTMP = parametro.getNameVar();
		Assegna defVariabile = findVariable(nomeVarTMP);
		if (defVariabile == null) {
			throw new VariableNotFoundException("Variabile '"
					+ ((parametro)).getNameVar() + "' non dichiarata!");
		}
		PrintMessage
				.printMessage(" [ Found Value ] " + defVariabile.getValue());

		return defVariabile;
	}

	public Assegna findVariable(String name) {
		for (int i = scopedVariablesStack.size() - 1; i >= 0; i--) {
			for (Statement statement : scopedVariablesStack.get(i)) {
				Assegna assegna = ((Assegna) statement);
				if (assegna.getNameVar().equals(name)) {
					return assegna;
				}
			}
		}
		return null;
	}

	/**
	 * Cerca la procedura "nomeProc" inserita nello stack delle procedure
	 * 
	 * @return Object E' la procedura cercata
	 * @param nomeProcedura
	 *            Il nome della procedura
	 * @param stackProc
	 *            E' lo stack delle procedure
	 */
	public Procedure findProcedure(String nomeProcedura)
			throws ProcedureNotFoundException {
		int i = 0;
		Procedure procedura = null;
		if (precedures != null && nomeProcedura != null) {
			try {
				procedura = precedures.get(i);
				while (!(procedura.getName()).equals(nomeProcedura)) {
					procedura = precedures.get(++i);
				}
			} catch (IndexOutOfBoundsException e) {
				/*
				 * PrintMessage.printMessage("ERRORE: Procedura non Dichiarata");
				 * System.exit(1);
				 */
				throw new ProcedureNotFoundException("Procedura '"
						+ nomeProcedura + "' non trovata!");
			}
		} else {
			throw new ProcedureNotFoundException("Procedura '" + nomeProcedura
					+ "' non trovata!");
		}
		return procedura;
	}

	/**
	 * Valuta il valore dell'oggetto
	 * 
	 * @param value
	 *            Il valore dellogetto da valutare
	 * @param variableStack
	 *            Lo stack delle variabili
	 * @return Il valore reale dell'oggetto
	 * @throws VariableNotFoundException
	 */
	public Object evaluate(Object value) throws VariableNotFoundException {
		Object valoreVar;

		if (value instanceof Variabile) {
			valoreVar = getVariable((Variabile) value).getValue();
		} else if (value instanceof Exp) {
			valoreVar = value;
			do {
				valoreVar = ((Exp) valoreVar).val();

			} while (valoreVar instanceof Exp);

		} else {
			valoreVar = value;
		}
		logger.log(Level.FINER, "Evaluate value for value: '" + value + "': "
				+ valoreVar);
		return valoreVar;
	}

	public void createNewScope() {
		List<Statement> scope = new ArrayList<Statement>();
		scopedVariablesStack.push(scope);
	}

	public void cleanScope() {
		scopedVariablesStack.pop();
	}

	public void addewScopedVariable(Statement var) {
		scopedVariablesStack.peek().add(var);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
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
	public abstract void execute() throws ProceduraTerminataException,
			ProcedureNotFoundException, ParameterException,
			VariableNotFoundException;

}
