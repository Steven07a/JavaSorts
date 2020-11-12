import java.util.concurrent.ThreadLocalRandom;

public class myTest {
    public static void main(String[] args) {
        // int a[] = {10,9,8,7,6,5,4,3,2,1};
        // ArraySorts.QuickSort2(a,a.length,2);
        // for(int i = 0; i < a.length; i++) {
        //     System.out.print(a[i] + " ");
        // }
        // ArraySorts.AlmostQS1(a,a.length,2);
        for(int k = 0; k < 100; k++) {
            int randSize = 100000000;//ThreadLocalRandom.current().nextInt(29000,30001);

            int arr[] = new int[randSize];
    
            for(int i = 0; i < randSize; i++) {
                arr[i] = ThreadLocalRandom.current().nextInt(randSize-3,randSize);
                //arr[i] = i+1;
            }
    
            shuffleArr.shuffleArray(arr);
            // System.out.println("Arr before sort: ");
            // for(int i = 0; i < arr.length; i++) {
            //     System.out.print(arr[i] + " ");
            // }
            ArraySorts.QuickSort5(arr, arr.length,2);
            //BentleyMcIlroyPartioning.quicksort(arr, 0, arr.length-1);
            //System.out.print(k+1 + " ");
            shuffleArr.isSorted(arr, arr.length);
            //System.out.println();
        }
    }
}
