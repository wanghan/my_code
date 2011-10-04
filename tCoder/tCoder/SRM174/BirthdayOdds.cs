
using System;
using System.Collections.Generic;
using System.Text;


class BirthdayOdds
{
    public int minPeople(int minOdds, int daysInYear)
    {
        for (int i = 1; ; ++i)
        {
            double temp = 1;
            double dodds = 1.0* minOdds / 100;
            for (int j = 0; j < i; ++j)
            {
                temp *= (1.0 * (daysInYear - j) / daysInYear);
            }
 //           Console.WriteLine(i + " " + temp);
            if ((1 - temp) >= dodds)
            {
                return i;
            }
        }
    }
}

