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

package org.logo.viewer.logo.graphic;

import org.logo.viewer.logo.graphic.actions.AzioneGrafica;

/**
 * Elemento della coda di stampa. Rappresenta un comando non grafico della
 * tartaruga
 * 
 * @author Marco Speranza
 * @version 0.1
 */
public class Comando implements Action {

	/**
	 * Nome del Comando da eseguire
	 */
	private String commandName;

	/**
	 * Parametro Eventuale del comado
	 */
	private Object param;

	/**
	 * Creates a new instance of Action
	 * 
	 * @param commandName
	 *            nome del comando
	 */
	public Comando(String commandName) {
		this.commandName = commandName;
	}

	/**
	 * Creates a new instance of Action
	 * 
	 * @param commandName
	 *            nome del comando
	 * @param param
	 *            parametro
	 */
	public Comando(String commandName, Object param) {
		this.commandName = commandName;
		this.param = param;
	}

	/**
	 * Esegue il comando
	 * 
	 * @param initialPointX
	 *            punto iniziale
	 * @param initialPointY
	 *            punto iniziale
	 * @param angulation
	 *            angolazione
	 * @return istanza di AzioneGrafica che verra' stampata a video
	 */
	public AzioneGrafica esegui(double initialPointX, double initialPointY,
			double angulation) {

		return null;
	}

	/**
	 * Torna il comando
	 * 
	 * @return String comando
	 */
	public String getComando() {
		return this.commandName;

	}

	/**
	 * Torana il parametro
	 * 
	 * @return Object parametro
	 */
	public Object getParam() {
		return this.param;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comando [commandName=" + commandName + ", param=" + param + "]";
	}

}
