
/***************************************************
ZigZag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P      A     H     N
A  P  L  S  I   I  G
Y       I      R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

Solution

Time ~ O(N), Space ~ O(1) 

***************************************************/
public class ZigZagCOnversion{

public String convert(String s, int nRows) {
    if (nRows == 1) return s;
    StringBuilder str = new StringBuilder();
    int skip1 = nRows + (nRows - 2);
    int skip2 = skip1;
    for (int i = 0; i < nRows; i++) {
        if (i != 0 && i != nRows - 1)   skip2 -= 2;
        int index = i;
        while (index < s.length()) {
            str.append(s.charAt(index));
            if (i != 0 && i != nRows - 1 && index + skip2 < s.length())
                str.append(s.charAt(index + skip2));
            index += skip1;
        }
    }
    return str.toString();
}
}