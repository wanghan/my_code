using System;
using System.Collections.Generic;
using System.Text;


class GeneticCrossover
{
    public double cross(string p1a, string p1b, string p2a, string p2b, int[] dom, int[] rec, int[] dependencies)
    {
        double[,] prob = new double[p1a.Length, 2];
        double[,] result = new double[p1a.Length, 2];
        bool[] asked = new bool[p1a.Length];
        for (int i = 0; i < p1a.Length; ++i)
        {
            asked[i] = false;
            char a1 = p1a[i];
            char a2 = p1b[i];

            char b1 = p2a[i];
            char b2 = p2b[i];
            int low1 = 0, low2 = 0;
            if (Char.IsLower(a1))
            {
                low1++;
            }
            if (Char.IsLower(a2))
            {
                low1++;
            }
            if (Char.IsLower(b1))
            {
                low2++;
            }
            if (Char.IsLower(b2))
            {
                low2++;
            }
            if (low1 == 2 && low2 == 2)
            {
                prob[i, 0] = 1;
                prob[i, 1] = 0;
            }
            else if ((low1 == 2 && low2 == 1) || (low1 == 1 && low2 == 2))
            {
                prob[i, 0] = 0.5;
                prob[i, 1] = 0.5;
            }
            else if (low1 == 1 && low2 == 1)
            {
                prob[i, 0] = 0.25;
                prob[i, 1] = 0.75;
            }
            else
            {
                prob[i, 0] = 0;
                prob[i, 1] = 1;
            }
            if (dependencies[i] == -1)
            {
                result[i, 0] = prob[i, 0];
                result[i, 1] = prob[i, 1];
                asked[i] = true;
            }
        }
        for (int i = 0; i < p1a.Length; ++i)
        {
            if (!asked[i])
            {
                if (dependencies[i] == -1)
                {
                    result[i, 0] = prob[i, 0];
                    result[i, 1] = prob[i, 1];
                    asked[i] = true;
                }
                else
                {
                    find(prob, result, asked, dependencies, i);

                }
            }
        }
        double re = 0;
        for (int i = 0; i < p1a.Length; ++i)
        {
            re += result[i, 0] * rec[i] + result[i, 1] * dom[i];
        }

        return re;
    }
    private void find(double[,] prob, double[,] result, bool[] asked ,int[] dependencies,int cur)
    {
        int de = dependencies[cur];
        if (!asked[de])
        {
            find(prob, result, asked, dependencies, de);
            result[cur, 0] = result[de, 0];
            result[cur, 1] = result[de, 1] * prob[cur, 1];
            result[cur, 0] += result[de, 1] * prob[cur, 0];
            asked[cur] = true;
        }
        else
        {
            result[cur, 0] = result[de, 0];
            result[cur, 1] = result[de, 1] * prob[cur, 1];
            result[cur, 0] += result[de, 1] * prob[cur, 0];
            asked[cur] = true;
        }
    }
}
