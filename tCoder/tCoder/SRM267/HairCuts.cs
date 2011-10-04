using System;
using System.Collections.Generic;
using System.Text;


class HairCuts
{
    public double maxCut(String[] enter, String lastExit)
    {
        int[] min = new int[enter.Length];
        for (int i = 0; i < enter.Length; ++i)
        {
            int hh = int.Parse(enter[i].Substring(0, 2));
            int mm = int.Parse(enter[i].Substring(3, 2));
            if (hh >= 9)
            {
                min[i] = (hh - 9) * 60 + mm;
            }
            else
            {
                min[i] = (hh + 3) * 60 + mm;
            }
        }
        Array.Sort(min);
        int last = 0;
        int hh1 = int.Parse(lastExit.Substring(0, 2));
        int mm1 = int.Parse(lastExit.Substring(3, 2));
        if (hh1 >= 9)
        {
            last = (hh1 - 9) * 60 + mm1;
        }
        else
        {
            last = (hh1 +3) * 60 + mm1;
        }
        double temp = min[0] + 5;
        for (int i = 1; i < min.Length; ++i)
        {
            if (temp > min[i])
            {
                temp += 5;
            }
            else
            {
                temp = min[i] + 5;
            }
        }
        if (temp > last)
        {
            return -1;
        }

        double low = 5;
        double high = 60 * 12;
        while (true)
        {
            double mid = low + (high - low) / 2;
            temp = min[0] + mid;
            for (int i = 1; i < min.Length; ++i)
            {
                if (temp > min[i])
                {
                    temp += mid;
                }
                else
                {
                    temp = min[i] + mid;
                }
            }
            if (temp > last)
            {
                high = mid;
            }
            else
            {
                low = mid;
            }
            if ((high - low) / low <= 0.00000000001)
            {
                return low;
            }
        }
    }
}

