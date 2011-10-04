using System;
using System.Collections.Generic;
using System.Text;


class LampsGrid
{
    public int mostLit(String[] initial, int K)
    {
        bool[] flag = new bool[initial.Length];
        for (int i = 0; i < initial.Length; ++i)
        {
            flag[i] = false;
        }

        int max = 0;

        for (int i = 0; i < initial.Length; ++i)
        {
            if (!flag[i])
            {
                int count = 0;
                for (int j = 0; j < initial[i].Length; ++j)
                {
                    if (initial[i][j] == '0')
                        count++;
                }
                if (K >= count && (K - count) % 2 == 0)
                {
                    int temp = 0;
                    for (int k = 0; k < initial.Length; ++k)
                    {
                        if (initial[k].Equals(initial[i]))
                        {
                            temp++;
                            flag[k] = true;
                        }
                    }
                    if (temp > max)
                        max = temp;
                }
                else
                {
                    for (int k = 0; k < initial.Length; ++k)
                    {
                        if (initial[k].Equals(initial[i]))
                        {
                            flag[k] = true;
                        }
                    }
                }
            }
        }

        return max;
    }
}
