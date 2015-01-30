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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.logo.viewer.logo.graphic.frontend.LogoInterface;

/**
 * Una <b>LogoMenu_About</b>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class LogoMenu_Pref extends JMenu implements ActionListener,
		ChangeListener {

	/**
	 * Interfaccia grafica
	 */
	private LogoInterface logoInterface;

	/**
	 * Color choser
	 */
	private JColorChooser color;

	/**
	 * Color choser
	 */
	private JColorChooser colorBack;

	/**
	 * Creates a new instance of LogoMenu
	 * 
	 * @param logoInterface
	 *            Istanza di logo nterface
	 */
	public LogoMenu_Pref(LogoInterface logoInterface) {
		super("Preferences");
		this.logoInterface = logoInterface;

		setMnemonic(KeyEvent.VK_A);

		JMenu menuItemFgC = makeEntryFgC();

		JMenu menuItemBgC = makeEntryBgC();
		/*
    
         **/
		add(menuItemFgC);
		// addSeparator();
		add(menuItemBgC);
	}

	/**
	 * Crea il menu
	 * 
	 * @return JMenu
	 */
	private JMenu makeEntryFgC() {

		JMenu subMenu = new JMenu("Set forground color");

		ButtonGroup group = new ButtonGroup();

		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Black");

		Color bgColor = this.logoInterface.getForGroundColorDisplay();
		if (bgColor.equals(Color.BLACK)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setForColor_BLACK");
		rbMenuItem.addActionListener(this);

		// boolean selected ;
		rbMenuItem = new JRadioButtonMenuItem("White");
		if (bgColor.equals(Color.WHITE)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setForColor_WHITE");
		rbMenuItem.addActionListener(this);

		rbMenuItem = new JRadioButtonMenuItem("Blue");
		if (bgColor.equals(Color.BLUE)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setForColor_BLUE");
		rbMenuItem.addActionListener(this);

		rbMenuItem = new JRadioButtonMenuItem("Red");
		if (bgColor.equals(Color.RED)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setForColor_RED");
		rbMenuItem.addActionListener(this);

		subMenu.addSeparator();

		rbMenuItem = new JRadioButtonMenuItem("Other Color");
		rbMenuItem.setActionCommand("OtherColorFore");
		group.add(rbMenuItem);
		rbMenuItem.addActionListener(this);
		subMenu.add(rbMenuItem);

		return subMenu;

	}

	/**
	 * Crea il menu
	 * 
	 * @return JMenu
	 */
	private JMenu makeEntryBgC() {

		JMenu subMenu = new JMenu("Set background color");

		ButtonGroup group = new ButtonGroup();

		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Black");

		Color bgColor = this.logoInterface.getBackGroundColorDisplay();
		if (bgColor.equals(Color.BLACK)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setBakColor_BLACK");
		rbMenuItem.addActionListener(this);

		rbMenuItem = new JRadioButtonMenuItem("White");
		if (bgColor.equals(Color.WHITE)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setBakColor_WHITE");
		rbMenuItem.addActionListener(this);

		rbMenuItem = new JRadioButtonMenuItem("Blue");
		if (bgColor.equals(Color.BLUE)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setBakColor_BLUE");
		rbMenuItem.addActionListener(this);

		rbMenuItem = new JRadioButtonMenuItem("Red");
		if (bgColor.equals(Color.RED)) {
			rbMenuItem.setSelected(true);
		} else {
			rbMenuItem.setSelected(false);
		}
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem.setActionCommand("setBakColor_RED");
		rbMenuItem.addActionListener(this);

		subMenu.addSeparator();

		rbMenuItem = new JRadioButtonMenuItem("Other Color");
		rbMenuItem.setActionCommand("OtherColorBack");
		group.add(rbMenuItem);
		rbMenuItem.addActionListener(this);
		subMenu.add(rbMenuItem);

		return subMenu;

	}

	/**
	 * Crea il frame del color choser
	 * 
	 */
	private void makeColorFrameFore() {
		JDialog dialog = new JDialog(logoInterface, "Foregroung Color", true);
		color = new JColorChooser(Color.BLACK);
		color.getSelectionModel().addChangeListener(this);

		dialog.getContentPane().add(color);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);

	}

	/** Crea il frame del color choser */
	private void makeColorFrameBack() {
		JDialog dialog = new JDialog(logoInterface, "Backgroung Color", true);
		colorBack = new JColorChooser(Color.BLACK);
		colorBack.getSelectionModel().addChangeListener(this);

		dialog.setLocationRelativeTo(null);
		dialog.getContentPane().add(colorBack);
		dialog.pack();
		dialog.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if ("setBakColor_WHITE".equals(e.getActionCommand())) {
			this.logoInterface.setBackGroundColorDisplay(Color.WHITE);
		} else if ("setBakColor_BLACK".equals(e.getActionCommand())) {
			this.logoInterface.setBackGroundColorDisplay(Color.BLACK);
		} else if ("setBakColor_BLUE".equals(e.getActionCommand())) {
			this.logoInterface.setBackGroundColorDisplay(Color.BLUE);
		} else if ("setBakColor_RED".equals(e.getActionCommand())) {
			this.logoInterface.setBackGroundColorDisplay(Color.RED);
		} else if ("setForColor_WHITE".equals(e.getActionCommand())) {
			this.logoInterface.setForGroundColorDisplay(Color.WHITE);
		} else if ("setForColor_BLACK".equals(e.getActionCommand())) {
			this.logoInterface.setForGroundColorDisplay(Color.BLACK);
		} else if ("setForColor_BLUE".equals(e.getActionCommand())) {
			this.logoInterface.setForGroundColorDisplay(Color.BLUE);
		} else if ("setForColor_RED".equals(e.getActionCommand())) {
			this.logoInterface.setForGroundColorDisplay(Color.RED);
		} else if ("OtherColorFore".equals(e.getActionCommand())) {
			makeColorFrameFore();
		} else if ("OtherColorBack".equals(e.getActionCommand())) {
			makeColorFrameBack();
		}

	}

	public void stateChanged(ChangeEvent e) {

		if (color != null) {
			Color newColor = color.getColor();
			this.logoInterface.setForGroundColorDisplay(newColor);
		} else {
			Color newColor2 = colorBack.getColor();
			this.logoInterface.setBackGroundColorDisplay(newColor2);
		}
	}

}
