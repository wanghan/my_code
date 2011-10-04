using System;
using System.Collections.Generic;
using System.Text;


class FairWorkload
{
    public int getMostWork(int[] folders, int workers)
    {
        int sum = 0;
        int lm = 0;
        foreach (int a in folders)
        {

            sum += a;
            lm = Math.Max(lm, a);
        }
        int high = sum;
        if (workers == 1)
        {
            return sum;
        }
        int low = lm;
        int max = 0;

        while (true)
        {

            int mid = low + (high - low) / 2;
            if (mid == low)
            {
                break;
            }
            int temp = 0;
            int www = 1;
            for (int i = 0; i < folders.Length; ++i)
            {
                if (temp + folders[i] <= mid)
                {
                    temp += folders[i];
                }
                else
                {
                    www++;
                    temp = folders[i];
                }
            }
            if (www > workers)
            {
                low = mid+1;
            }
            else if (www < workers)
            {
                high = mid;
            }
            else
            {
                high = mid;
            }
        }
        int temp1 = 0;
        int www1 = 1;
        for (int i = 0; i < folders.Length; ++i)
        {
            if (temp1 + folders[i] <= low)
            {
                temp1 += folders[i];
            }
            else
            {
                www1++;
                temp1 = folders[i];
            }
        }
        if (www1 <= workers)
        {
            return low;
        }
        else
        {
            return high;
        }
    }
    
}
