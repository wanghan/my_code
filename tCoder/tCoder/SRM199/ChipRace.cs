using System;
using System.Collections.Generic;
using System.Text;


class ChipRace
{
    public double[] chances(int[] chips)
    {
        int sum = 0;
        foreach (int cc in chips)
        {
            sum += cc;
        }
        int cs = 0;
        if (sum % 5 >= 3)
        {
            cs = 1 + sum / 5;
        }
        else
        {
            cs = sum / 5;
        }

        double[] result = new double[chips.Length];
        double[,] prob = new double[chips.Length,cs];





        return result;
    }
}

