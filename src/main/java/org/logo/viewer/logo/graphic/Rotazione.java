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
import org.logo.viewer.logo.graphic.actions.RotazioneGrafica;

/**
 * Elemento della coda di stampa. Rappresenta una ROTAZIONE della tartaruga
 * 
 * @author Marco Speranza
 * @version 0.1
 */
public class Rotazione implements Action {

	/**
	 * Angolo di rotazione in gradi
	 */
	private double localAngulation;

	/**
	 * Creates a new instance of Rotazione
	 * 
	 * @param angulation
	 *            angolo in radianti
	 */
	public Rotazione(double angulation) {
		this.localAngulation = (angulation);
	}

	/**
	 * Esegue l'azione. Calcola il valore finale a partire che provoca questa
	 * azione
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

		double newAngulation = ((angulation + this.localAngulation) % 360);
		return new RotazioneGrafica(newAngulation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rotazione [localAngulation=" + localAngulation + "]";
	}

}
