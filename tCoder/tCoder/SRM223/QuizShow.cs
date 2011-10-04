using System;
using System.Collections.Generic;
using System.Text;


class QuizShow
{
    public int wager(int[] scores, int wager1, int wager2)
    {

        int[] result = new int[4];
        result[0] = Math.Max(scores[1] + wager1, scores[2] + wager2);
        result[1] = Math.Max(scores[1] - wager1, scores[2] + wager2);
        result[2] = Math.Max(scores[1] + wager1, scores[2] - wager2);
        result[3] = Math.Max(scores[1] - wager1, scores[2] - wager2);

        int max = 0;
        int re = 0;

        for (int i = 0; i <= scores[0]; ++i)
        {
            int yes = scores[0] + i;
            int no = scores[0] - i;

            int aaa = 0;
            for (int j = 0; j < 4; ++j)
            {
                if (yes > result[j])
                {
                    aaa++;
                }
                if (no > result[j])
                {
                    aaa++;
                }

                
            }
     //       Console.WriteLine(i + " " + aaa);
            if (aaa > max)
            {
                re = i;
                max = aaa;
            }
        }

        return re;
    }
}

