using System;
using System.Collections.Generic;
using System.Text;


class BenfordsLaw
{
    public int questionableDigit(int[] transactions, int threshold)
    {
        int[] a = new int[10];
        double[] b = new double[10];
        for (int i = 0; i < transactions.Length; ++i)
        {
            string s = transactions[i].ToString();
            a[s[0] - '0']++;
        }

        for (int i = 1; i < 10; ++i)
        {
            b[i] = transactions.Length * Math.Log10(1 + 1.0 / i);
            if (a[i] / b[i] >= threshold || b[i] / a[i] >= threshold)
            {
                return i;
            }
        }
        return -1;

    }
}

