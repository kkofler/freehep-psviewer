// Copyright 2001-2004, FreeHEP.
package org.freehep.postscript.types;

import org.freehep.postscript.errors.Undefined;
import org.freehep.postscript.stacks.OperandStack;

/**
 * Objects for PostScript Processor, as defined in 3.3 Data Types and Objects
 * 
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/postscript/PSName.java 17245790f2a9
 *          2006/09/12 21:44:14 duns $
 */
public class PSName extends PSSimple {
	private String value;

	public PSName(String name) {
		this(name, false);
	}

	public PSName(String name, boolean literal) {
		super("name", literal);
		value = name;
	}

	@Override
	public boolean execute(OperandStack os) {
		if (isLiteral()) {
			os.push(this);
			return true;
		}

		// ignore names starting with ESC
		if (value.charAt(0) == 0x1B) {
			return true;
		}

		PSObject obj = os.dictStack().lookup(this);
		if (obj == null) {
			error(os, new Undefined());
		}

		os.execStack().pop();
		os.execStack().push(obj);
		return false;
	}

	public int size() {
		return value.length();
	}

	@Override
	public String getType() {
		return "nametype";
	}

	public String getValue() {
		return value;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new PSName(value);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PSName) {
			return getValue().equals(((PSName) o).getValue());
		} else if (o instanceof PSString) {
			return getValue().equals(((PSString) o).getValue());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String cvs() {
		return value;
	}

	@Override
	public String toString() {
		return ((isLiteral()) ? "/" : "") + value;
	}
}
