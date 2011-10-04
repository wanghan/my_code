using System;
using System.Collections.Generic;
using System.Text;

class OneArmedBandit
{
    public double progressiveJackpot(String[] wheels, String jackpotLine, String[] payoffTable)
    {
        int[,] wheelC = new int[wheels.Length, 26];
        for (int i = 0; i < wheels.Length; ++i)
        {
            for (int j = 0; j < wheels[i].Length; ++j)
            {
                wheelC[i, wheels[i][j] - 'A']++;
            }
        }

        double probJ = 1;
        for (int i = 0; i < jackpotLine.Length; ++i)
        {
            if (jackpotLine[i] == '-')
            {
                continue;
            }
            probJ *= 1.0 * wheelC[i, jackpotLine[i] - 'A'] / wheels[i].Length;
        }
       
        double sumPayoff = 0;

        for (int k = 0; k < payoffTable.Length; ++k)
        {
            String ss = payoffTable[k].Split(new char[] { ' ' })[0];
            int pay = int.Parse(payoffTable[k].Split(new char[] { ' ' })[1]);
            double temp = 1;
            for (int i = 0; i < ss.Length; ++i)
            {
                if (ss[i] == '-')
                {
                    continue;
                }
                temp *= 1.0 * wheelC[i, ss[i] - 'A'] / wheels[i].Length ;
            }
            sumPayoff += temp * pay;
        }
        if (sumPayoff >= 1)
        {
            return 0;
        }
        if (probJ == 0)
        {
            return -1;
        }
        return (1 - sumPayoff) / probJ;
    }
}

