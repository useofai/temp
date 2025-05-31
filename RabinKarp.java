import java.util.*;

public class RabinKarp {
    static final int d = 256, q = 101; // d = alphabet size, q = prime

    static List<Integer> search(String pat, String txt) {
        int m = pat.length(), n = txt.length();
        int p = 0, t = 0, h = 1;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < m - 1; i++) h = (h * d) % q;

        for (int i = 0; i < m; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        for (int i = 0; i <= n - m; i++) {
            if (p == t) {
                if (txt.substring(i, i + m).equals(pat)) result.add(i);
            }
            if (i < n - m) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
                if (t < 0) t += q;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String txt = "abracadabra";
        String pat = "abra";
        List<Integer> positions = search(pat, txt);
        System.out.println("Pattern found at indices: " + positions);
    }
}
