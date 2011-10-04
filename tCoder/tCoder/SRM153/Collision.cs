using System;
using System.Collections.Generic;
using System.Text;


class Collision
{
    public double probability(int[] assignments, int ids)
    {
        double result1 = 1;
        double result2 = 1;
        int sum = 0;
        for (int i = 0; i < assignments.Length; ++i)
        {
            sum += assignments[i];
        }
        if (sum > ids)
        {
            return 0;
        }
        for (int i = 0; i < sum; ++i)
        {
            result1 *= 1.0 * (ids - i) / ids;
            
        }
        result2 = result1;
        for (int i = 0; i < assignments.Length; ++i)
        {
            for (int j = 0; j < assignments[i]; ++j)
            {
                result2 /=( 1.0*(ids - j)/ids);
            }
        }
        return Math.Abs(result1 - result2);
    }
}

