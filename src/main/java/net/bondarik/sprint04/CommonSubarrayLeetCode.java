package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CommonSubarrayLeetCode {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length1 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
        int[] line1 = new int[length1];
        for (int i = 0; i < length1; i++) {
            line1[i] = Integer.parseInt(tokenizer1.nextToken());
        }

        int length2 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
        int[] line2 = new int[length2];
        for (int i = 0; i < length2; i++) {
            line2[i] = Integer.parseInt(tokenizer2.nextToken());
        }

        int n = line1.length;
        int m = line2.length;
        int ans = 0;
        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++){

                if(line1[i-1] == line2[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans=Math.max (ans,dp[i][j] );
                }
                else dp[i][j]=0;
            }
        }

        System.out.println(ans);;
    }
}
