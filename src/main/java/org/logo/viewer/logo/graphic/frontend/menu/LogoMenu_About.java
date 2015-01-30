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

package org.logo.viewer.logo.graphic.frontend.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.logo.viewer.logo.graphic.frontend.LogoInterface;

/**
 * Una <b>LogoMenu_About</b>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class LogoMenu_About extends JMenu implements ActionListener {

	/**
	 * Associazione con logo interface
	 */
	private LogoInterface logointerface;

	/**
	 * Creates a new instance of LogoMenu
	 * 
	 * @param logointerface
	 *            istanza di logo Interface
	 */
	public LogoMenu_About(LogoInterface logointerface) {
		super("About");
		this.logointerface = logointerface;
		setMnemonic(KeyEvent.VK_A);

		JMenuItem menuItem = makeEntryMenu();

		add(menuItem);
	}

	/**
	 * Crea il Menu About
	 * 
	 * @return Il menu Item
	 */
	private JMenuItem makeEntryMenu() {

		JMenuItem menuItemExit = new JMenuItem("About", KeyEvent.VK_A);
		menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		menuItemExit.setActionCommand("About");
		menuItemExit.addActionListener(this);
		return menuItemExit;

	}

	/**
	 * Action Perfored
	 * 
	 * @param e
	 *            Action Event
	 */
	public void actionPerformed(ActionEvent e) {
		if ("About".equals(e.getActionCommand())) {
			/*
			 * JOptionPane.showMessageDialog(logointerface, "Logo\npipoo",
			 * "About",JOptionPane.INFORMATION_MESSAGE);
			 */
			JDialog f = new JDialog(this.logointerface, "About", true);
			f.setResizable(false);
			f.setSize(400, 400);
			JTabbedPane tPanel = new JTabbedPane();

			// MediaTracker mt = new MediaTracker(this);
			Image splashIm = null;
			MyLogo p2 = null;
			MediaTracker mt = new MediaTracker(this);
			URL imageURL = LogoMenu_About.class
					.getResource("../Img/turtle1.gif");
			if (imageURL != null) {
				splashIm = Toolkit.getDefaultToolkit().createImage(imageURL);
				p2 = new MyLogo(splashIm);
			}

			JPanel tot = new JPanel(new BorderLayout());

			JPanel p = new JPanel(new GridLayout(6, 1));
			p.setBorder(BorderFactory.createTitledBorder("About"));

			JLabel l = new JLabel("Visualizzatore LOGO");
			l.setFont(new Font(null, Font.BOLD, 20));

			JLabel l2 = new JLabel(
					"Programma scritto da Marco Speranza (marco_speranza@tin.it)");
			JLabel l3 = new JLabel(
					"per il corso di Linguaggi di Programmazione Speciali");
			JLabel l4 = new JLabel("prof. Laurent Thery");

			JLabel l5 = new JLabel(
					"Developed with NetBeans IDE 3.5.1 - Java 1.4.1");
			JLabel l6 = new JLabel("Under Linux Slackware 9.0");
			l5.setFont(new Font(null, Font.PLAIN, 10));
			l6.setFont(new Font(null, Font.PLAIN, 10));

			p.add(l);
			p.add(l2);
			p.add(l3);
			p.add(l4);
			p.add(l5);
			p.add(l6);

			tot.add(p, BorderLayout.CENTER);

			tPanel.addTab("About", p2);
			tPanel.addTab("Detail", tot);

			f.setLocationRelativeTo(null); // center it
			f.getContentPane().add(tPanel);
			f.pack();
			f.setVisible(true);

		}
	}

}

class MyLogo extends JPanel {

	Image i;

	MyLogo(Image i) {
		super(new GridLayout(1, 1));
		// super();
		setPreferredSize(new Dimension(400, 400));
		this.i = i;
	}

	MyLogo(Image i, int w, int h) {
		super(new GridLayout(1, 1));
		setSize(w, h);
		// setPreferredSize(new Dimension(w, h));
		this.i = i;
	}

	public void paint(Graphics g) {
		if (i != null) {
			g.drawImage(i, 0, 0, this);
		}
	}

}
