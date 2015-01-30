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

package org.logo.viewer.logo.graphic;

import org.logo.viewer.logo.graphic.actions.AzioneGrafica;
import org.logo.viewer.logo.graphic.actions.PuntoFinale;

public class GraphicalPosition implements Action {
	private float x;
	private float y;

	public GraphicalPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public AzioneGrafica esegui(double initialPointX, double initialPointY,
			double angulation) {

		return new PuntoFinale(initialPointX, initialPointY, x, y);
	}

	@Override
	public String toString() {
		return "GraphicalPosition [x=" + x + ", y=" + y + "]";
	}
}
