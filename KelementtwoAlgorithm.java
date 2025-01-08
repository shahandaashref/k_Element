import java.util.Arrays;

public class KelementtwoAlgorithm {
    public static int KElementOfTwoArray(int[] a, int[] b, int k) {
        //----If one of the arrays is empty, return the k-th element of the other array----//
        if (a.length == 0) {
            return b[k - 1];
        } else if (b.length == 0) {
            return a[k - 1];
        } else if (k == 1) {
            return Math.min(a[0], b[0]);
        }
        
        //----Find the mid-point of the arrays----//
        int mida = Math.min(k / 2, a.length) - 1;
        int midb = Math.min(k / 2, b.length) - 1;
        
        //----Find the minimum element between the two mid-points----//
        if (a[mida] <= b[midb]) {
            a = Arrays.copyOfRange(a, mida + 1, a.length);
            return KElementOfTwoArray(a, b, k - (mida + 1));
        } else {
            b = Arrays.copyOfRange(b, midb + 1, b.length);
            return KElementOfTwoArray(a, b, k - (midb + 1));
        }
    }

    public static int KElementOfTwoArrayBruteForce(int[] a, int[] b, int k) {
        //----Merge the two arrays----//
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, index = 0;

        //----Merge the two arrays----//
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                merged[index++] = a[i++];
            } else {
                merged[index++] = b[j++];
            }
        }

        //----Copy the remaining elements of the arrays----//
        while (i < a.length) {
            merged[index++] = a[i++];
        }
        while (j < b.length) {
            merged[index++] = b[j++];
        }
        
        return merged[k - 1];
    }
    
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int k = 5;
        
        System.out.println("The " + k + "-th element is: " + KElementOfTwoArray(arr1, arr2, k));
    }
    }
    


