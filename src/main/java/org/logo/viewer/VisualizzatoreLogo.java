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

/**Rappresenta il Begin Program
 *
 * @author  Marco Speranza
 * @version 0.1
 */

package org.logo.viewer;

/** Visualizzatore LOGO */
public class VisualizzatoreLogo {

	/** Aggregazione con la classe logoManager */
	LogoManager logoManager;

	/** Creates a new instance of Main */
	public VisualizzatoreLogo() {
		logoManager = new LogoManager();
	}

	public static void main(String[] args) {
		new VisualizzatoreLogo();
	}
	
	
	

}
