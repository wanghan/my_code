using System;
using System.Collections.Generic;
using System.Collections;
using System.Collections.Specialized;
using System.Text;

class SandwichBar
{
    public int whichOrder(String[] available, String[] orders)
    {
        Hashtable set = new Hashtable();
        int k = 0;
        foreach (String s in available)
        {
            if(!set.ContainsKey(s))
            set.Add(s, k++);
        }
        for (int i = 0; i < orders.Length; ++i)
        {
            string[] ins = orders[i].Split(new char[] { ' ' });
            bool isOk = true;
            foreach (String ss in ins)
            {
                if (!set.ContainsKey(ss))
                {
                    isOk = false;
                    break;
                }

            }
            if (isOk)
            {
                return i;
            }
        }
        return -1;
    }
}
