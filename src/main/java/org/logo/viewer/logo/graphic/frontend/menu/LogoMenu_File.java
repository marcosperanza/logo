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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.logo.viewer.logo.graphic.frontend.LogoInterface;

/**
 * Una <b>LogoMenu_About</b>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class LogoMenu_File extends JMenu implements ActionListener {

	/**
	 * Associazione con logo Intrerface
	 */
	private LogoInterface logoInterface;
	private String startDir = ".";

	/**
	 * Creates a new instance of LogoMenu
	 * 
	 * @param logoInterface
	 *            Associazione
	 */
	public LogoMenu_File(LogoInterface logoInterface) {
		super("File");
		this.logoInterface = logoInterface;
		setMnemonic(KeyEvent.VK_F);

		// JMenu subMenu = makeEntryMenu_SubMenu();
		JMenuItem subMenu = makeEntryMenu_SubMenuOpenFile();
		JMenuItem menuItem = makeEntryMenu_Exit();

		add(subMenu);
		addSeparator();
		add(menuItem);
	}

	/**
	 * Crea il menu Item
	 * 
	 * @return JMenuItem
	 */
	private JMenuItem makeEntryMenu_Exit() {

		JMenuItem menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.ALT_MASK));
		menuItemExit.setActionCommand("Exit");
		menuItemExit.addActionListener(this);
		return menuItemExit;

	}

	/**
	 * Crea il menu Item
	 * 
	 * @return JMenu
	 */
	private JMenuItem makeEntryMenu_SubMenuOpenFile() {

		JMenuItem subMenu = new JMenuItem("Load File", KeyEvent.VK_O);
		subMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.ALT_MASK));

		subMenu.setActionCommand("Load");
		subMenu.addActionListener(this);

		return subMenu;

	}

	/**
	 * Crea il menu Item
	 * 
	 * @return JMenu
	 * 
	 *         private JMenu makeEntryMenu_SubMenu() {
	 * 
	 *         JMenu subMenu = new JMenu("Choose a design");
	 * 
	 * 
	 *         ButtonGroup group = new ButtonGroup();
	 * 
	 *         JRadioButtonMenuItem rbMenuItem = new
	 *         JRadioButtonMenuItem("Disegno 1"); rbMenuItem.setSelected(true);
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("1");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 * 
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 2");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("2");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 3");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("3");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 4");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("4");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 5");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("5");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 * 
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 6");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("6");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 7");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("7");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 8");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("8");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 9");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("9");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno 10");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("10");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 * 
	 * 
	 *         rbMenuItem = new JRadioButtonMenuItem("Disegno Ottagono");
	 * 
	 *         group.add(rbMenuItem); subMenu.add(rbMenuItem);
	 *         rbMenuItem.setActionCommand("11");
	 *         rbMenuItem.addActionListener(this);
	 * 
	 *         return subMenu;
	 * 
	 * 
	 * 
	 * 
	 *         }
	 */

	public void actionPerformed(ActionEvent e) {
		if ("Exit".equals(e.getActionCommand())) {
			System.exit(0);
		} else if ("Load".equals(e.getActionCommand())) {
			JFileChooser chooser = new JFileChooser(new File(startDir));
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					startDir = chooser.getSelectedFile().getParent();
					logoInterface.setTitle(chooser.getSelectedFile().getName());
					this.logoInterface.loadProgram(chooser.getSelectedFile()
							.getAbsolutePath());
				} catch (Throwable e2) {
					JOptionPane.showMessageDialog(logoInterface, e2
							.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

}
