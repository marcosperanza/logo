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
 * Procedura terminta
 * 
 * @author Marco Speranza
 * @version 0.1
 */
public class ProceduraTerminataException extends java.lang.Exception {

	/**
	 * Creates a new instance of <code>ProceduraTerminataException</code>
	 * without detail message.
	 */
	public ProceduraTerminataException() {
	}

	/**
	 * Constructs an instance of <code>ProceduraTerminataException</code> with
	 * the specified detail message.
	 * 
	 * @param msg
	 *            the detail message.
	 */
	public ProceduraTerminataException(String msg) {
		super(msg);
	}
}
