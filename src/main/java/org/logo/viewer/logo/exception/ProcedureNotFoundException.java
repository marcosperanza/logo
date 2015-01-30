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

package org.logo.viewer.logo.exception;

/**
 * Procedura non trovata
 * 
 * @author Marco Speranza
 * @version 0.1
 */
public class ProcedureNotFoundException extends Exception {

	/** Creates a new instance of ProcedureNotFoundException */
	public ProcedureNotFoundException() {
	}

	/**
	 * Constructs an instance of <code>ProcedureNotFoundException</code> with
	 * the specified detail message.
	 * 
	 * @param msg
	 *            the detail message.
	 */
	public ProcedureNotFoundException(String msg) {
		super(msg);
	}

}
