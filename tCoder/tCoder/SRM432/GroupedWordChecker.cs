using System;
using System.Collections.Generic;
using System.Text;


class GroupedWordChecker
{
    public int howMany(String[] words)
    {
        int result = 0;
        foreach (String word in words)
        {
            if (checkGrouped(word))
                result++;
        }

        return result;
    }
    public bool checkGrouped(String s)
    {
        bool[] set = new bool[26];
        for (int i = 0; i < 26; ++i)
        {
            set[i] = false;
        }
        char cur = s[0];
        set[cur - 'a'] = true;
        for (int i = 1; i < s.Length; ++i)
        {
            if (cur == s[i])
            {
                continue;
            }
            else
            {
                if (set[s[i] - 'a'])
                    return false;
                else
                {
                    cur = s[i];
                    set[s[i] - 'a'] = true;
                }
            }
        }
        return true;
    }
}
