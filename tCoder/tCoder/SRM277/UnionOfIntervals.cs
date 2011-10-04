using System;
using System.Collections.Generic;
using System.Text;


class UnionOfIntervals
{
    public int nthElement(int[] lowerBound, int[] upperBound, int n)
    {
        int lo = int.MinValue, hi = int.MaxValue;
        while (lo < hi)
        {
            int X = (int)(((long)lo + hi + 1) / 2);
            long count = 0;
            for (int i = 0; i < lowerBound.Length; ++i)
            {
                if (X >= lowerBound[i] && X <= upperBound[i])
                {
                    // part of interval i is less than X
                    count += (long)X - lowerBound[i];
                }
                if (X >= lowerBound[i] && X > upperBound[i])
                {
                    // all numbers in interval i are less than X
                    count += (long)upperBound[i] - lowerBound[i] + 1;
                }
            }

            if (count <= n) lo = X;
            else hi = X - 1;
        }

        return lo;
    }
}

