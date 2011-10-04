using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
namespace tCoder.ProgramBeauty
{
    class CPUCurve
    {
        public static void showLine()
        {
            while (true)
            {
                System.DateTime begin = DateTime.Now;
                int k = 100000000;
                for (int i = 0; i < k; ++i)
                {
                    ;
                }
                System.DateTime end = DateTime.Now;
                Console.WriteLine(end.Millisecond - begin.Millisecond);
                Thread.Sleep(200);
            }
        }
    }
}
