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
 * Una <b>RotazioneGrafica</b> e' una Espressione
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class RotazioneGrafica implements AzioneGrafica {

	/**
	 * Rappresenta l'angolo finale
	 */
	private double finalAngulation;

	/**
	 * Creates a new instance of Rotazione
	 * 
	 * @param finalAngulation
	 *            angolazione
	 */
	public RotazioneGrafica(double finalAngulation) {
		this.finalAngulation = finalAngulation;
	}

	/**
	 * Torna l'angolazione finale
	 * 
	 * @return double angolazione
	 */
	public double getAngulation() {
		return this.finalAngulation;
	}

}
