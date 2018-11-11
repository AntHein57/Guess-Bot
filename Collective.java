public class Collective {

    private Brain[] mastermind;

    // Create a Collective object based on an array of Brain objects.
    public Collective (Brain[] brains) {

        // Purposely want an unprotected copy, otherwise we can not reflect changes
        // in user behavior as the game progresses.
        mastermind = brains;
    }

    // Return the value (1 or 2) that is agreed upon by the most brains.
    public int predict(int[] a) {

        double prediction = 0;
        int numOpinions = 0;

        // Transverse mastermind array.
        for (int i = 0; i < mastermind.length; i++) {
            if (mastermind[i].opinion(a) != 0) {
                prediction += mastermind[i].opinion(a);
                numOpinions++;
            }
        }

        return (int) Math.round(prediction/ numOpinions);
    }
}
