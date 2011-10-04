using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;


class Cyberline
{

    public String lastCyberword(String cyberline)
    {
        Regex r = new Regex(@"[a-zA-Z0-9\-\@]+");

        MatchCollection matchs = r.Matches(cyberline, 0);

        for (int i = matchs.Count - 1; i >= 0; --i)
        {
            if (test(matchs[i].Value))
            {
                continue;
            }
            else
            {
                return matchs[i].Value.Replace("-", "");
            }
        }
        return "";
    }

    public bool test(String s)
    {
        for (int i = 0; i < s.Length; ++i)
        {
            if (s[i] != '-')
            {
                return false;
            }
        }
        return true;
    }
}

