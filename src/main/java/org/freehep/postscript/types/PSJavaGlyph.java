// Copyright 2001, FreeHEP.
package org.freehep.postscript.types;

import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

/**
 * Object is only for storage and lookup in Dictionaries and Arrays, not to be
 * executed.
 * 
 * @author Mark Donszelmann
 * @version $Id: src/main/java/org/freehep/postscript/PSJavaGlyph.java
 *          17245790f2a9 2006/09/12 21:44:14 duns $
 */
public class PSJavaGlyph extends PSGlyph {
	private GlyphVector gv;

	public PSJavaGlyph(GlyphVector gv) {
		this.gv = gv;
		GlyphMetrics gm = gv.getGlyphMetrics(0);
		setWx(gm.getAdvance());
		Rectangle2D r = gm.getBounds2D();
		setLLx(r.getMinX());
		setLLy(r.getMinY());
		setURx(r.getMaxX());
		setURy(r.getMaxY());
	}

	public GlyphVector getGlyph() {
		return gv;
	}

	// FIXME
	@Override
	public int hashCode() {
		return 0;
	}

	// FIXME
	@Override
	public boolean equals(Object o) {
		return false;
	}

	// FIXME
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(getClass()+" not implemented");
	}
}
