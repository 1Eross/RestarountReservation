package HashUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*

Не мой код

 */

public class PrimeNumbers {
    private static List<Integer> sieveOfEratosthenes(int n){
        boolean[] prime = new boolean[n+1];
        for(boolean b: prime){
            b = true;
        }
        for(int p = 2; p * p <= n; p++){
            if(prime[p]){
                for(int i = p * p; i <= n; i += p){
                    prime[p] = false;
                }
            }
        }
        Vector<Integer> temp = new Vector<>();
        for(int i = 2; i < n; i++){
            if(prime[i]){
                temp.add(i);
            }
        }
        return temp;
    }

    private static int upperBound (Integer[] arr, int low, int high, int x){
        if (low > high){
            return low;
        }
        int mid = low +(high - low)/2;
        if(arr[mid] <= x){
            return upperBound(arr, mid + 1, high, x);
        }
        return upperBound(arr, low, mid - 1, x);
    }
    public static int findClosestPrime(int n){
        if(n == 1){
            return 2;
        }
        else{
            List<Integer> temp = sieveOfEratosthenes(n);
            Integer[] arr = temp.toArray(new Integer[temp.size()]);
            int index = upperBound(arr, 0, arr.length, n);
            if(arr[index] == n || arr[index - 1] == n){
                return n;
            }
            else if(Math.abs(arr[index] - n) < Math.abs(arr[index-1] - n)){
                return arr[index];
            }
            else{
                return arr[index-1];
            }

        }
    }

}
