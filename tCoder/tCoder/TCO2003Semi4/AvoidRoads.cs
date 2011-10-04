using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


class AvoidRoads
{
    public long numWays(int width, int height, string[] bad)
    {
        long[,] dp = new long[width + 1, height + 1];
        block[] blocks = new block[bad.Length];
        for (int i = 0; i < bad.Length; ++i)
        {
            blocks[i] = new block(bad[i]);
        }
        dp[0,0] = 1;

        for (int i = 1; i <= width; ++i)
        {
            if (isBad(i, 0, i - 1, 0,blocks))
            {
                dp[i, 0] = 0;
            }
            else
            {
                dp[i, 0] =  dp[i-1,0];
            }
        }
        for (int i = 1; i <= height; ++i)
        {
            if (isBad(0,i,0,i-1, blocks))
            {
                dp[0, i] = 0;
            }
            else
            {
                dp[0, i] = dp[0,i - 1];
            }
        }


        for (int i = 1; i <= width; ++i)
        {
            for (int j = 1; j <= height; ++j)
            {
                long aa=0, bb=0;
                if (!isBad(i, j, i - 1, j, blocks))
                {
                    aa = dp[i - 1, j];
                }
                if (!isBad(i, j, i , j-1, blocks))
                {
                    bb = dp[i, j-1];
                }
                dp[i, j] = aa + bb;
            }
        }
        return dp[width, height];

    }

    private bool isBad(int a,int b, int c,int d, block [] blocks)
    {
        foreach (block bb in blocks)
        {
            if (a == bb.a&&b==bb.b&&c==bb.c&&d==bb.d)
            {
                return true;
            }
            else if (a == bb.c && b == bb.d && c == bb.a && d == bb.b)
            {
                return true;
                   
            }

        }
        return false;
    }
}
class block
{
    public int a, b, c, d;

    public block(String s)
    {
        String[] splits = s.Split(new char[] { ' ' });
        a = int.Parse(splits[0]);
        b = int.Parse(splits[1]);
        c = int.Parse(splits[2]);
        d = int.Parse(splits[3]);
    }
}