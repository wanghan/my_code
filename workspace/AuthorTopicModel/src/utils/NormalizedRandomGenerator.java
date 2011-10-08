package utils;

import java.io.Serializable;

/** This interface represent a normalized random generator for
 * scalars.
 * Normalized generator should provide null mean and unit standard
 * deviation scalars.
 * @version $Id: NormalizedRandomGenerator.java,v 1.1 2011/02/20 10:36:32 wanghan Exp $
 * @author L. Maisonobe
 */
public interface NormalizedRandomGenerator extends Serializable {

  /** Generate a random scalar with null mean and unit standard deviation.
   * <p>This method does <strong>not</strong> specify the shape of the
   * distribution, it is the implementing class that provides it. The
   * only contract here is to generate numbers with null mean and unit
   * standard deviation.</p>
   * @return a random scalar
   */
  public double nextDouble();

}
