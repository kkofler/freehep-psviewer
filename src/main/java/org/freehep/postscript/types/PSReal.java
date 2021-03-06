// Copyright 2001-2004, FreeHEP.
package org.freehep.postscript.types;

import org.freehep.postscript.errors.RangeException;

/**
 * Objects for PostScript Processor, as defined in 3.3 Data Types and Objects
 * 
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/postscript/PSReal.java 17245790f2a9
 *          2006/09/12 21:44:14 duns $
 */
public class PSReal extends PSNumber {
	private double value;

	public PSReal(double v) {
		super("real");
		value = v;
	}

	@Override
	public String getType() {
		return "realtype";
	}

	public double getValue() {
		return value;
	}

	@Override
	public double getDouble() {
		return value;
	}

	@Override
	public int getInt() throws RangeException {
		if ((value < Integer.MIN_VALUE) || (value > Integer.MAX_VALUE)) {
			throw new RangeException();
		}
		return (int) value;
	}

	public int compareTo(Object o) {
		double d1 = getValue();
		double d2 = ((PSNumber) o).getDouble();
		return (d1 > d2) ? +1 : (d1 < d2) ? -1 : 0;
	}

	@Override
	public int hashCode() {
		long bits = Double.doubleToLongBits(value);
		return (int) (bits ^ (bits >>> 32));
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PSReal) {
			return (value == ((PSReal) o).getValue());
		}
		return false;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new PSReal(value);
	}

	@Override
	public String cvs() {
		return toString();
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
