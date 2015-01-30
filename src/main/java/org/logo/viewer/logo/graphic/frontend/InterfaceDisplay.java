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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import org.logo.viewer.logo.graphic.actions.AzioneGrafica;
import org.logo.viewer.logo.graphic.actions.PuntoFinale;

/**
 * Una <b>InterfaceDisplay</b>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class InterfaceDisplay extends JPanel {

	/**
	 * Angolazione
	 */
	private double angulation;

	/**
	 * Associazione alla classe AzioneGrafica
	 */
	private AzioneGrafica azioneDaStampare;

	/**
	 * Immagine di OffScreen
	 */
	private Image offScreenImage;

	/**
	 * BackGround Color
	 */
	private Color backgroundColor = Color.BLACK;

	/**
	 * ForGround color
	 */
	private Color color = Color.WHITE;

	/**
	 * true se si sta facendo il clear dello schermo
	 */
	private boolean clear = false;

	/**
	 * Costruttore
	 */
	public InterfaceDisplay() {

		setDoubleBuffered(true);
		setAutoscrolls(true);
		setBackground(backgroundColor);
	}

	/**
	 * Setta l'immagine di OffScreen
	 * 
	 * @param osi
	 *            Immagine di offscreen
	 */
	protected void setOffScreenImage(Image osi) {
		this.offScreenImage = osi;
	}

	/**
	 * Torna l'immagine di offscreeen
	 * 
	 * @return Image Off screen image
	 */
	protected Image getOffScreenImage() {
		return this.offScreenImage;
	}

	/**
	 * Torna l'angolazione attuale
	 * 
	 * @return double l'angolazione
	 */
	protected double getAngulation() {
		return this.angulation;
	}

	/**
	 * Setta l'angolazione
	 * 
	 * @param angulation
	 *            angolazione
	 */
	protected void setAngulation(double angulation) {
		this.angulation = angulation;
	}

	/**
	 * Paint component
	 * 
	 * @param g
	 *            Graphics
	 */
	public void paintComponent(Graphics g) {
		if (this.offScreenImage != null) {
			g.drawImage(this.offScreenImage, 0, 0, null);
		} else {
			this.initDispay();
		}
		drawFigure(g);
	}

	/**
	 * Stampa la figura
	 * 
	 * @param g
	 *            Graphics
	 */
	protected void drawFigure(Graphics g) {
		g.setColor(color);

		PuntoFinale p = (PuntoFinale) azioneDaStampare;
		if (p != null && !this.clear) {
			g.drawLine((int) p.getInitialX(), (int) p.getInitialY(), (int) p
					.getX(), (int) p.getY());

		} else {
			g.drawImage(this.offScreenImage, 0, 0, null);
			azioneDaStampare = null;
			this.clear = false;
		}
	}

	/**
	 * Stampa l'azione
	 * 
	 * @param azioneDaStampare
	 *            azione che deve essere stampata
	 */
	protected void stampa(AzioneGrafica azioneDaStampare) {
		Graphics g;
		if (offScreenImage != null) {
			g = offScreenImage.getGraphics();
		} else {
			offScreenImage = createImage(getSize().width, getSize().height);
			setOffScreenImage(this.offScreenImage);
			g = offScreenImage.getGraphics();
		}

		if (azioneDaStampare instanceof PuntoFinale) {
			this.azioneDaStampare = azioneDaStampare;
			drawFigure(g);
			g.dispose();
			repaint();
		}
	}

	/**
	 * inizializza il display
	 */
	protected void initDispay() {
		Image offScreenImage = createImage(getSize().width, getSize().height);
		setOffScreenImage(offScreenImage);
		Graphics g = offScreenImage.getGraphics();
		g.setColor(this.backgroundColor);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setColor(Color.black);
		g.drawRect(0, 0, getSize().width, getSize().height);
		g.dispose();
	}

	/**
	 * Pulisce lo schermo
	 */
	protected void clearScreen() {
		initDispay();
		this.clear = true;
		repaint();
	}

	/**
	 * Torna il colore di background
	 * 
	 * @return Color il colore
	 */
	protected Color getBackgroundColor() {
		return this.backgroundColor;
	}

	/**
	 * Torna il colore di forground
	 * 
	 * @return Color il colore
	 */
	protected Color getForgroundColor() {
		return this.color;
	}

	/**
	 * Setta il colore di background
	 * 
	 * @param bgc
	 *            colore di background
	 */
	protected void setBackgroundColor(Color bgc) {
		this.backgroundColor = bgc;
		aggiornaSchermo();
	}

	/**
	 * Setta il colore di forground
	 * 
	 * @param fgc
	 *            colore di forground
	 */
	protected void setForgroundColor(Color fgc) {
		this.color = fgc;
	}

	/**
	 * Aggirna il displ;ay
	 */
	private void aggiornaSchermo() {
		Graphics g = offScreenImage.getGraphics();
		g.setColor(this.backgroundColor);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setColor(Color.black);
		g.drawRect(0, 0, getSize().width, getSize().height);
		g.dispose();
		this.clear = true;
		repaint();
	}
}
