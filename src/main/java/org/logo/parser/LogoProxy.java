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

package org.logo.parser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.logo.viewer.Spool;
import org.logo.viewer.logo.Statement;

/**
 * 
 * 
 * @author Marco Speranza
 * @version $Id: ControlFlowProxy.java 000 2009-12-01 00:00:00Z marco.speranza79
 *          $
 */
public class LogoProxy implements InvocationHandler {

	private Object obj;

	public static Statement newInstance(Object obj) {
		Statement res = (Statement) java.lang.reflect.Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
				new Class[] { Statement.class }, new LogoProxy(obj));
		return res;
	}

	/**
     * 
     */
	public LogoProxy(Object obj) {
		this.obj = obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		Object result = null;
		try {
			if ("implementazione".equalsIgnoreCase(m.getName())) {
				if (isProgramIsRunnig()) {
					result = m.invoke(obj, args);
				}
			} else {
				result = m.invoke(obj, args);
			}
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: "
					+ e.getMessage());
		} finally {
		}
		return result;
	}

	/**
	 * @return
	 */
	private boolean isProgramIsRunnig() {
		return Spool.isRunning;
	}

}
