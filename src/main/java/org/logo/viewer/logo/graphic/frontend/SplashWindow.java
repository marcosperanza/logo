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
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * Creo uno Splash screen
 */
public class SplashWindow extends Window {

	/** Immagine */
	private Image splashIm;

	/**
	 * Istanza di splash screen
	 * 
	 * @param parent
	 *            Il frame parent
	 * @param splashIm
	 *            immagine
	 */
	SplashWindow(Frame parent, Image splashIm) {
		super(parent);
		this.splashIm = splashIm;
		setSize(400, 400);

		/* Center the window */
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle winDim = getBounds();
		setLocation((screenDim.width - winDim.width) / 2,
				(screenDim.height - winDim.height) / 2);

		setVisible(true);
		// repaint();
	}

	public void paint(Graphics g) {
		if (splashIm != null) {
			g.drawImage(splashIm, 0, 0, this);
		}
	}
}
