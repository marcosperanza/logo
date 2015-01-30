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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;

import org.logo.viewer.logo.graphic.Action;
import org.logo.viewer.logo.graphic.Comando;
import org.logo.viewer.logo.graphic.LogoViewer;
import org.logo.viewer.logo.graphic.actions.AzioneGrafica;
import org.logo.viewer.logo.graphic.actions.PuntoFinale;
import org.logo.viewer.logo.graphic.actions.RotazioneGrafica;
import org.logo.viewer.logo.graphic.frontend.menu.LogoMenuBar;

/**
 * Una <b>LogoInterface</b>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class LogoInterface extends JFrame implements Runnable {

	// QUESTA CLASSE AVRA AL SUO INTERNO LE INFORMAZIONI SULLA GRAFICA:
	// PUNTO DOVE SI TROVA LA TARTARUGA
	// ANGOLAZIONE
	// ECC

	/**
	 * Punto iniziale (Casa)
	 */
	private double homePointX = 0;
	private double homePointY = 0;

	/**
	 * Punto attuale della tartaruga
	 */
	private double actualPointX = 0;
	private double actualPointY = 0;

	/**
	 * Angolazione attuale della tartaruga
	 */
	private double actualAngulation = 0;

	/**
	 * Stato della stampa true se NON sta stampando
	 */
	private boolean idle;

	/**
	 * indice dell'ultimo elemento stampato
	 */
	private int lastPrint = 0;

	/**
	 * Indica se la penna ? sulla lavagna (se True la tartaruga scrive)
	 */
	private boolean pennaGiu = true;

	/**
	 * OffScreen Image
	 */
	private Image offScreenImage;

	/**
	 * Lavagna dell'interfacia grafica
	 */
	private InterfaceDisplay display;

	/**
	 * Il Left Panel dell'intefaccia grafica di LOGO
	 */
	private InterfaceLeftPanel leftPanel;

	/**
	 * La meniu bar dell'intefaccia grafica di LOGO
	 */
	private LogoMenuBar menuBar;

	/**
	 * Indica la grandezza (X) di default della finestra
	 */
	private int defX = 0;

	/**
	 * Indica la grandezza (Y) di default della finestra
	 */
	private int defY = 0;

	/**
	 * associazione con LogoViewer
	 */
	private LogoViewer logoViewer;

	/**
	 * il p?rogramma e' stato stoppato
	 */
	private boolean stop = false;

	final static private String TITLE = "Visualizzatore LOGO";

	/**
	 * Creates a new instance of LogoInterface
	 * 
	 * @param logoViewer
	 *            istanza di logoViewer
	 */
	public LogoInterface(LogoViewer logoViewer) {
		super(TITLE);

		/* prendoledimensioni dello schermo */
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.defX = (screen.width * 90) / 100;
		this.defY = (screen.height * 90) / 100;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* costruisco il center panel */
		InterfaceCenterPanel centerPanel = new InterfaceCenterPanel(this.defX,
				this.defY);
		this.display = centerPanel.getDisplay();
		this.display.setPreferredSize(new Dimension(this.defX, this.defY));

		/* Setto il left panel */
		this.leftPanel = new InterfaceLeftPanel(this.defX, this.defY);
		this.leftPanel.setDisplay(this);

		getContentPane().add(this.leftPanel, BorderLayout.WEST);
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		MediaTracker mt = new MediaTracker(this);
		URL imageURL = LogoInterface.class.getResource("/img/turtle1.gif");
		if (imageURL != null) {
			Image splashIm = Toolkit.getDefaultToolkit().createImage(imageURL);
			mt.addImage(splashIm, 0);
			try {
				mt.waitForID(0);
			} catch (InterruptedException ie) {
			}

			SplashWindow sw = new SplashWindow(this, splashIm);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException ie) {
			}

			sw.dispose();

		}

		imageURL = LogoInterface.class.getResource("/img/turtleIcon.gif");
		Image icona = Toolkit.getDefaultToolkit().createImage(imageURL);
		setIconImage(icona);

		pack();
		setLocationRelativeTo(null); // center it
		setVisible(true);

		/* costruiscoil menu */
		this.menuBar = new LogoMenuBar(this);
		setJMenuBar(this.menuBar);

		/* Setto le condizioni iniziali della tartaruga */
		this.setHomePoint(centerPanel.getCenter());
		this.display.initDispay();
		this.idle = true;
		this.logoViewer = logoViewer;
		this.leftPanel.setTextLabelActCoordinate("" + this.actualPointX, ""
				+ this.actualPointY);
		this.leftPanel.setTextLabelActAngulation("" + this.actualAngulation);

	}

	/**
	 * Run (Runnable)
	 */
	public void run() {

	}

	/**
	 * Aggiorna la figura
	 * 
	 * @param spool
	 *            spool di stampa
	 */
	public void updateFigure(Action action) {

		if (action == null) {
			return;
		}

		this.idle = false;
		if (action instanceof Comando) {
			String comando = ((Comando) action).getComando();
			if (comando.equals("Su")) {
				this.pennaGiu = false;
			}

			if (comando.equals("Giu")) {
				this.pennaGiu = true;
			}

			if (comando.equals("Casa")) {
				this.actualPointX = this.homePointX;
				this.actualPointY = this.homePointY;
			}

			if (comando.equals("Posizione")) {
				Point p = (Point) ((Comando) action).getParam();
				this.actualPointX = p.getX();
				this.actualPointY = p.getY();
			}

			if (comando.equals("Pulisci")) {
				this.crearScreen();
			}

			if (comando.equals("BeginProgram")) {
				this.leftPanel.painting(true);
				this.menuBar.painting(true);
			}

			if (comando.equals("EndProgram")) {
				this.endProgram();
			}
		}

		AzioneGrafica azioneGrafica = action.esegui(this.actualPointX,
				this.actualPointY, this.actualAngulation);
		if (azioneGrafica != null) {
			// aggiornare anche l'angolazione
			if (azioneGrafica instanceof PuntoFinale) {
				this.actualPointX = ((PuntoFinale) azioneGrafica).getX();
				this.actualPointY = ((PuntoFinale) azioneGrafica).getY();
				this.leftPanel.setTextLabelActCoordinate(""
						+ ((PuntoFinale) azioneGrafica).getX(), ""
						+ ((PuntoFinale) azioneGrafica).getY());

			}

			if (azioneGrafica instanceof RotazioneGrafica) {
				RotazioneGrafica rot = ((RotazioneGrafica) azioneGrafica);
				this.actualAngulation = rot.getAngulation();
				this.leftPanel.setTextLabelActAngulation("" + actualAngulation);
			}

			if (this.pennaGiu) {
				this.display.setAngulation(this.actualAngulation);
				this.display.stampa(azioneGrafica);
			}
		}

		this.idle = true;
	}

	public boolean isIdle() {
		return this.idle;
	}

	/**
	 * Stampabile?
	 * 
	 * @return true se si puo stamapre
	 */
	protected boolean isDrawable() {
		return ((this.display.getOffScreenImage() != null));
	}

	/**
	 * Setta il punto di Home
	 * 
	 * @param d
	 *            Punto di Home
	 */
	protected void setHomePoint(Dimension d) {
		this.homePointX = d.getWidth();
		this.homePointY = d.getHeight();
		this.actualPointX = this.homePointX;
		this.actualPointY = this.homePointY;
	}

	/**
	 * Torna il punto di Home
	 * 
	 * @return Dimension punto di home
	 */
	protected Dimension getHomePoint() {
		return new Dimension((int) this.homePointX, (int) this.homePointY);
	}

	/**
	 * Vai al punto di home
	 */
	protected void goToHome() {
		this.actualPointX = this.homePointX;
		this.actualPointY = this.homePointY;
	}

	/**
	 * Torna l'angolazione attuale
	 * 
	 * @return double angolazione
	 */
	protected double getActualAngulation() {
		return this.actualAngulation;
	}

	/**
	 * setta l'angolazione attuale
	 * 
	 * @param ang
	 *            angolazione
	 */
	protected void setActualAngulation(double ang) {
		this.actualAngulation = ang;
	}

	/**
	 * torna il punto attuale
	 * 
	 * @return Dimension punto attuale
	 */
	protected Dimension getActualPoint() {
		return new Dimension((int) this.actualPointX, (int) this.actualPointX);
	}

	/**
	 * Setta il puntoa ttuale
	 * 
	 * @param point
	 *            punto
	 */
	protected void setActualPoint(Dimension point) {
		this.actualPointX = point.width;
		this.actualPointY = point.height;
	}

	/**
	 * Cancella lo schermo
	 */
	protected void crearScreen() {
		this.display.clearScreen();
	}

	/**
	 * Lancia il programma
	 * 
	 * @throws Exception
	 */
	protected void startProgram() throws Exception {
		this.stop = false;
		this.logoViewer.startProgram();
	}

	/**
	 * Lancia il programma
	 */
	protected void stopProgram() {
		this.stop = true;
		this.leftPanel.painting(false);
		this.menuBar.painting(false);
		this.logoViewer.stopProgram();
	}

	/**
	 * Punto valido
	 * 
	 * @param d
	 *            Punto
	 * @return true se e' un punto valido
	 */
	protected boolean isValidPoint(Dimension d) {
		return ((d.width < this.display.getX() && d.width > 0) || (d.height < this.display
				.getY() && d.height > 0));

	}

	/**
	 * Programam finito
	 */
	public void endProgram() {
		this.leftPanel.painting(false);
		this.menuBar.painting(false);
	}

	/**
	 * torna il background color
	 * 
	 * @return Color colore background
	 */
	public Color getBackGroundColorDisplay() {
		return this.display.getBackgroundColor();
	}

	/**
	 * torna il forground color
	 * 
	 * @return Color colore forground
	 */
	public Color getForGroundColorDisplay() {
		return this.display.getForgroundColor();
	}

	/**
	 * setta il background color
	 * 
	 * @param bgc
	 *            colore background
	 */
	public void setBackGroundColorDisplay(Color bgc) {
		this.display.setBackgroundColor(bgc);
		// this.display.
	}

	/**
	 * setta il forground color
	 * 
	 * @param fgc
	 *            colore forground
	 */
	public void setForGroundColorDisplay(Color fgc) {
		this.display.setForgroundColor(fgc);
	}

	/**
	 * @param actionCommand
	 * @throws Exception
	 */
	public void loadProgram(String program) throws Exception {
		this.logoViewer.loadProgram(program);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Frame#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {

		String value = TITLE + " - [" + title + "]";

		super.setTitle((title == null ? TITLE : value));
	}
}
