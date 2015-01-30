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

package org.logo.viewer;

import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Marco Speranza
 */
public class Spool<T> extends Observable {

	private static Spool spool = null;
	public static boolean isRunning = true;

    private BlockingQueue<T> queue = new ArrayBlockingQueue<T>(50);

	private Spool() {
	}

	public static synchronized  <T> Spool<T> getSpool() {
		if (spool == null) {
			spool = new Spool<T>();
		}
		return spool;
	}

	public void push(T obj) {
        queue.add(obj);
	}

    public BlockingQueue<T> getQueue() {
        return queue;
    }

	public void setRunning(boolean b) {
		isRunning = b;
	}

    public boolean isRunning() {
        return isRunning;
    }


}
