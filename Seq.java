public class Seq {

    private int[] entries;  // A series of n numbers followed by value that occurred after user entered this series.
    private int freq;

    // Sequence represents an array of entries in the order they were entered.
    // NOTE: The last item in entries is in fact the value that followed a series of numbers.
    public Seq (int [] entries) {
        this.entries = entries;
        freq = 1;
    }

    // Increment frequency because sequence was found multiple times.
    public void freqUp () {
        freq++;
    }

    // Get frequency.
    public int getFreq () {
        return freq;
    }

    // Return values that occured after a series of numbers.
    public int getVal() {
        return entries[entries.length - 1];
    }

    // Return the look-back of the sequence.
    public int getLength() {
        return entries.length;
    }

    // Return true if series is equal to that of another sequence. Recall, we do not compare the last item in series
    // because this last item is the value that occured after the series.
    public boolean equals(Seq s) {

        if (Math.abs(entries.length - s.entries.length) > 1)
            return false;

        for (int i = 0; i < entries.length - 1; i++) {
            if (entries[i] != s.entries[i])
                return false;
        }

        return true;
    }

    public boolean sameVal(Seq s) {
        if (entries.length != s.entries.length)
            return false;

        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != s.entries[i])
                return false;
        }

        return true;
    }

    // Print values for debugging.
    public String toString () {

        String print = "";

        for(int i = 0; i < entries.length; i++)
            print += entries[i];

        return print;
    }


    // Test in main method.
    public static void main(String[] args) {

        int[] test = {1, 2, 3, 4, 5};

        Seq testSeq = new Seq(test);

        System.out.println(testSeq);
        System.out.println(testSeq.getLength());
        System.out.println(testSeq.getVal());

        int[] test2 = {1, 2, 3, 4};
        Seq testSeq2 = new Seq(test2);

        System.out.println(testSeq.equals(testSeq2));

        testSeq.freqUp();
        System.out.println(testSeq.getFreq());

        System.out.println(testSeq.sameVal(testSeq2));

        int[] test3 = {1, 2, 3, 4, 5};
        Seq testSeq3 = new Seq(test);

        System.out.println(testSeq.sameVal(testSeq3));


    }


}
