package HashUtilities;

public class HashFunction {
    private static final long basisFnvOffset = 2166136261L; // Postfix L - means that number type is long
    private static final long primeFnv = 16777619L;  // Random value

    public static long HashFunction1(String lineToHash, int tableSize) {
        return fnvHasher(lineToHash) % tableSize - 1;   //Используем для первичного хеширования
    }

    /*
    Две функции для уменьшения коллизий
    Первичная добаляет эллемент.
    Вторичная используется, при происхождении коллизии и ставит элл на место ((hash)*2-1) % tablesize
     */
    public static long HashFunction2(String lineToHash, int tableSize) {
        return (fnvHasher(lineToHash) * 2 - 1) % tableSize; // Используем для вторичного хеширования
    }

    public static long fnvHasher(String lineToHash) {
        long hash = basisFnvOffset;
        for (byte bte : lineToHash.getBytes()) {
            hash = (hash * primeFnv) ^ bte;
        }
        return hash;
    }
}