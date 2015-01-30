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

package org.logo.viewer.logo.graphic.frontend;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Una <b>LogoInterface</b>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class InterfaceCenterPanel extends JPanel {

	/**
	 * Associazione con interface Display
	 */
	private InterfaceDisplay display;

	/**
	 * Creates a new instance of InterfaceCenterPanel
	 * 
	 * @param x
	 *            dimensione
	 * @param y
	 *            dimensione
	 */
	public InterfaceCenterPanel(int x, int y) {

		super(new GridLayout(1, 1));
		setPreferredSize(new Dimension(percent(x, 80), y));
		setBorder(BorderFactory.createTitledBorder("Lavagna"));
		this.display = new InterfaceDisplay();
		add(this.display);
	}

	/**
	 * Torna il diplay disegnabile
	 * 
	 * @param InterfaceDisplay
	 */
	public InterfaceDisplay getDisplay() {
		return this.display;
	}

	/**
	 * Calcola la percentuale di un numero
	 * 
	 * @param num
	 *            Numero del quale si vuole calcolare la percentuale
	 * @param per
	 *            Percentiuale
	 * @return int La percentiale
	 */
	private int percent(int num, int per) {
		return (num * per) / 100;
	}

	/**
	 * Calcola il centro della lavagna
	 * 
	 * @return Dimention il punto di centro
	 */
	public Dimension getCenter() {
		Dimension d = this.display.getSize();
		return new Dimension(d.width / 2, d.width / 2);
	}

}
