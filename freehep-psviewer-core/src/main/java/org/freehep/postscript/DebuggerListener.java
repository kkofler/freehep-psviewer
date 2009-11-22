// Copyright 2004, FreeHEP.
package org.freehep.postscript;

import java.io.IOException;
import java.util.EventListener;

public interface DebuggerListener extends EventListener {
	/**
	 * Resets
	 */
	void reset() throws IOException;

	/**
	 * Executes one step
	 * 
	 * @return true if more steps can be taken
	 */
	boolean step() throws IOException;

	/**
	 * Proceeds to end
	 */
	void go() throws IOException;
}