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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.logo.viewer.LogoManager;
import org.logo.viewer.Spool;
import org.logo.viewer.logo.graphic.frontend.LogoInterface;

/**
 * @author Marco Speranza
 * @version 0.1
 */
public class LogoViewer {
	private LogoInterface f;
	private LogoManager logoManager;
    private final BlockingQueue<Action> queue;
    private final ExecutorService executorService;

    public LogoViewer(LogoManager logoManager) {
		this.createUi(logoManager);
        final Spool<Action> spool = Spool.getSpool();
        queue = spool.getQueue();

        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (spool.isRunning()) {
                    try {
                        update();
                    } catch (InterruptedException ignore) {

                    }
                }
            }
        });
	}

	public void update() throws InterruptedException {
        Action action = queue.poll(1, TimeUnit.SECONDS);

        if (f.isIdle()) {
			f.updateFigure(action);
		}
	}

	private void createUi(LogoManager logoManager) {
		this.f = new LogoInterface(this);
		this.logoManager = logoManager;
        executorService.submit(f);
	}

	/**
	 * Start del programma
	 * 
	 * @throws Exception
	 */
	public void startProgram() throws Exception {
		logoManager.start();
	}

	/**
	 * Stop del programma
	 */
	public void stopProgram() {
		logoManager.stop();
	}

	/**
	 * Start del programma
	 */
	public void endProgram() {
		f.endProgram();
	}

	/**
	 * @param program
	 * @throws Exception
	 */
	public void loadProgram(String program) throws Exception {
		logoManager.loadProgram(program);
	}

}
