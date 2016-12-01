/**
 * My hash table.
 * @author A-Arndt
 *
 */
public class HashTable {
    //Initialization of the table
    Bucket[] table = new Bucket[16000057];

    /**
    *The add function will add a hashed string to the table.
    *@param string to be added
    *@return true if added
    */
    public boolean add(String value) {
        boolean found = false;
        boolean done = false;
        int thisTry = 0;
        long hash = hash(value);
        int maxTries = table.length;
        while (!done && !found && thisTry < maxTries) {
            int index = probe(hash, thisTry);
            if (table[index] == null) {
                table[index] = new Bucket(value);
                done = true;
            } else if (table[index].isDeleted()) {
                table[index].setWord(value);
                done = true;
            } else if (table[index ].getWord().equals(value)) {
                found = true;
            } else {
                thisTry++;
            }
        }
        return done;
    }

    /**
    *The delete function will delete a hashed string from the table.
    *@param string to be deleted
    *@return true if deleted
    */
    public boolean delete(String value) {
        boolean found = false;
        boolean done = false;
        int thisTry = 0;
        long hash = hash(value);
        int maxTries = table.length;
        while (!done && !found && thisTry < maxTries) {
            int index = probe(hash, thisTry);
            if (table[index] == null) {
                done = true;
            } else if (table[index].isDeleted()) {
                thisTry++;
            } else if (table[index].getWord().equals(value)) {
                table[index].setDeleted(true);
                table[index].setWord(null);
                found = true;
            } else {
                thisTry++;
            }
        }
        return found;
    }
    
     /**
     * Used to find a specific item in the hash table.
     * @param value the item you are looking for.
     * @return true if found, false if not found.
     */
    public boolean find(String value) {
        boolean found = false;
        boolean done = false;
        int thisTry = 0;
        long hash = hash(value);
        int maxTries = table.length;
        while (!done && !found && thisTry < maxTries) {
            int index = probe(hash, thisTry);
            if (table[index] == null) {
                done = true;
            } else if (table[index].isDeleted()) {
                thisTry++;
            } else if (table[index].getWord().equals(value)) {
                found = true;
            } else {
                thisTry++;
            }
        }
        return found;
    }

    /**
    *The probe function will create the index to be looked at.
    *@param hash is the hash value to look at
    *@param curTry is the current itteration that will be tried starting from 0.
    *@return an int that will specifiy the index to attempt to add at.
    */
    public int probe(long hash, int curTry) {
        return (int) ((hash + (curTry * curTry)) % table.length);
    }

    /**
     * A function to hash a string value into a specified integer
     * using the number value of each char, and adding them together.
     * @param value the string to be converted
     * @return the hashed value.
     */
    public long hash(String value) {
        long hashVal = 0;
        for(int i = 0; i < value.length(); i++) {
            hashVal = (hashVal * 31) + value.charAt(i);
        }

        return hashVal;
    }

    /**
     * The object of buckets.
     * @author Austin
     *
     */
    private class Bucket {
        private boolean deleted;
        private String word;

        /**
         * Crates a new bucket object.
         * @param newWord
         */
        public Bucket(String newWord) {
            this.setWord(newWord);
            this.setDeleted(false);
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }


    }
}
