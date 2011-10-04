using System;
using System.Collections.Generic;
using System.Text;


class LuckyRemainder
{
    public int getLuckyRemainder(string X)
    {
        int length = X.Length;


        int result = 0;
        for (int i = 0; i < length; ++i)
        {
            int c = X[i] - '0';
            for (int j = 0; j <= length - 1-i ; ++j)
            {
                result += (c * cmn(length-1-i,j)*get2multi(i)) % 9;
            }
            result = result % 9;   
        }
        return result % 9;
    }

    private int get2multi(int m)
    {
        int lenMod9 = 1;

        for (int i = 0; i < m; ++i)
        {
            lenMod9 = (lenMod9 * 2) % 9;
        }
        return lenMod9;
    }
    private int get10multi(int m)
    {
        int lenMod9 = 1;

        for (int i = 0; i < m; ++i)
        {
            lenMod9 = (lenMod9 * 10) % 9;
        }
        return lenMod9;
    }

    private int cmn(int m, int n)
    {
        if(n==0||n==m){
            return 1;
        }
        int result = 1;
        bool done = false;
        for (int i = m; i >= m - n + 1; --i)
        {

            
                result *= (i  % 9);
            
            result = result % 9;   
        }

        return result;
    }
}

