/********************************
Add Binary: Strings

Given two binary strings, return their sum (also a binary string).
For example,
a = "11"
b = "1"
Return "100".

Solution

Time ~ O (Max {Na, Nb}), Space ~ O (Max {Na, Nb})
From the end to the beginning of the calculation, remember the last to determine whether the highest bit carry, and finally reverse the entire String.
The following methods can be extended to 10 to 10 any of the above binary addition of any number of operations.
// working for any place-value systems below 10.
*************************************/
public class AddBinaryStrings{
public String addBinary(String a, String b) {
    int place = 2;  // 10: decimal, 2 - binary
    int na = a.length(), nb = b.length();
    int digit = 0, carry = 0;
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < Math.max(na, nb); i++) {
        int da = (i < na) ? Character.getNumericValue(a.charAt(na - 1 - i)) : 0;
        int db = (i < nb) ? Character.getNumericValue(b.charAt(nb - 1 - i)) : 0;
        int sum = da + db + carry;
        digit = sum % place;
        carry = sum / place;
        str.append(digit);
    }
    if (carry > 0) str.append(carry);
    return str.reverse().toString();
}
}