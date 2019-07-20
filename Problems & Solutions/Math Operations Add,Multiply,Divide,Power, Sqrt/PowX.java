
/********************
Pow(x, n)

Implement pow(x, n).

Solution

Recursion: Time ~ O(logN), Space ~ O(1) 
if n is even, pow(x, n) = pow(x, n/2) * pow(x, n/2);
if n is odd, pow(x, n) = pow(x, n/2) * pow(x, n/2) * x.
********************************/

public class PowX{
public double pow(double x, int n) {
    if (n < 0)  return 1.0 / power(x, -n);
    else        return power(x, n);
}

private double power(double x, int n) {
    if (n == 0) return 1;
    double v = pow(x, n >> 1);          // n / 2
    if ((n & 1) == 0)   return v * v;   // n % 2
    else                return v * v * x;
}
}