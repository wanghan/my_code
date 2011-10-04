using System;
using System.Collections.Generic;
using System.Text;


class SortEstimate
{
    public double howMany(int c, int time)
    {
        double low = 0;
        double high = 2000000000;
        double result = 1.0 * time / c * Math.Log(2);

        while (true)
        {
            if ((high - low )/low<= 0.0000000001)
            {
                return high;
            }
            double mid = low + (high - low) / 2;
            double temp = mid * Math.Log(mid);
            if (temp <= result)
            {
                low = mid;
            }
            else
            {
                high = mid;
            }
        }
    }
}
