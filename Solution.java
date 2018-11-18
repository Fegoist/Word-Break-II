package wordBreak2;

import java.util.*;

class KMP_String_Matching
{
    ArrayList<Integer> KMPSearch(String pat, String txt)
    {
        ArrayList<Integer> resList = new ArrayList<Integer>();

        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j) == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                System.out.println("Found pattern "
                        + "at index " + (i - j));
                resList.add(i - j);

                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return resList;
    }

    void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0)
                {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}

    public class Solution
    {
        public List<String> wordBreak(String s, List<String> wordDict)
        {
            Map<String, ArrayList<Integer>> wordPos = new HashMap<String, ArrayList<Integer>>();
            for (int i = 0; i <= wordDict.size() - 1; i++)
            {
                KMP_String_Matching search = new KMP_String_Matching();
                ArrayList<Integer> occurr =  search.KMPSearch(wordDict.get(i), s);
                wordPos.put(wordDict.get(i),occurr);
            }


        }
    }
