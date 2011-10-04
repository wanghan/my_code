using System;
using System.Collections.Generic;
using System.Text;


class Rank
{
    public double[] rank(int[] scores)
    {
        person[] ppp = new person[scores.Length];
        for (int i = 0; i < ppp.Length; ++i)
        {
            ppp[i] = new person();
            ppp[i].index = i;
            ppp[i].score = scores[i];
        }
        Array.Sort(ppp);
        int curScore = ppp[0].score;
        int sum=1;
        int count=1;
        for (int i = 1; i < ppp.Length; ++i)
        {
            if (ppp[i].score == curScore)
            {
                sum += (i + 1);
                count++;
            }
            else
            {
                for (int j = 0; j < ppp.Length; ++j)
                {
                    if (ppp[j].score == curScore)
                    {
                        ppp[j].rank = 1.0 * sum / count;
                    }
                   
                }
                curScore = ppp[i].score;
                sum = i + 1;
                count = 1;
            }
        }
        if (count > 0)
        {
            for (int j = 0; j < ppp.Length; ++j)
            {
                if (ppp[j].score == curScore)
                {
                    ppp[j].rank = 1.0 * sum / count;
                }
              
            }
        }
        double[] result = new double[ppp.Length];
        for (int i = 0; i < ppp.Length; ++i)
        {
            result[ppp[i].index] = ppp[i].rank;
        }
        return result;
    }
}
class person:IComparable<person>
{
   public int index;
   public int score;
   public double rank;


   #region IComparable<person> Members

   public int CompareTo(person other)
   {
       return other.score - this.score;
   }

   #endregion
}
