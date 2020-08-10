import java.io.*;
import java.util.*;

class findArrayQuadruplet {

    /*
    arr = [2, 7, 4, 0, 9, 5, 1, 3]
    s = 20
    [0, 4, 7, 9]
    [2, 4, 5, 9]

    1. sort the array
    loop i = 0 -> n-4
      loop j = i+1 -> n-3
        find 2 element that sum up to s - arr[i], arr[j]

    Time O(n^3)

    f
    O(logN) - second num
    NlogN
    O(N)
        l              r
    [0, 1, 1, 3, 4, 5, 7, 9]

    l = 0;
    r = arr.length-1;

    while(l < r) {
      sum := arr[l] + arr[r]
      if sum == num {
        return l,r;
      }
      if(sum > num) {
         r--;
      }else if(sum < num) {
        l--;
      }
    }


    List<Integer> result;
    arr[0] = 0
    arr[1] = 1, s= 19
    arr[2] = 2, s= 17
    arr[4] = 4, s= 14

    arr[0] = 0;
    rest = s - arr[0];

    pointer

    */
    static int[] findArrayQuadruplet(int[] arr, int s) {
        int length = arr.length;
        //sort the array
        Arrays.sort(arr);
        for(int i = 0; i < length-3; i++) { // i =0
            for(int j = i+1; j < length-2; j++) { // j= 1
                int[] idxs = find(arr, j+1, length-1, s - arr[i] - arr[j]); //2, 3, 8
                if (idxs != null) {
                    int[] res = {arr[i], arr[j], arr[idxs[0]], arr[idxs[1]]};

                    return res;
                }
            }
        }

        return new int[0];
    }

    static int[] find(int[] arr, int s, int e, int num) {
        int l = s;
        int r = e;

        while(l < r) {
            int sum = arr[l] + arr[r];
            if (sum == num) {
                int[] res = {l, r};
                return res;
            }

            if(sum > num) {
                r--;
                continue;
            }

            l++;
        }

        return null;
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 4, 4};
        int[] result = findArrayQuadruplet(arr, 16);

        for(int i : result) {
            System.out.println(i);
        }
    }

}