using System;
using System.Collections.Generic;
using System.Text;


    class ChessMetric
    {

        private int[,] move = new int[,] {{-1,0},{1,0},{0,1},{0,-1},
        {-1,-1},{-1,1},{1,-1},{1,1},
        {-2,1},{-2,-1},{2,1},{2,-1},
        {-1,2},{-1,-2},{1,2},{1,-2}};

        public long howMany(int size, int[] start, int[] end, int numMoves)
        {
            long[, ,] dp = new long[numMoves+1,size, size];
            dp[0, start[0], start[1]] = 1;

            for (int i = 1; i <= numMoves; ++i)
            {
                for (int j = 0; j < size; ++j)
                {
                    for (int k = 0; k < size; ++k)
                    {
                        long sum = 0;
                        for (int x = 0; x < move.Length / 2; ++x)
                        {
                            int curx = j + move[x, 0];
                            int cury = k + move[x, 1];
                            if (curx >= 0 && curx < size && cury >= 0 && cury < size)
                            {
                                sum += dp[i - 1, curx, cury];
                            }
                        }

                        dp[i, j, k] = sum;
                    }
                }
            }


            return dp[numMoves, end[0], end[1]];
        }
    }

