package org.takewo.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumericUtilsTest {

	@Test
	public void testIsInteger() {
		assertTrue(NumericUtils.isInteger("1"));
		assertTrue(NumericUtils.isInteger("1098"));
		assertTrue(NumericUtils.isInteger("-3"));
		assertFalse(NumericUtils.isInteger("1.0123"));
		assertFalse(NumericUtils.isInteger("-1.230848"));
	}

	@Test
	public void testIsReal() {
		assertTrue(NumericUtils.isReal("1"));
		assertTrue(NumericUtils.isReal("1098"));
		assertTrue(NumericUtils.isReal("-3"));
		assertTrue(NumericUtils.isReal("1.0123"));
		assertTrue(NumericUtils.isReal("-1.230848"));
	}

	@Test
	public void testIsPositiveReal() {
		assertTrue(NumericUtils.isPositiveReal("1"));
		assertTrue(NumericUtils.isPositiveReal("1098"));
		assertFalse(NumericUtils.isPositiveReal("-3"));
		assertTrue(NumericUtils.isPositiveReal("1.0123"));
		assertFalse(NumericUtils.isPositiveReal("-1.230848"));
	}

}
