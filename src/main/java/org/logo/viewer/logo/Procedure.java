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

package org.logo.viewer.logo;

import java.util.List;

import org.logo.viewer.exp.Exp;
import org.logo.viewer.logo.exception.ParameterException;
import org.logo.viewer.logo.exception.ProcedureNotFoundException;
import org.logo.viewer.logo.exception.VariableNotFoundException;

public interface Procedure {

	public void setName(String name);

	public String getName();

	public List<Statement> getBody();

	public void invoke(List<Exp> realParams, ScopedStatement invoker)
			throws ProcedureNotFoundException, ParameterException,
			VariableNotFoundException;

	public void setBody(List<Statement> body);

}