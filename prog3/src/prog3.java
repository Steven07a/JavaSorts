import java.util.Random;
/*
 * Author: Steven Herrera
 * Class: CS 282 
 * Meeting Time: M,W 3:30 - 4:45pm
 * Assignment #3
 * Project: Sorting alogithims
 * Purpose: to create different implementations of quicksort and heapsort as well as test there times
 *          
 * 
 * Date turned in: 11/12/20
 * Notes: 
 */

class ArraySorts {
    //a random number getter so i dont need to initilize one everytime 
    private static Random randNum = new Random();

    public static void insertionSort(int a[], int n) {
        int tempIndex = 0;
        for (int i = 1; i < n; i++) {
            tempIndex = i;
            // if a number is less then the number to its right then swap them
            while (tempIndex - 1 >= 0 && a[tempIndex - 1] > a[tempIndex]) {
                swap(a, tempIndex - 1, tempIndex);
                tempIndex--;
            }
        }
    }

    public static void QuickSort1(int a[], int n, int cutoff) {
        qs1(a, 0, n - 1, cutoff);
        insertionSort(a, n);
    }

    private static void qs1(int a[], int lf, int rt, int cutoff) {
        int pivot, lfpt, rtpt;
        while((rt - lf + 1) >= cutoff) {
            pivot = randNum.nextInt((rt - lf) + 1) + lf;
            pivot = outsideInPartition(a, lf, rt, pivot);
            lfpt = pivot + 1;
            rtpt = pivot - 1;
            
            if ((rtpt - lf) < (rt - lfpt)) {
                qs1(a, lf, rtpt, cutoff);
                lf = lfpt;
            } else {
                qs1(a, lfpt, rt, cutoff);
                rt = rtpt;
            }
        }
    }

    private static int outsideInPartition(int a[], int lf, int rt, int pivot) {
        int lfpt = lf+1, rtpt = rt;
        //swap our pivot number to the begining of the arr
        swap(a, lf, pivot);
        while(lfpt <= rtpt) {
            //if a number at left pointer is > our pivot then swap it with right pointer
            //if right pointer is pointing to something smaller then pivot
            if(a[lfpt] >= a[lf]) {
                if(a[rtpt] <= a[lf]) {
                    swap(a, lfpt, rtpt);
                    lfpt++;
                    rtpt--;
                } else {
                    rtpt--;
                }
            } else {
                lfpt++;
            }
        }
        pivot = rtpt;
        swap(a, lf, rtpt);
        return pivot;
    }

    public static void QuickSort2(int a[], int n, int cutoff) {
        qs2(a, 0, n - 1, cutoff);
        insertionSort(a, n);
    }

    private static void qs2(int a[],int lf,int rt,int cutoff) {
        int pivot, lfpt, rtpt;
        while ((rt - lf + 1) >= cutoff) {
            pivot = lf + (int) (Math.random() * (rt - lf + 1));
            pivot = leftToRightPartition(a, lf, rt, pivot);
            lfpt = pivot + 1;
            rtpt = pivot - 1;
            if ((rtpt - lf) < (rt - lfpt)) { // find the smaller partition
                qs2(a, lf, rtpt, cutoff);
                lf = lfpt;
            } else {
                qs2(a, lfpt, rt, cutoff);
                rt = rtpt;
            }
        }
    }

    private static int leftToRightPartition(int a[], int lf, int rt, int pivot) {
        int lfpt = lf;
        // swapping the pivot item to end
        swap(a, rt, pivot);
        for (int i = lfpt; i < rt; i++) {
            //if number is smaller then pivot then swap with left pointer 
            //at the end left pointer will point to the position that pivot belongs in
            if (a[i] < a[rt]) {
                swap(a, i, lfpt);
                lfpt++;
            } 
        }
        //swap our pivot which was moved to end of the arr with left pointer its correct position
        swap(a, rt, lfpt);
        return lfpt;
    }

    public static void QuickSort3(int a[], int n, int cutoff) {
        qs3(a, 0, n - 1, cutoff);
        insertionSort(a, n);
    }

    private static void qs3(int a[], int lf, int rt, int cutoff) {
        int pivot1 = 0, pivot2 = 0;
        pair p;
        if ((rt - lf + 1) < cutoff) {
            // base case
        } else {
            if (lf == rt - 1) {
                pivot1 = lf;
                pivot2 = rt;
            } else {
                pivot1 = randNum.nextInt(rt - lf) + lf;
                // moves the first pivot number to the begining of our arr
                swap(a, lf, pivot1);
                // gets a random number excluding our first element
                pivot2 = randNum.nextInt(rt - lf) + lf + 1;
                swap(a, rt, pivot2);
            }

            p = twoPivotPartition(a, lf, rt);
            //sorts the left side of the arr so left pivot and smaller
            qs3(a, lf, p.getLeft() - 1, cutoff);
            //sorts the center of the array 
            qs3(a, p.getLeft() + 1, p.getRight() - 1, cutoff);
            //sorts the right side of the array
            qs3(a, p.getRight() + 1, rt, cutoff);
        }

    }

    private static pair twoPivotPartition(int a[], int lfpt, int rtpt) {
        int lastSmall = lfpt + 1, firstUnknown = lfpt + 1, firstBig = rtpt - 1;
        //if left pivot > right pivot then swap them
        if (a[lfpt] > a[rtpt]) {
            swap(a, lfpt, rtpt);
        }
        while (firstUnknown <= firstBig) {
            //if number belongs in the middle then increment
            if (a[firstUnknown] > a[lfpt] && a[firstUnknown] < a[rtpt]) {
                firstUnknown++;
            //if number is less then out left pivot then swap with last small
            } else if (a[firstUnknown] <= a[lfpt]) {
                swap(a, firstUnknown, lastSmall);
                lastSmall++;
                firstUnknown++;
            //if a number is greater then right pivot then swap with first big
            } else if (a[firstUnknown] >= a[rtpt]) {
                swap(a, firstUnknown, firstBig);
                firstBig--;
            }
        }
        if(lastSmall >= 1) {
            lastSmall--;
        }
        firstBig++;
        //last small will hold the location of left pivots index so swap them
        swap(a, lfpt, lastSmall);
        //first big hold position of right pivot so swap them
        swap(a, firstBig, rtpt);
        pair p = new pair(lastSmall, firstBig);
        return p;
    }

    public static void QuickSort4(int a[], int n, int cutoff) {
        qs4(a, 0, n - 1, cutoff);
        insertionSort(a, n);
    }

    private static void qs4(int a[], int lf, int rt, int cutoff) {
        int pivot, lfpt, rtpt;
        while((rt - lf + 1) >= cutoff) {
            //sets pivot to left most item
            pivot = lf;
            pivot = outsideInPartition(a, lf, rt, pivot);
            lfpt = pivot + 1;
            rtpt = pivot - 1;
            
            if ((rtpt - lf) < (rt - lfpt)) { // find the smaller partition and sort it
                qs4(a, lf, rtpt, cutoff);
                lf = lfpt;
            } else {
                qs4(a, lfpt, rt, cutoff);
                rt = rtpt;
            }
        }
    }

    public static void QuickSort5(int a[], int n, int cutoff) {
        qs5(a, 0, n - 1, cutoff);
        insertionSort(a, n);
    }

    private static void qs5(int a[], int lf, int rt, int cutoff) {
        int pivot, lfpt, rtpt;
        while ((rt - lf + 1) >= cutoff) { 
            pivot = lf;
            pivot = leftToRightPartition(a, lf, rt, pivot);
            lfpt = pivot + 1;
            rtpt = pivot;
            if ((rtpt - lf) < (rt - lfpt)) { // find the smaller partition and sort it
                qs5(a, lf, rtpt, cutoff);
                lf = lfpt;
            } else {
                qs5(a, lfpt, rt, cutoff);
                rt = rtpt;
            }
        }
    }

    public static void AlmostQS1(int a[], int n, int cutoff) {
        qs1(a, 0, n - 1, cutoff);
        //insertionSort(a, n);
    }

    public static void AlmostQS2(int a[], int n, int cutoff) {
        qs2(a, 0, n - 1, cutoff);
        //insertionSort(a, n);
    }

    public static void AlmostQS3(int a[], int n, int cutoff) {
        qs3(a, 0, n - 1, cutoff);
        //insertionSort(a, n);
    }

    public static void HeapSortTD(int a[], int n) {
        int count = 1;
        while(count < n) {
            heapifyTD(a, 0, count);
            count++;
        }
        for(int i = n-1; i > 0; i--) {
            swap(a, 0, i);
            //trickles down the root
            trickleDown(a, 0, i);
        }
    }
    private static void heapifyTD(int a[], int index, int size) {
        int child = size;
        while (child > index) {
            int parent = (child - 1) / 2;
            if (a[parent] < a[child]) {
                swap(a, parent, child);
                child = parent;
            } else {
                child = index-1;
            }
        }
    }

    private static void trickleDown(int a[], int index, int size) {
        int largestIndex = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        //checks that the left index is still within array then checks if its less then the index given to us
        if (left < size && a[left] > a[largestIndex]) {
            largestIndex = left;
        }

        //checks that the right index is still within array then checks if its less then the index given to us
        if (right < size && a[right] > a[largestIndex]) {
            largestIndex = right;
        }

        //if the largestIndex is not equal to the index given then we found a new largest and need to swap them then recursivly repeat
        if (largestIndex != index) {
            swap(a, index, largestIndex);
            trickleDown(a, largestIndex, size);
        }
    }

    public static void HeapSortBU(int a[], int n) {
        for(int i = n/2 - 1; i >= 0; i--) {
            heapifyBU(a, i, n);
        }
        for(int i = n-1; i > 0; i--) {
            swap(a, 0, i);
            heapifyBU(a, 0, i);
        }
    }

    private static void heapifyBU(int a[], int index, int size) {
        int largestIndex = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        //checks that the left index is still within array then checks if its less then the index given to us
        if (left < size && a[left] > a[largestIndex]) {
            largestIndex = left;
        }

        //checks that the right index is still within array then checks if its less then the index given to us
        if (right < size && a[right] > a[largestIndex]) {
            largestIndex = right;
        }

        //if the largestIndex is not equal to the index given then we found a new largest and need to swap them then recursivly repeat
        if (largestIndex != index) {
            swap(a, index, largestIndex);
            heapifyBU(a, largestIndex, size);
        }
    }
    public static String myName() {
        return "Steven Herrera";
    }

    // helper function to swap two elements within an array
    private static void swap(int a[], int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    public static boolean testSetsWork() {
        return false;
    }
}

//class to return the two pivot locations in the 2-pivot partition
class pair {
    public int left, right;

    public pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}