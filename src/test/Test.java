package test;

import net.sourceforge.interval.ia_math.IAMath;
import net.sourceforge.interval.ia_math.RealInterval;

/**
 * @author nvpanov
 *
 */
public class Test {

	public static void main(String args[]) {
		final String usage = "USAGE:\tAdd this JAR and actual version of JInterval.jar to your classpath instead of IAMath.jar.";
		System.out.println("This is an Adapter from \n\tJInterval (http://www.jinterval.kenai.com)\n\t" +
				"to IAMath (http://interval.sourceforge.net/interval/index.html)");
		System.out.println(usage);
		System.out.println("\nThe project is hosted on GitHub:" +
				"\n\thttp://github.com/nvpanov/IAMath2Jinterval");
		System.out.println("Distributed under GPL license.");
		System.out.println("(c) Nikita Panov");
		
		// TODO: Add real tests instead of this
		RealInterval i = new RealInterval(-1, 1);
		RealInterval r = IAMath.add(i, 2);
		RealInterval i1 = new RealInterval(1, 3);
		if (!r.equals(i1)) {
			System.out.println("\nSanity check FAILD! Please do not use this version.");
		}		
	}

}
