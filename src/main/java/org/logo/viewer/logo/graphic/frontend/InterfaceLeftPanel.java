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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * E' il Left Panel dell'interfaccia grafica di LOGO.
 * 
 * @author Marco Speranza
 * @version 0.1
 */
public class InterfaceLeftPanel extends JPanel implements ActionListener {

	/**
	 * label attuale angolazione
	 */
	private JLabel labelActAngValue;

	/**
	 * label coordinate
	 */
	private JLabel labelActXValue;

	/**
	 * label coordinate
	 */
	private JLabel labelActYValue;

	/**
	 * text field coordinate
	 */
	private JTextField textX;

	/**
	 * text field coordinate
	 */
	private JTextField textY;

	/**
	 * text field angolazione
	 */
	private JTextField textAng;

	/**
	 * Bottoni del pannello
	 */
	private JButton buttonStart;

	/**
	 * Bottoni del pannello
	 */
	private JButton buttonStop;

	/**
	 * Bottoni del pannello
	 */
	private JButton buttonClear;

	/**
	 * Bottoni del pannello
	 */
	private JButton buttonGoToHome;

	/**
	 * Associaziomne con logo Interface
	 */
	private LogoInterface logoInterface;

	/**
	 * Creates a new instance of InterfaceLeftPanel
	 * 
	 * @param x
	 *            grandezza del frame
	 * @param y
	 *            grandezza del frame
	 */
	public InterfaceLeftPanel(int x, int y) {
		super(new FlowLayout(FlowLayout.CENTER));

		setPreferredSize(new Dimension(percent(x, 20), y));
		setBorder(BorderFactory.createTitledBorder("LeftMenu"));

		// aggiungo il pannello dell'angolazione attuale
		add(makePanelActAng(new Dimension(percent(x, 18), percent(y, 8))));

		// aggiungo il pannello dellecoordinate attuali
		add(makePanelActCoor(new Dimension(percent(x, 18), percent(y, 14))));

		// aggiungo il pannello dei comandi
		add(makePanelPoint(new Dimension(percent(x, 18), percent(y, 14))));

		// aggiungo il pannello dei comandi
		add(makePanelSetAng(new Dimension(percent(x, 18), percent(y, 9))));

		// aggiungo il pannello dei comandi
		add(makePanelCommand(new Dimension(percent(x, 18), percent(y, 22))));
	}

	/**
	 * Setta il testo della Label
	 * 
	 * @param s
	 *            La stringa da stampare
	 */
	protected void setTextLabelActAngulation(String s) {
		this.labelActAngValue.setText(s);
	}

	/**
	 * Setta il testo della Label
	 * 
	 * @param x
	 *            La coordinata X
	 * @param y
	 *            La coordinata Y
	 */
	protected void setTextLabelActCoordinate(String x, String y) {
		this.labelActXValue.setText(x);
		this.labelActYValue.setText(y);

	}

	/**
	 * Creao il pannello dell'angoalzione
	 * 
	 * @param dim
	 *            Dimensione delpannello
	 * @return JPanel
	 */
	private JPanel makePanelActAng(Dimension dim) {
		/* creo il pannello dell'angolazione */
		JPanel panelActualAng = new JPanel(new GridLayout(1, 1));
		panelActualAng
				.setBorder(BorderFactory.createTitledBorder("Angulation"));
		panelActualAng.setPreferredSize(dim);
		/* Label Actual angulation */
		JLabel labelActAng = new JLabel("Actual Ang. : ");
		labelActAng.setVerticalTextPosition(JLabel.CENTER);
		labelActAng.setHorizontalTextPosition(JLabel.CENTER);
		/* Label Actual angulation Value */
		labelActAngValue = new JLabel("");
		// Set the position of the text, relative to the icon:
		labelActAngValue.setVerticalTextPosition(JLabel.CENTER);
		labelActAngValue.setHorizontalTextPosition(JLabel.CENTER);
		panelActualAng.add(labelActAng);
		panelActualAng.add(labelActAngValue);
		return panelActualAng;

	}

	/**
	 * Creao il pannello delle coordinate
	 * 
	 * @param dim
	 *            Dimensione delpannello
	 * @return JPanel
	 */
	private JPanel makePanelActCoor(Dimension dim) {
		/* creo il pannello delle coordinate */
		JPanel panelActualCoo = new JPanel(new GridLayout(2, 2));

		// Dimension actPos = this.logoInterface.getHomePoint();

		panelActualCoo.setBorder(BorderFactory
				.createTitledBorder("Coordinated"));
		panelActualCoo.setPreferredSize(dim);
		/* Label Actual X */
		JLabel labelActX = new JLabel("Actual X:");
		labelActX.setVerticalTextPosition(JLabel.CENTER);
		labelActX.setHorizontalTextPosition(JLabel.RIGHT);
		/* Label Actual X value */
		labelActXValue = new JLabel("");
		labelActXValue.setVerticalTextPosition(JLabel.CENTER);
		labelActXValue.setHorizontalTextPosition(JLabel.LEFT);

		/* Label Actual Y */
		JLabel labelActY = new JLabel("Actual Y:");
		labelActY.setVerticalTextPosition(JLabel.CENTER);
		labelActY.setHorizontalTextPosition(JLabel.RIGHT);

		/* Label Actual Y value */
		labelActYValue = new JLabel("");
		labelActYValue.setVerticalTextPosition(JLabel.CENTER);
		labelActYValue.setHorizontalTextPosition(JLabel.LEFT);

		panelActualCoo.add(labelActX);
		panelActualCoo.add(labelActXValue);

		panelActualCoo.add(labelActY);
		panelActualCoo.add(labelActYValue);

		return panelActualCoo;

	}

	/**
	 * Creao il pannello delle coordinate
	 * 
	 * @param dim
	 *            Dimensione delpannello
	 * @return JPanel
	 */
	private JPanel makePanelCommand(Dimension dim) {
		/* creo il pannello delle coordinate */
		JPanel panel = new JPanel(new GridLayout(0, 1));

		panel.setBorder(BorderFactory.createTitledBorder("Command Panel"));
		panel.setPreferredSize(dim);

		buttonStart = new JButton("Start");
		buttonStart.setActionCommand("Start");
		buttonStart.setMnemonic(KeyEvent.VK_S);
		// buttonStart.setSize(d.width, 50);
		buttonStart.addActionListener(this);

		buttonStop = new JButton("Stop");
		buttonStop.setActionCommand("Stop");
		buttonStop.setMnemonic(KeyEvent.VK_S);
		buttonStop.addActionListener(this);

		buttonClear = new JButton("Clear Screen");
		buttonClear.setActionCommand("ClearScreen");
		buttonClear.setMnemonic(KeyEvent.VK_L);
		buttonClear.addActionListener(this);

		buttonGoToHome = new JButton("Go To HOME");
		buttonGoToHome.setActionCommand("GoToHome");
		buttonGoToHome.setMnemonic(KeyEvent.VK_H);
		buttonGoToHome.addActionListener(this);

		JButton buttonExit = new JButton("Exit");
		buttonExit.setActionCommand("Exit");
		buttonExit.setMnemonic(KeyEvent.VK_X);
		buttonExit.addActionListener(this);

		panel.add(buttonStart);
		panel.add(buttonStop);
		panel.add(buttonGoToHome);
		panel.add(buttonClear);
		panel.add(buttonExit);
		return panel;

	}

	/**
	 * Setta il display
	 * 
	 * @param logoInterface
	 *            interfaccia
	 */
	protected void setDisplay(LogoInterface logoInterface) {
		this.logoInterface = logoInterface;
	}

	/**
	 * Crea il pannello
	 * 
	 * @param dim
	 *            Dimensione delpannello
	 * @return JPanel
	 */
	private JPanel makePanelPoint(Dimension dim) {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.setBorder(BorderFactory.createTitledBorder("Set an Start Point"));
		panel.setPreferredSize(dim);

		JPanel panelText = new JPanel(new GridLayout(2, 2));
		JLabel labelX = new JLabel("Set X Point : ");
		labelX.setVerticalTextPosition(JLabel.CENTER);
		labelX.setHorizontalTextPosition(JLabel.CENTER);

		textX = new JTextField(5);
		textX.setText("");

		JLabel labelY = new JLabel("Set Y Point : ");
		labelY.setVerticalTextPosition(JLabel.CENTER);
		labelY.setHorizontalTextPosition(JLabel.CENTER);

		textY = new JTextField(5);
		textY.setText("");

		JPanel panelButton = new JPanel(new GridLayout(1, 1));

		buttonStart = new JButton("OK");
		buttonStart.setActionCommand("OK");
		buttonStart.setMnemonic(KeyEvent.VK_O);
		// buttonStart.setSize(d.width, 50);
		buttonStart.addActionListener(this);

		panelText.add(labelX);
		panelText.add(textX);
		panelText.add(labelY);
		panelText.add(textY);

		panelButton.add(buttonStart);

		panel.add(panelText);
		panel.add(panelButton);

		return panel;
	}

	/**
	 * Crea il pannello
	 * 
	 * @param dim
	 *            Dimensione delpannello
	 * @return JPanel
	 */
	private JPanel makePanelSetAng(Dimension dim) {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.setBorder(BorderFactory
				.createTitledBorder("Set an Start Angulation"));
		panel.setPreferredSize(dim);

		JPanel panelText = new JPanel(new GridLayout(1, 2));
		JLabel labelX = new JLabel("Set Ang. : ");
		labelX.setVerticalTextPosition(JLabel.CENTER);
		labelX.setHorizontalTextPosition(JLabel.CENTER);

		textAng = new JTextField(3);
		textAng.setText("");

		JPanel panelButton = new JPanel(new GridLayout(1, 1));

		buttonStart = new JButton("OK");
		buttonStart.setActionCommand("OKAng");
		buttonStart.setMnemonic(KeyEvent.VK_O);
		// buttonStart.setSize(d.width, 50);
		buttonStart.addActionListener(this);

		panelText.add(labelX);
		panelText.add(textAng);
		panelButton.add(buttonStart);

		panel.add(panelText);
		panel.add(panelButton);

		return panel;

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

	public void actionPerformed(ActionEvent e) {
		if ("ClearScreen".equals(e.getActionCommand())) {
			this.logoInterface.crearScreen();
		} else if ("GoToHome".equals(e.getActionCommand())) {
			this.logoInterface.goToHome();
			Dimension d = this.logoInterface.getHomePoint();
			this.setTextLabelActCoordinate("" + d.width, "" + d.height);
		} else if ("Exit".equals(e.getActionCommand())) {
			System.exit(0);
		} else if ("Start".equals(e.getActionCommand())) {
			try {
				this.logoInterface.startProgram();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(logoInterface, e2
						.getLocalizedMessage(), "Errore",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if ("Stop".equals(e.getActionCommand())) {
			this.logoInterface.setTitle(null);
			this.logoInterface.stopProgram();
		} else if ("OK".equals(e.getActionCommand())) {
			String message = null;
			try {
				if (textX.getText().matches("[0-9]+")
						&& textY.getText().matches("[0-9]+")) {

					Integer x = new Integer(textX.getText());
					Integer y = new Integer(textY.getText());
					Dimension d = new Dimension(x.intValue(), y.intValue());
					this.logoInterface.setActualPoint(d);
					this.setTextLabelActCoordinate("" + x.intValue(), ""
							+ y.intValue());
				} else {
					message = "";
				}
			} catch (NumberFormatException exc) {
				message = exc.getLocalizedMessage();

			}

			if (message != null) {
				JOptionPane.showMessageDialog(logoInterface,
						"Coordinate non valide: " + message, "Errore",
						JOptionPane.ERROR_MESSAGE);

			}
		} else if ("OKAng".equals(e.getActionCommand())) {
			boolean errore = false;
			try {
				if (textAng.getText().matches("[0-9]+")) {
					Integer x = new Integer(textAng.getText());

					if (x.intValue() >= 0 && x.intValue() <= 360) {
						this.logoInterface.setActualAngulation(x.doubleValue());
						this.setTextLabelActAngulation("" + x.intValue());
					} else {
						errore = true;
					}
				} else {
					errore = true;
				}
			} catch (NumberFormatException exc) {
				errore = true;

			}

			if (errore) {
				JOptionPane.showMessageDialog(logoInterface,
						"Angolazione non valida", "Errore",
						JOptionPane.ERROR_MESSAGE);

			}

		}

	}

	/**
	 * Comportament o quando sui sta stampando
	 * 
	 * @param value
	 *            true se si sta stampando
	 */
	protected void painting(boolean value) {
		if (value == true) {
			this.buttonStart.setEnabled(false);
			this.buttonGoToHome.setEnabled(false);
			this.buttonClear.setEnabled(false);
		} else {
			this.buttonStart.setEnabled(true);
			this.buttonGoToHome.setEnabled(true);
			this.buttonClear.setEnabled(true);
		}

	}

}
