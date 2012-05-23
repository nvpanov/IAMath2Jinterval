package net.sourceforge.interval.ia_math; // iamath2jinterval;
import static java.lang.Math.max;
import static java.lang.Math.min;

import com.kenai.jinterval.fast_nearest_round.DoubleInterval;


public class RealInterval {
	protected DoubleInterval i;
	
	public RealInterval() {
		i = DoubleInterval.valueOf(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	public RealInterval(double value) {
		i = new DoubleInterval(value);
	}
	public RealInterval(int value) {
		i = new DoubleInterval(value);
	}
	public RealInterval(int lo, int hi) {
		i = DoubleInterval.valueOf(lo, hi);
	}
	public RealInterval(double lo, double hi) {
		i = DoubleInterval.valueOf(lo, hi);
	}
	protected RealInterval(DoubleInterval di) {
		i = di.clone();
	}
	public RealInterval clone() {
		RealInterval clon = new RealInterval(i); 
		return clon;
	}
	public double lo() {
		return i.doubleInf();
	}
	public double hi() {
		return i.doubleSup();
	}
	///////////////////// math //////////////////////
	// ^
	public static RealInterval pow(RealInterval iVal, int intVal) {
		return power(iVal, (double)intVal);
	}
	public static RealInterval power(RealInterval iVal, double dVal) {
		return power(iVal, new RealInterval(dVal));
	}
	public static RealInterval power(RealInterval a, RealInterval b) {
		return new RealInterval(a.i.pow(b.i));
	}
	// not standart
	public static RealInterval intPow(RealInterval x, RealInterval y) { 
		/*
		 * This pow function treats y not as usual interval but as a set of
		 * integers. For example, if y = [-2.8, 1.1] it will assume that y
		 * contains only -2, -1, 0, 1. So result = x^-2 union x^-1 union x^0
		 * union x^1.
		 */
		RealInterval res = power(x, y.lo());
		for (int i = (int) y.lo() + 1; i <= (int) y.hi(); i++) {
			RealInterval tmp = power(x, i);
			res = new RealInterval(min(res.lo(), tmp.lo()), max(res.hi(),
					tmp.hi()));
		}
		return res;
	}

	// +
	public static RealInterval add(RealInterval iVal, double dVal) {
		return add(iVal, new RealInterval(dVal));
	}
	public static RealInterval add(RealInterval a, RealInterval b) {
		return new RealInterval( a.i.add(b.i) );
	}
	// -
	public static RealInterval sub(RealInterval a, RealInterval b) {
		return new RealInterval(a.i.subtract(b.i));
	}
	public static RealInterval sub(RealInterval a, int b) {
		return sub(a, new RealInterval(b));
	}
	// *
	public static RealInterval mul(int i, RealInterval interval) {
		return mul(interval, (double)i);
	}
	public static RealInterval mul(RealInterval interval, int i) {
		return mul(i, interval);
	}
	public static RealInterval mul(RealInterval interval, double d) {
		return mul(new RealInterval(d), interval);
	}
	public static RealInterval mul(RealInterval a, RealInterval b) {
		return new RealInterval(a.i.multiply(b.i));
	}
	// cos/sin/...
	public static RealInterval cos(RealInterval x) {
		return new RealInterval(x.i.cos());
	}
	public static RealInterval sin(RealInterval x) {
		return new RealInterval(x.i.sin());
	}
	
	@Override
	public String toString() {
		return i.toString();
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof RealInterval && i.equals(((RealInterval) o).i);
	}

	@Override
	public int hashCode() {
		return i.hashCode();
	}

	// not standard
	public double wid() {
		return i.doubleWid();
	}
	public static double wid(RealInterval interval) {
		return interval.i.doubleWid();
	}
	public boolean contains(double d) {
		return d >= lo() && d <= hi();
	}
	public void intersect(RealInterval x) {
		i = i.intersect(x.i);
	}
	public boolean isEmpty() {
		return i.isEmpty();
	}
	public static RealInterval negate(RealInterval x) {
		return new RealInterval(-x.hi(), -x.lo() );
	}

	
}
