/*
[Easy/Intermediate] Unusual Bases
Description

Binary numbers (base 2) are written using 1s and 0s to represent which powers of 2 sum together to create the decimal number.
16	8	4	2	1
1	0	0	1	1
A 1 represents using that power of 2 and a 0 means not using it. In the above example there is a one in the 16s, 2s and the 1s so we do:
10011 = 16 + 2 + 1 = 19
meaning that 10011 is binary for 19
The Fibonacci Sequence has a similar property that any positive integer can be written in the form of Fibonacci numbers (with no repeats). For example:
25 = 21 + 3 + 1
If we use the same form as for writing binary, with the Fibonacci sequence instead of powers of 2, we can represent which Fibonacci numbers we use with a 1, and the ones we don't with a 0.
13	8	5	3	2	1	1
1	0	1	0	0	1	0
1010010 = 13 + 5 + 1 = 19
meaning that 101001 is 'Base Fib' for 19
The task is to create a converter to convert to and from decimal to 'Base Fib' Due to the nature of the Fibonacci Sequence, many numbers have multiple representations in 'Base Fib', for the moment these are to be ignored - any form is acceptable.

Input description

You will be given a line of input for each conversion, stating the base it is currently in, and the number to convert seperated by space
10 16
10 32
10 9024720
F 10
F 1
F 111111
F 100000
F 10110110100111001

Output description

The program should output the converted number, in it's expected base, e.g.
1001000
10101000
1010100101010100000010001000010011
1
1
20
8
2868

Notes/Hints

List of Fibonacci Numbers, though you can generate these yourself quite easily.
Your language probably already has a list of primes, although for the bonus you may need to create you own list of Fibonacci Numbers
Bonus

Now, a specific form is required for the 'Base Fib' answers.
Because each term of the sequence is the sum of the previous two, the 'Base Fib' form of a decimal number in the Fibonacci sequence can either be the term itself, or the previous two, e.g.
8             = 100000
8 = 5 + 3     = 11000
8 = 5 + 2 + 1 = 10101
For the bonus challenge, give the output with the least 1's.

Bonus input

10 8
10 16
10 32
10 9024720
*/

import java.util.ArrayList;

public class FibonacciBase {

    private ArrayList<Integer> fibonacciNumbers;

    public static void main(String[] args) {
        FibonacciBase fb = new FibonacciBase();

        System.out.println(fb.decToFib(16));
        System.out.println(fb.decToFib(32));
        System.out.println(fb.decToFib(9024720));

        System.out.println(fb.fibToDec("10"));
        System.out.println(fb.fibToDec("1"));
        System.out.println(fb.fibToDec("111111"));
        System.out.println(fb.fibToDec("100000"));
        System.out.println(fb.fibToDec("10110110100111001"));
    }

    public FibonacciBase() {
        fibonacciNumbers = new ArrayList<>();
    }

    public void populateFibonacci(int target) {
        if (fibonacciNumbers.size() > 0 && target <= fibonacciNumbers.get(fibonacciNumbers.size() - 1)) {
            //do nothing
            return;
        } else {
            if (fibonacciNumbers.size() <= 2) {
                fibonacciNumbers.clear();
                fibonacciNumbers.add(1);
                fibonacciNumbers.add(1);
            }
            int a, b;
            a = fibonacciNumbers.get(fibonacciNumbers.size() - 2);
            b = fibonacciNumbers.get(fibonacciNumbers.size() - 1);
            while (b <= target) {
                int sum = a + b;
                if (sum < target) {
                    fibonacciNumbers.add(sum);
                }
                a = b;
                b = sum;
            }
        }
    }

    public String decToFib(int dec) {
        populateFibonacci(dec);
        StringBuilder sb = new StringBuilder();
        for (int i = fibonacciNumbers.size() - 1; i >= 0; i--) {
            if (dec - fibonacciNumbers.get(i) >= 0) {
                sb.append("1");
                dec = dec - fibonacciNumbers.get(i);
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public int fibToDec(String fib) {
        int result = 0;
        int lengthOfFib = fib.length();
        for (int i = 0; i < lengthOfFib; i++) {
            switch (fib.charAt(0)) {
                case '0':
                    fib = fib.substring(1);
                    break;
                case '1':
                    result += fibonacciNumbers.get(lengthOfFib - i - 1);
                    fib = fib.substring(1);
                    break;
                default: return -1;
            }
        }
        return result;
    }

    public ArrayList<Integer> getFibonacciNumbers() {
        return fibonacciNumbers;
    }

}
