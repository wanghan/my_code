using System;
using System.Collections.Generic;
using System.Text;


class ProbabilityTree
{
    public int[] getOdds(string[] tree, int lowerBound, int upperBound)
    {
        int n = tree.Length;
        treeNode[] nodes = new treeNode[n];
        bool[] asked = new bool[n];
        for (int i = 0; i < n; ++i)
        {
            nodes[i] = new treeNode();
            nodes[i].Index = i;
            asked[i] = false;
        }
        asked[0] = true;
        nodes[0].ParentIndex = -1;
        nodes[0].Pro = int.Parse(tree[0]) / 100.0;
        nodes[0].ProNo = 1 - nodes[0].Pro;

        for (int i = 1; i < n; ++i)
        {
            string s = tree[i];
            string[] ss = s.Split(new char[] { ' ' });
            int a = int.Parse(ss[0]);
            int b = int.Parse(ss[1]);
            int c = int.Parse(ss[2]);

            nodes[i].ParentIndex = a;
            nodes[i].ProDe = b / 100.0;
            nodes[i].ProNoDe = c / 100.0;
        }

        for (int i = 1; i < n; ++i)
        {
            if (!asked[i])
            {
                find(nodes, asked, i);
            }
        }

        List<int> result = new List<int>();
        for (int i = 0; i < n; ++i)
        {
            if (lowerBound <= nodes[i].Pro*100 && nodes[i].Pro*100 <= upperBound)
            {
                result.Add(i);
            }
        }
        return result.ToArray();
       
    }
    private void find(treeNode[] nodes,bool[] asked, int cur)
    {
        int pa = nodes[cur].ParentIndex;
        if (asked[pa])
        {
            
        }
        else
        {
            find(nodes, asked, pa);
        }
        nodes[cur].Pro = nodes[cur].ProDe * nodes[pa].Pro + nodes[cur].ProNoDe * nodes[pa].ProNo;
        nodes[cur].ProNo = 1 - nodes[cur].Pro;
        asked[cur] = true;
    }
}

class treeNode
{
    public int Index;
    public int ParentIndex;
    public double Pro;
    public double ProNo;
    public double ProDe;
    public double ProNoDe;
}