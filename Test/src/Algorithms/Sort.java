package Algorithms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
 * 
 1.选择排序：不稳定，时间复杂度 O(n^2)
 选择排序的基本思想是对待排序的记录序列进行n-1遍的处理，第i遍处理是将L[i..n]中最小者与L[i]交换位置。这样，经过i遍处理之后，前i个记录的位置已经是正确的了。 
 2.插入排序：稳定，时间复杂度 O(n^2)
 插入排序的基本思想是，经过i-1遍处理后,L[1..i-1]己排好序。第i遍处理仅将L[i]插入L[1..i-1]的适当位置，使得L[1..i] 又是排好序的序列。要达到这个目的，我们可以用顺序比较的方法。首先比较L[i]和L[i-1]，如果L[i-1]≤ L[i]，则L[1..i]已排好序，第i遍处理就结束了；否则交换L[i]与L[i-1]的位置，继续比较L[i-1]和L[i-2]，直到找到某一个位置j(1≤j≤i-1)，使得L[j] ≤L[j+1]时为止。图1演示了对4个元素进行插入排序的过程，共需要(a),(b),(c)三次插入。
 3.冒泡排序：稳定，时间复杂度 O(n^2)
 冒泡排序方法是最简单的排序方法。这种方法的基本思想是，将待排序的元素看作是竖着排列的“气泡”，较小的元素比较轻，从而要往上浮。在冒泡排序算法中我们要对这个“气泡”序列处理若干遍。所谓一遍处理，就是自底向上检查一遍这个序列，并时刻注意两个相邻的元素的顺序是否正确。如果发现两个相邻元素的顺序不对，即“轻”的元素在下面，就交换它们的位置。显然，处理一遍之后，“最轻”的元素就浮到了最高位置；处理二遍之后，“次轻”的元素就浮到了次高位置。在作第二遍处理时，由于最高位置上的元素已是“最轻”元素，所以不必检查。一般地，第i遍处理时，不必检查第i高位置以上的元素，因为经过前面i-1遍的处理，它们已正确地排好序。 
 4.堆排序：不稳定，时间复杂度 O(nlog n)
 堆排序是一种树形选择排序，在排序过程中，将A[n]看成是完全二叉树的顺序存储结构，利用完全二叉树中双亲结点和孩子结点之间的内在关系来选择最小的元素。 
 5.归并排序：稳定，时间复杂度 O(nlog n)
 设有两个有序（升序）序列存储在同一数组中相邻的位置上，不妨设为A[l..m]，A[m+1..h]，将它们归并为一个有序数列，并存储在A[l..h]。 
 6.快速排序：不稳定，时间复杂度 最理想 O(nlogn) 最差时间O(n^2)
 快速排序是对冒泡排序的一种本质改进。它的基本思想是通过一趟扫描后，使得排序序列的长度能大幅度地减少。在冒泡排序中，一次扫描只能确保最大数值的数移到正确位置，而待排序序列的长度可能只减少1。快速排序通过一趟扫描，就能确保某个数（以它为基准点吧）的左边各数都比它小，右边各数都比它大。然后又用同样的方法处理它左右两边的数，直到基准点的左右只有一个元素为止。
 7.希尔排序：不稳定，时间复杂度 平均时间 O(nlogn) 最差时间O(n^s) 1<s<2
 在直接插入排序算法中，每次插入一个数，使有序序列只增加1个节点，并且对插入下一个数没有提供任何帮助。如果比较相隔较远距离（称为 增量）的数，使得数移动时能跨过多个元素，则进行一次比较就可能消除多个元素交换。D.L.shell于1959年在以他名字命名的排序算法中实现了这一思想。算法先将要排序的一组数按某个增量d分成若干组，每组中记录的下标相差d.对每组中全部元素进行排序，然后再用一个较小的增量对它进行，在每组中再进行排序。当增量减到1时，整个要排序的数被分成一组，排序完成。
 8.基数排序：稳定 ，时间复杂度为O(nlog(r)m)r为采取的基数，m为堆数；非比较型整数排序算法，将整数按照位数切割成不同的数字，这里的整数也可以是字符串比如名字和时间
 将所有待比较数值(正整数)统一为同样的数位长度,数位较短的数前面补零. 然后, 从最低位开始, 依次进行一次排序.这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列.基数排序的方式可以采用LSD（Least significantdigital）或MSD（Most significantdigital），LSD的排序方式由键值的最右边开始，适合低位数，而MSD则相反，由键值的最左边开始适合高位数
 */
/**
 * 
 * 
 * @ClassName: Sort
 * 
 * @Description: TODO(实现一些排序算法，并简单的测试速度)
 * 
 * @author xieydd xieydd@gmail.com
 * 
 * @date 2017-8-4 下午3:06:17
 * 
 * 
 */
public class Sort {
	public static void main(String[] args) {
		int[] src = getRandomIntArr(1000000);
		long startTimes = System.currentTimeMillis();

		// insertSort(src);//100000个数排序花费时间4秒
		// shellSort(src);// 100000个数排序花费时间1秒
		// selectSort(src);//100000个数排序花费时间4秒
		//bubbleSort(src);// 100000个数排序花费时间21秒
		//quickSort(src, 0, src.length-1);//10000000个数排序花费时间1秒
		//mergingSort(src, 0, src.length - 1);//100000个数排序花费时间5秒
		radixSort(src,src.length);//1000000个数排序花费时间17秒
		
		long endTimes = System.currentTimeMillis();
		long times = endTimes - startTimes;
		// SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String datas = data.format(new Date(times));
		System.out.println(src.length + "个数排序花费时间" + times / 1000 + "秒");
	}
	
	public static void radixSort(int[] src,int d){
		
		//代表位数对应的数
		int n =1;
		//保存每一位排序后的结果，用于下一次的排序的输入
		int k =0;
		
		//保存每一次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		int[][] bucket = new int[10][src.length];
		//用于保存每一个桶中有多少个数字
		int[] order = new int[src.length];
		
		
		while(n<d){
			for(int num:src) {//将数组中的数据放到相应的桶里
				int digit = (num/n)%10;
				bucket[digit][order[digit]] = num;
				order[digit]++;//该位数为digit的个数
			}
			
			for(int i =0;i<10;i++) {//将前一个循环生成的桶里数据覆盖到原数组中保存这一位排序的结果
				if(order[i]!=0) {//只要桶里有数据
					for(int j =0;j<order[i];j++) {
						src[k] = bucket[i][j];
						k++;
					}
				}
				order[i]=0;//将桶里的计数置0，用于下一位的排序
			}
			n*=10;
			k=0;//将k置0为下一轮保存位排序结果,因为每一次都排序所有的
		}
		showArr(src);
	} 
	
	
	
	
	
	/**
	 * 
	 * 
	 * @Title: mergingSort
	 * 
	 * @Description: TODO(归并排序)将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列
	 * 
	 * @param @param src
	 * @param @param left
	 * @param @param right 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public static void mergingSort(int[] src, int left, int right) {
		if (left >= right) {
			return;
		}
		//System.out.println(left + "," + right);
		int center = (left + right) / 2;
		//左边数组进行递归
		mergingSort(src, left, center);
		//右边数组进行递归
		mergingSort(src, center + 1, right);
		//左右合并
		merge(src, left, right, center);
		//showArr(src);
	}
	/**
	 * 
	* @Title: merge 
	
	* @Description: TODO 归并排序中进行调用，(归并:归并前的两个数组以及有序)
	
	* @param @param src
	* @param @param left
	* @param @param right
	* @param @param center    设定文件 
	
	* @return void    返回类型 
	
	* @throws
	 */
	public static void merge(int[] data, int left, int right, int center) {
		System.out.println(left + "," + center + "," + right);

		int[] tmpArr = new int[data.length];
		//右指针
		int mid = center + 1;
		//记录临时数组的索引 
		int third = left;
		//左指针
		int tmp = left;
		
		while (left <= center && mid <= right) {
			//将较小的数移到新数组中
			if (data[left] <= data[mid]) {
				tmpArr[third++] = data[left++];
			} else {
				tmpArr[third++] = data[mid++];
			}
		}
		//将剩余的部分依次放入临时数组
		//将右边剩余的数移入数组
		while (mid <= right) {
			tmpArr[third++] = data[mid++];
		}
		//将左边剩余的数移入数组
		while (left <= center) {
			tmpArr[third++] = data[left++];
		}
		//将新数组覆盖原数组(left到right的内容被复制到原数组)
		while (tmp <= right) {
			data[tmp] = tmpArr[tmp++];
		}
	}
	/**
	 * 
	 * 
	 * @Title: quickSort
	 * 
	 * @Description: TODO(快速排序)
	 * 
	 * @param @param src
	 * @param @param low
	 * @param @param high 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public static void quickSort(int[] src, int low, int high) {
		if (low < high) {
			int middle = getMiddle(src, low, high);
			quickSort(src, low, middle - 1);
			quickSort(src, middle + 1, high);
		}
	}

	public static int getMiddle(int[] list, int low, int high) {
		int tmp = list[low];// 选择一个作为中轴
		while (low < high) {
			while (low < high && list[high] > tmp) {
				high--;
			}
			list[low] = list[high];
			while (low < high && list[low] <= tmp) {
				low++;
			}
			list[high] = list[low];
		}
		list[low] = tmp;// 循环过后还是将原先设定的第一个数放到中轴位
		return low;// 得到中位数
	}

	/**
	 * 
	 * 
	 * @Title: bubbleSort
	 * 
	 * @Description: TODO(冒泡排序算法)
	 * 
	 * @param @param src 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public static void bubbleSort(int[] src) {
		int temp = 0;
		for (int i = 0; i < src.length - 1; i++) {
			for (int j = 0; j < src.length - 1 - i; j++) {// 注意将最大的放到后面就不管了
				if (src[j] > src[j + 1]) {
					// 大放后小放前
					temp = src[j];
					src[j] = src[j + 1];
					src[j + 1] = temp;
				}
			}
		}
		showArr(src);
	}

	/**
	 * 
	 * 
	 * @Title: selectSort
	 * 
	 * @Description: TODO(简单选择排序)
	 * 
	 * @param @param src 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public static void selectSort(int[] src) {
		int position = 0;
		for (int i = 0; i < src.length; i++) {
			int temp = src[i];
			position = i;
			int j = i + 1;
			for (; j < src.length; j++) {
				if (temp > src[j]) {// 将最小的选择出来并记得其位号
					temp = src[j];
					position = j;
				}
			}
			src[position] = src[i];
			src[i] = temp;
		}
		showArr(src);
	}

	/**
	 * 
	 * 
	 * @Title: shellSort
	 * 
	 * @Description: TODO(希尔排序（最小增量排序）实际上是分组插入的方法)
	 * 
	 * @param @param src 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public static void shellSort(int[] src) {

		int d = (int) Math.ceil(src.length / 2);
		int temp;

		while (d > 0) {
			for (int i = d; i < src.length; i++) {

				temp = src[i];
				int j = i - d;
				while (j >= 0 && temp < src[j]) {
					src[j + d] = src[j];
					j = j - d;
				}
				src[j + d] = temp;
			}
			d = d / 2;
		}
		showArr(src);
	}

	/**
	 * 
	 * 
	 * @Title: insertSort
	 * 
	 * @Description: TODO(简单插入算法 注意for循环出来的条件和j的设定
	 *               取出数据和前面所有的相比较出现小于的继续，出现大于的化赋值到指定的位置)
	 * 
	 * @param @param src 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public static void insertSort(int[] src) {

		for (int i = 1; i < src.length; i++) {
			int temp = src[i];
			int j = i - 1;
			for (; j >= 0 && src[j] > temp; j--) {
				src[j + 1] = src[j];
			}
			src[j + 1] = temp;
		}
		showArr(src);
	}

	/*
	 * 当然是可以取出数据往里面放，但是要判断数是否在里面，如果数据量大的化会非常占内存，改进(测试得到长度为n且数组数值为1到n且不能重复)
	 * 这种好处是,随机数所取范围逐步缩小,而且杜绝了大数据时集合执行删除操作时产生的瓶颈
	 */
	/**
	 * 
	 * 
	 * @Title: getRandomIntArr
	 * 
	 * @Description: TODO(创建需要排序的数组:这里使用容器的概念，而不是简单的判断加入)
	 * 
	 * @param @param n
	 * @param @return 设定文件
	 * 
	 * @return int[] 返回类型
	 * 
	 * @throws
	 */
	public static int[] getRandomIntArr(int n) {

		// 得到两个容器
		int[] arrayA = new int[n];
		int[] arrayB = new int[n];

		// 填充arrayA为顺序的1,2,3,...n
		for (int i = 0; i < arrayA.length; i++) {
			arrayA[i] = i + 1;
		}

		Random r = new Random();
		int end = n - 1;
		for (int j = 0; j < n; j++) {

			int ranIndex = r.nextInt(end + 1);
			arrayB[j] = arrayA[ranIndex];
			// 将最后一个数覆盖得到的随机数，然后删除掉最后那个数
			arrayA[ranIndex] = arrayA[end];
			end--;
		}
		return arrayB;
	}

	/**
	 * 
	 * 
	 * @Title: showArr
	 * 
	 * @Description: TODO(显示打印数组)
	 * 
	 * @param @param arr 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public static void showArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				System.out.print(arr[i]);
				System.out.print(" ");
			} else {
				System.out.print(arr[i]);
			}
		}
		System.out.println();
	}
}
