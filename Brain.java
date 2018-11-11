public class Brain {

    private Seq[] records;

    // Create a new Brain object when a sequence of necessary length is created.
    public Brain () {
        records = new Seq[0];
    }

    // Add a new sequence to the brain.
    public void add(Seq s) {

        boolean flag = false;

        // Check if sequence is already in Brain records.
        for (int i = 0; i < records.length; i++) {
            if (records[i].equals(s) && records[i].sameVal(s)) {
                records[i].freqUp();
                flag = true;
                break;
            }
        }

        if (!flag) {
            // Sequence not found, so add another one.
            Seq[] temp = new Seq[records.length + 1];

            // Increase array size to hold new sequence.
            for (int i = 0; i < records.length; i++)
                temp[i] = records[i];

            // Add new sequence.
            temp[temp.length - 1] = s;

            // Set instance variable to reflect new array.
            records = temp;
        }
    }

    // Return what the Brain thinks the next user entry will be based on records and most frequent sequence.
    public int opinion(int[] a) {

        // Extract relevant series from int[] a.
        if (records.length == 0)
            return 0;

        int[] temp = new int[records[0].getLength() - 1];

        for (int i = 0; i < temp.length; i++)
            temp[i] = a[a.length-temp.length + i];

        // Create new sequence.
        Seq test = new Seq(temp);

        // Store weighted sum of next value after Seq s.
        double thought = 0;
        int numSeq = 0;

        // Traverse records to find instances of Seq s.
        for (int i = 0; i < records.length; i++) {
            if (records[i].equals(test)) {
                thought += records[i].getVal() * records[i].getFreq();
                numSeq += records[i].getFreq();
            }
        }

        return (int) Math.round(thought / numSeq);

    }

    public String toString() {
        String print = "";

        for (int i = 0; i < records.length; i++) {
            print += records[i].toString() + " " + records[i].getFreq() + '\n';
        }

        return print;
    }

    // Test in main method.
    public static void main(String[] args) {

        int[] test = {1, 2, 3, 4, 1};
        Seq testSeq = new Seq(test);
        int[] test2 = {1, 2, 3, 4, 2};
        Seq testSeq2 = new Seq(test2);


        Brain testB = new Brain();
        testB.add(testSeq);
        testB.add(testSeq2);
        testB.add(testSeq);

        System.out.println(testB);

        int[] needOpinion = {1, 2, 3, 4};

        System.out.println(testB.opinion(needOpinion));

    }

}
