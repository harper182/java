package com.tw.harper.arithmetic;

public class SortTest {
    /**
     * 1.冒泡排序：由数组头部开始，一次比较两个元素，如果他们的顺序错误就把他们交换过来。每次交换完成后，当前数组最大值就会被放在最后。
     传入参数：a为待排序数组，n为数组长度。
     第一个for循环，用j的值控制第二个循环，即比对数组的长度。由冒泡排序的定义可知，每一次都会将最大值放在最后，所以下一次排的时候就可以少管一个数；
     第二个for循环，将两个数比对，大的放在后面。
     本题第一个for循环是一种小小的优化，可以不使用，直接对整个数组进行交换也是没有问题的
     时间复杂度n*n
     * @param numbers
     * @param size
     * @return
     */
    public static int[] bubbleSort(int[] numbers, int size){
        int tmp;
        for (int i = 0; i < size - 2; i++) {
            for (int j = 0; j < size - i - 1 ; j++) {
                if ( numbers[j] > numbers [j+1]){
                    tmp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = tmp;
                }
            }
            
        }
        return numbers;
    }

    /**
     * 选择排序：每次在数组中选择最小的一个数，将其依次放在数组头对应位置。
     * 时间复杂度 n*n
     * @param a
     * @param n
     * @return
     */
    public static int[] selectionSort(int[] a,int n){
        int maxi;
        int tmp;
        for (int j = 0; j < n - 1; j++){
            maxi = j;
            for (int i = j+1 ; i < n; i++) {
                if(a[maxi] < a[i]){
                    maxi = i;
                }
            }
            if(maxi != j){
                tmp = a[j];
                a[j] = a[maxi];
                a[maxi] = tmp;
            }
        }
        return a;
    }

    /**
     * 插入排序：将其模拟为往一个有序数组中插入一个值。关键在于需要把有序数组比当前数大的数字一个个往后移动
     * 时间复杂度n*n
     * @param a
     * @param n
     * @return
     */
    public static int[] insertionSort(int[] a, int n){
        int num;
        for (int i = 1; i < n; i++) {
            num = a[i];
            int j = i - 1;
            for(; j >= 0; j--){
                if(num < a[j]){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[++j] = num;
        }
        return a;
    }

    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param a 带查找数组
     * @param low   开始位置
     * @param high  结束位置
     * @return  中轴所在位置
     */
    public static int getMiddle(int[] a, int low,int high){
        int temp = a[low];
        while (low < high){
            while (low < high && a[high] > temp){
                high--;
            }
            a[low] = a[high];   ////比中轴小的记录移到低端
            while (low < high && a[low] < temp){
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }

    /**
     * @param a 带排序数组
     * @param low  开始位置
     * @param high 结束位置
     */
    public static void quickSort(int[] a,int low,int high){
        if (low < high) {
            int middle = getMiddle(a,low,high); //将numbers数组进行一分为二
            quickSort(a, low, middle-1);   //对低字段表进行递归排序
            quickSort(a, middle+1, high); //对高字段表进行递归排序
        }
    }

    /**
     * 快速排序是通常被认为在同数量级（O(nlog2n)）的排序方法中平均性能最好的。
     * 但若初始序列按关键码有序或基本有序时，快排序反而蜕化为冒泡排序。为改进之，通常以“三者取中法”来选取基准记录，
     * 即将排序区间的两个端点与中点三个记录关键码居中的调整为支点记录。快速排序是一个不稳定的排序方法。
     * @param a
     */
    public static void quickSort(int[] a){
        if(a.length > 0){
            quickSort(a,0,a.length -1);
        }
    }

    public static void main(String[] args) {
        int[] a = {2,11,33,21,9,0,3,5,7};
//        print(bubbleSort(a,a.length));
//        print(selectionSort(a,a.length));
//        print(insertionSort(a,a.length));
        quickSort(a);
        print(a);
    }
    public static void print(int[] numbers){
        for(int num : numbers){
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
