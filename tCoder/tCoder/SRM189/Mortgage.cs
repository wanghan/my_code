using System;
using System.Collections.Generic;
using System.Text;


class Mortgage
{
    public int monthlyPayment(int loan, int interest, int term)
    {
        int low = 0;
        int high = 2000000000;
        int n = term * 12;
        double rate = 1.0 * interest / 12 / 1000;

        while (true)
        {
            int mid = low + (high - low) / 2;
            
            double temp = loan;
            bool isOk = false;
            for (int i = 0; i < n; ++i)
            {
                temp -= mid;
                if (temp <= 0)
                {
                    isOk = true;
                    break;
                }
                temp += Math.Ceiling(temp * rate);
            }

            if (isOk)
            {
                high = mid;
            }
            else
            {
                low = mid + 1;
            }
            if (mid == low)
            {
                return low;
            }
        }
        
    }
}

