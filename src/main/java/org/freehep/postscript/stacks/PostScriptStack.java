// Copyright 2001-2009, FreeHEP.
package org.freehep.postscript.stacks;

import java.util.Stack;
import java.util.logging.Logger;

import org.freehep.postscript.types.PSArray;
import org.freehep.postscript.types.PSMark;
import org.freehep.postscript.types.PSName;
import org.freehep.postscript.types.PSObject;

/**
 * PostScript Abstract Stack for PostScript Processor
 * 
 * @author Mark Donszelmann
 */
public class PostScriptStack extends Stack<Object> {
	protected Logger log = Logger.getLogger("org.freehep.postscript");

	protected int marker = -1;

	@Override
	public Object push(Object o) {
		if (o == null) {
			throw new IllegalArgumentException(
					"PostScript Stack cannot contain 'null'");
		}

		if (!(o instanceof PSObject)) {
			throw new IllegalArgumentException(
					"PostScript Stack can only contain PSObjects, not: " + o);
		}

		return super.push(o);
	}

	public Object push(String s) {
		return super.push(new PSName(s));
	}

	public PSObject peekObject() {
		return (PSObject) peek();
	}

	public PSObject peekObject(int i) {
		if (i > elementCount) {
			return null;
		}
		return (PSObject) elementData[elementCount - i - 1];
	}

	// do not remove elements, just lower the top of the stack
	@Override
	public Object pop() {
		if (elementCount == 0) {
			return null;
		}

		elementCount--;
		return elementData[elementCount];
	}

	public PSObject popObject() {
		return (PSObject) pop();
	}

	// mark and counttomark needed for making procedure objects
	public int countToMark() {
		int count = 0;
		int i = elementCount - 1;
		while (i > 0 && !(elementData[i] instanceof PSMark)) {
			i--;
			count++;
		}
		return (elementData[i] instanceof PSMark) ? count : -1;
	}

	public PSMark popMark() {
		return (PSMark) super.pop();
	}

	// sets the marker, and cleans up what is above
	public void mark() {
		marker = elementCount;
		setSize(marker);
	}

	// resets the stack to the marker level, and cleans above
	public void reset() {
		if (marker >= 0) {
			setSize(marker);
			marker = -1;
		}
	}

	public void copyInto(PSArray a) {
		for (int i = 0; i < a.size(); i++) {
			a.set(i, (PSObject) elementData[i]);
		}
	}

	public void printStack() {
		for (int i = elementCount - 1; i >= 0; i--) {
			log.info(i + ": " + elementData[i].getClass().getName()
					+ ": " + elementData[i].toString());
		}
	}
}