/**********************************************
Reverse Bits

Reverse bits of a given 32 bits unsigned integer.
For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).
Follow up:
If this function is called many times, how would you optimize it?

Solution


Bit Manipulation: Time ~ O(N), Space ~ O(1)
***************************************************/
public class ReversBits{

public int reverseBits(int n) {
    int val = 0;
    for (int i = 0; i < 32; i++) {
        val = val << 1 | n & 1; // use | instead of +
        n = n >> 1;
    }
    return val;
}
}
