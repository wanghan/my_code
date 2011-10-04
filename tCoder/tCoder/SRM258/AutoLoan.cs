using System;
using System.Collections.Generic;
using System.Text;


class AutoLoan
{
    public double interestRate(double price, double monthlyPayment, int loanTerm)
    {
        double high = 1;
        double low = 0;

        while (true)
        {
            if (high - low <= 0.0000000000001)
            {
                return low * 12 * 100;
            }
            double mid = (high - low) / 2 + low;
            double temp = price;
            for (int i = 0; i < loanTerm; ++i)
            {
                temp += (mid * temp - monthlyPayment);
            }
            if (temp <= 0)
            {
                low = mid;
            }
            else
            {
                high = mid;
            }
        }
    }
}

