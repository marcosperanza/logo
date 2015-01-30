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

package org.logo.viewer.logo.graphic.actions;

/**
 * Una <b>PuntoFinale</b> e' una Espressione
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class PuntoFinale implements AzioneGrafica {

	/**
	 * Rappresenta l'ascissa del punto di inizio
	 */
	private double initialPointX;

	/**
	 * Rappresenta l'ordinata del punto di inizio
	 */
	private double initialPointY;

	/**
	 * Rappresenta l'ascissa del punto finale
	 * 
	 */
	private double finalPointX;

	/**
	 * Rappresenta l'ordinata del punto finale
	 * 
	 */
	private double finalPointY;

	/**
	 * Creates a new instance of PuntoFinale
	 * 
	 * @param initialPointX
	 *            punto iniziale
	 * @param initialPointY
	 *            punto iniziale
	 * @param finalPointX
	 *            punto finale
	 * @param finalPointY
	 *            punto finale
	 */
	public PuntoFinale(double initialPointX, double initialPointY,
			double finalPointX, double finalPointY) {

		this.initialPointX = initialPointX;
		this.initialPointY = initialPointY;
		this.finalPointX = finalPointX;
		this.finalPointY = finalPointY;
	}

	/**
	 * Torna l'ascissa del punto finale
	 * 
	 * @return double
	 */
	public double getX() {
		return this.finalPointX;
	}

	/**
	 * Torna l'ordinata del punto finale
	 * 
	 * @return double
	 */
	public double getY() {
		return this.finalPointY;
	}

	/**
	 * Torna l'ascissa del punto finale
	 * 
	 * @return double
	 */
	public double getInitialX() {
		return this.initialPointX;
	}

	/**
	 * Torna l'ordinata del punto finale
	 * 
	 * @return double
	 */
	public double getInitialY() {
		return this.initialPointY;
	}
}
