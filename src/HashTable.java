import InfoElements.*;

public class HashTable {
    private static final double rehashSize = 0.75;
    private int currentCount = 0;
    private int tablesize;
    private int bufferSize;
    int allCount = 0;
    private Node[] hashtable; //неИспользуем прямое связывание для избежания коллизий, так делать не стоит, лучше открытая адресация

    public HashTable(int tableSize) {
        /*this.bufferSize = PrimeNumbers.findClosestPrime(tableSize * 2);*/
        this.tablesize = tableSize;
        this.bufferSize = tableSize * 2;
        hashtable = new Node[bufferSize]; // Массив параметризированного типа в java
/*        for(int i = 0; i < bufferSize; i++){
            hashtable[i] = null; //(nullptr C++)
        }*/
    }

    private void reSize() {
        int pastBufferSize = bufferSize;
        /*this.bufferSize = PrimeNumbers.findClosestPrime(pastBufferSize * 2);*/
        this.bufferSize *= 2;
        this.allCount = 0;
        this.currentCount = 0;
        Node[] tempHashTable = new Node[bufferSize];
        for (int i = 0; i < bufferSize; i++) {
            tempHashTable[i] = null;
        }
        //(C++ swap[arr1,arr2] ?
        for (int i = 0; i < pastBufferSize; i++) {
            if (hashtable[i].isNotDeleted && tempHashTable[i].isNotDeleted) {
                tempHashTable[i].key = hashtable[i].key;
                tempHashTable[i].dataline = hashtable[i].dataline;
                //По логике не должны встретить ошибку?
                //Не получается правильно из за отсутсвия "Swap" из C++ между массивами в java
            }
        }
        this.hashtable = tempHashTable;
    }

    private void reHash() {
        this.allCount = 0;
        this.currentCount = 0;
        Node[] tempHashTable = new Node[bufferSize];
        for (int i = 0; i < bufferSize; i++) {
            tempHashTable[i] = null;
        }
        for (int i = 0; i < bufferSize; i++) {
            if (hashtable[i].isNotDeleted && tempHashTable[i].isNotDeleted) {
                tempHashTable[i].key = hashtable[i].key;
                tempHashTable[i].dataline = hashtable[i].dataline;
                //По логике не должны встретить ошибку?
                //Не получается правильно из за отсутсвия "Swap" из C++ между массивами в java
            }
        }
        this.hashtable = tempHashTable;
    }

    public Node find(String key) {
        int index1 = Math.abs((int) HashFunction.HashFunction1(key, this.bufferSize));//Не создаст ли коллизий приведение long к int ??
        int index2 = Math.abs((int) HashFunction.HashFunction2(key, this.bufferSize));
        int i = 0;
        while (hashtable[index1] != null && i < bufferSize) {
            if (hashtable[index1].key.equals(key) && hashtable[index1].isNotDeleted) {
                return hashtable[index1];
            }
            index1 = (index1 + index2) % tablesize;
            i++;
        }
        return null;
    }

    public boolean test(String key) {
        if (HashFunction.HashFunction1(key, this.bufferSize) > 0 && HashFunction.HashFunction2(key, this.bufferSize) > 0) {
            return true;
        }
        return false;
    }

    public boolean remove(String key) {
        int index1 = (int) HashFunction.HashFunction1(key, this.bufferSize);//Не создаст ли коллизий приведение long к int ??
        int index2 = (int) HashFunction.HashFunction2(key, this.bufferSize);
        int i = 0;
        while (hashtable[index1] != null && i < bufferSize) {
            if (hashtable[index1].key.equals(key) && hashtable[index1].isNotDeleted) {
                hashtable[index1].isNotDeleted = false;
                --currentCount;
                return true;
            }
            index1 = (index1 + index2) % tablesize;
            i++;
        }
        return false;
    }

    public boolean add(String key, String dataline) {
        /*
        Пытаемся найти равный
        Пытаемся найти по сходному индексу, но удаленный
        */
        if (currentCount + 1 > (int) (rehashSize * bufferSize)) { //+1 это условно добавленный
            reSize();
        }
        if (allCount > currentCount * 2) {
            reHash();
        }
        int firstDeleted = -1;
        int index1 = Math.abs((int) HashFunction.HashFunction1(key, this.bufferSize));//Не создаст ли коллизий приведение long к int ??
        int index2 = Math.abs((int) HashFunction.HashFunction2(key, this.bufferSize));
        int i = 0;
        while (hashtable[index1] != null && i < bufferSize) {
            if (hashtable[index1].key.equals(key) && hashtable[index1].isNotDeleted) {
                return false;
            }
            if (!hashtable[index1].isNotDeleted && firstDeleted == -1) {
                firstDeleted = index1;
            }
            index1 = (index1 + index2) % bufferSize;
            i++;
        }
        if (firstDeleted == -1) {
            hashtable[index1] = new Node(key, dataline);
            allCount++;
        } else {
            hashtable[firstDeleted].key = key;
            hashtable[firstDeleted].isNotDeleted = true;
        }
        currentCount++;
        return true;
    }
}
