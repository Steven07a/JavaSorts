import java.util.Random;

public class shuffleArr {
    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    public static boolean isSorted(int a[], int size) {
        boolean sorted = true;
        for(int i = 1; i < size; i++) {
            if(a[i-1] > a[i]) {
                System.out.print("This array is not sorted:\n");
                // if(size < 20) {
                //     for(int k = 0; k < size; k++) {
                //         System.out.print(a[k] + " ");
                //     }
                //     System.out.println();   
                // }
                sorted = false;
                break;
            } 
        }
        // if(size < 51) {
        //     for(int k = 0; k < size; k++) {
        //         System.out.print(a[k] + " ");
        //     }
        //     System.out.println();   
        // }
        System.out.println("This array is sorted!");
        return sorted;
    }
}