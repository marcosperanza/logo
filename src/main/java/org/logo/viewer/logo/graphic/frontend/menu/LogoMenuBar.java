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

import javax.swing.JMenuBar;

import org.logo.viewer.logo.graphic.frontend.LogoInterface;

/**
 * Una <b>Logo Menu Bar</b>
 * 
 * @author Marco Speranza
 * @version 0.2
 */
public class LogoMenuBar extends JMenuBar {

	/**
	 * Associazione con logoInterface
	 */
	private LogoInterface logoInterface;

	/**
	 * Composizione con logo menu File
	 */
	private LogoMenu_File logoMenuFile;

	/**
	 * Composizione con logo menu Preference
	 */
	private LogoMenu_Pref logoMenuPref;

	/**
	 * Creates a new instance of InterfaceLogoMenuBar
	 * 
	 * @param logoInterface
	 *            Logo Interface istance
	 */
	public LogoMenuBar(LogoInterface logoInterface) {
		this.logoInterface = logoInterface;
		logoMenuFile = new LogoMenu_File(logoInterface);
		add(logoMenuFile);

		logoMenuPref = new LogoMenu_Pref(logoInterface);
		add(logoMenuPref);

		add(new LogoMenu_About(logoInterface));

	}

	/**
	 * Setta il comportamento della finestra quando si sta disegnando
	 * 
	 * @param value
	 *            true se si sta disegnando
	 */
	public void painting(boolean value) {
		if (value) {
			this.logoMenuFile.setEnabled(false);
			this.logoMenuPref.setEnabled(false);
		} else {
			this.logoMenuFile.setEnabled(true);
			this.logoMenuPref.setEnabled(true);
		}

	}
}
