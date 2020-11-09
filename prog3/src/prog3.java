
// Get random numbers the way you did in the first program. I think most of you 
// used the Random class. If you use the Random class be sure not to instantiate
// multiple, unnecessary copies.
import java.math.*;
// maybe not needed?
// or
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// use this class to contain everything related to your sorts
class ArraySorts {
    private static Random randNum = new Random();

    // Some sample driver method headers
    public static void insertionSort(int a[], int n) {
        int tempIndex = 0;
        for (int i = 1; i < n; i++) {
            tempIndex = i;
            while (tempIndex - 1 >= 0 && a[tempIndex - 1] > a[tempIndex]) {
                swap(a, tempIndex - 1, tempIndex);
                tempIndex--;
            }
        }
    }

    public static void QuickSort1(int a[], int n, int cutoff) {
        qs1(a, 0, n - 1, cutoff);
    }

    private static void qs1(int a[], int lf, int rt, int cutoff) {
        int pivot = 0;
        if ((rt - lf + 1) < cutoff) {
            // base case do insertionsort instead
            insertionSort(a, rt + 1);
        } else {
            // gets us a random number
            pivot = randNum.nextInt((rt - lf) + 1) + lf;
            pivot = outsideInPartition(a, lf, rt, pivot);
            qs1(a, lf, pivot - 1, cutoff);
            qs1(a, pivot + 1, rt, cutoff);
        }
    }

    private static int outsideInPartition(int a[], int lf, int rt, int pivot) {
        int lfpt = lf + 1, rtpt = rt;
        swap(a, lf, pivot);
        while (lfpt <= rtpt) {
            if (a[lfpt] >= a[lf]) {
                if (a[rtpt] <= a[lf]) {
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
    }

    private static void qs2(int a[],int lf,int rt,int cutoff) {
        if((rt - lf + 1) < cutoff) {
            //base case
            insertionSort(a, rt+1);
        } else {
            int pivot,lfpt = lf, midpt = lf, rtpt = lf;
            pivot = randNum.nextInt((rt-lf)+1) + lf;
            swap(a, rt, pivot);
            //pivot = leftToRightPartition(a, lf, rt);
            while(rtpt != rt) {
                if(a[rtpt] < a[rt]) {
                    swap(a, lfpt, rtpt);
                    swap(a, midpt, rtpt);
                    lfpt++;
                    midpt++;
                    rtpt++;
                } else if (a[rtpt] == a[rt]) {
                    swap(a, midpt, rtpt);
                    midpt++;
                    rtpt++;
                } else if (a[rtpt] > a[rt]) {
                    rtpt++;
                }
            }
            swap(a, midpt, rtpt);
            qs2(a, lf, lfpt-1, cutoff);
            qs2(a, midpt+1, rt, cutoff);
        }
    }

    private static int leftToRightPartition(int a[], int lf, int rt) {
        int lfpt = lf; // used to be rt-1
        // swapping the pivot item to end
        int pivot = randNum.nextInt((rt-lf)+1) + lf;
        swap(a, rt, pivot);
        for (int i = lfpt; i < rt; i++) {
            if (a[i] < a[rt]) {
                swap(a, i, lfpt);
                lfpt++;
            }
        }
        pivot = lfpt;
        swap(a, rt, pivot);
        return pivot;
    }

    public static void QuickSort3(int a[], int n, int cutoff) {
        qs3(a, 0, n - 1, cutoff);
    }

    private static void qs3(int a[], int lf, int rt, int cutoff) {
        int pivot1 = 0, pivot2 = 0;
        pair p;
        if (((rt - lf + 1) > 0)) {
            if ((rt - lf + 1) < cutoff) {
                // base case call insertion sort
                insertionSort(a, rt + 1);
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
                qs3(a, lf, p.getLeft() - 1, cutoff);
                qs3(a, p.getLeft() + 1, p.getRight() - 1, cutoff);
                qs3(a, p.getRight() + 1, rt, cutoff);
            }
        }
    }

    private static pair twoPivotPartition(int a[], int lfpt, int rtpt) {
        int lastSmall = lfpt + 1, firstUnknown = lfpt + 1, firstBig = rtpt -1;
        if (a[lfpt] > a[rtpt]) {
            swap(a, lfpt, rtpt);
        }
        while (firstUnknown <= firstBig) {
            if (a[firstUnknown] > a[lfpt] && a[firstUnknown] < a[rtpt]) {
                firstUnknown++;
            } else if (a[firstUnknown] <= a[lfpt]) {
                swap(a, firstUnknown, lastSmall);
                lastSmall++;
                firstUnknown++;
            } else if (a[firstUnknown] >= a[rtpt]) {
                swap(a, firstUnknown, firstBig);
                firstBig--;
            }
        }
        if(lastSmall >= 1) {
            lastSmall--;
        }
        firstBig++;
        swap(a, lfpt, lastSmall);
        swap(a, firstBig, rtpt);
        pair p = new pair(lastSmall, firstBig);
        return p;
    }

    public static void QuickSort4(int a[], int n, int cutoff) {
        qs4(a, 0, n - 1, cutoff);
    }

    private static void qs4(int a[], int lf, int rt, int cutoff) {
        if ((rt - lf + 1) < cutoff) {
            // base case
            insertionSort(a, rt + 1);
        } else {
            int pivot,lfpt = lf, midpt = lf, rtpt = rt-1;
            pivot = lf;
            swap(a, rt, pivot);
            //pivot = leftToRightPartition(a, lf, rt);
            while(rtpt > midpt) {
                if(a[rtpt] < a[rt]) {
                    swap(a, lfpt, rtpt);
                    //swap(a, midpt, rtpt);
                    lfpt++;
                    midpt++;
                    //rtpt--;
                } else if (a[rtpt] == a[rt]) {
                    swap(a, midpt, rtpt);
                    midpt++;
                    //rtpt--;
                } else if (a[rtpt] > a[rt]) {
                    rtpt--;
                }
            }
            swap(a, midpt, rtpt);
            qs4(a, lf, lfpt-1, cutoff);
            qs4(a, midpt+1, rt, cutoff);
        }
    }

    public static void QuickSort5(int a[], int n, int cutoff) {
        qs5(a, 0, n - 1, cutoff);
    }

    private static void qs5(int a[], int lf, int rt, int cutoff) {
        if ((rt - lf + 1) < cutoff) {
            // base case
            insertionSort(a, rt + 1);
        } else {
            int pivot,lfpt = lf, midpt = lf, rtpt = lf;
            pivot = randNum.nextInt((rt-lf)+1) + lf;
            swap(a, rt, pivot);
            //pivot = leftToRightPartition(a, lf, rt);
            while(rtpt != rt) {
                if(a[rtpt] < a[rt]) {
                    swap(a, lfpt, rtpt);
                    swap(a, midpt, rtpt);
                    lfpt++;
                    midpt++;
                    rtpt++;
                } else if (a[rtpt] == a[rt]) {
                    swap(a, midpt, rtpt);
                    midpt++;
                    rtpt++;
                } else if (a[rtpt] > a[rt]) {
                    rtpt++;
                }
            }
            swap(a, midpt, rtpt);

            qs5(a, lf, lfpt-1, cutoff);
            qs5(a, midpt+1, rt, cutoff);
        }
    }

    public static void AlmostQS1(int a[], int n, int cutoff) {
        aqs1(a, 0, n - 1, cutoff);
    }

    private static void aqs1(int a[], int lf, int rt, int cutoff) {
        int pivot = 0;
        if ((rt - lf + 1) <= 1) {
            // base case
        } else {
            // pivot = ThreadLocalRandom.current().nextInt(lf,rt);
            pivot = randNum.nextInt(rt - lf) + lf;
            pivot = outsideInPartition(a, lf, rt, pivot);
            aqs1(a, lf, pivot - 1, cutoff);
            aqs1(a, pivot + 1, rt, cutoff);
        }
    }

    public static void AlmostQS2(int a[], int n, int cutoff) {
        aqs2(a, 0, n - 1, cutoff);
    }

    private static void aqs2(int a[], int lf, int rt, int cutoff) {
        if ((rt - lf + 1) <= 1) {
            // base case
        } else {
            // pivot = ThreadLocalRandom.current().nextInt(lf,rt+1);
            int pivot,lfpt = lf, midpt = lf, rtpt = lf;
            pivot = randNum.nextInt((rt-lf)+1) + lf;
            swap(a, rt, pivot);
            //pivot = leftToRightPartition(a, lf, rt);
            while(rtpt <= rt) {
                if(a[rtpt] < a[rt]) {
                    swap(a, lfpt, rtpt);
                    swap(a, midpt, rtpt);
                    lfpt++;
                    midpt++;
                    rtpt++;
                } else if (a[rtpt] == a[rt]) {
                    swap(a, midpt, rtpt);
                    midpt++;
                    rtpt++;
                } else if (a[rtpt] > a[rt]) {
                    rtpt++;
                }
            }
            if(rtpt > rt) {
                rtpt = rt;
            }
            swap(a, midpt, rtpt);
            aqs2(a, lf, lfpt-1, cutoff);
            aqs2(a, midpt+1, rt, cutoff);
        }
    }

    public static void AlmostQS3(int a[], int n, int cutoff) {
        aqs3(a, 0, n - 1, cutoff);
    }

    private static void aqs3(int a[], int lf, int rt, int cutoff) {
        int pivot1 = 0, pivot2 = 0;
        pair p;
        if (((rt - lf + 1) > 0)) {
            if ((rt - lf + 1) <= 1) {
                // base case call insertion sort
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
                qs3(a, lf, p.getLeft() - 1, cutoff);
                qs3(a, p.getLeft() + 1, p.getRight() - 1, cutoff);
                qs3(a, p.getRight() + 1, rt, cutoff);
            }
        }
    }

    public static void HeapSortTD(int a[], int n) {

    }

    public static void HeapSortBU(int a[], int n) {

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

// use this class to return the two pivot locations in the 2-pivot partition
// algorithm
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