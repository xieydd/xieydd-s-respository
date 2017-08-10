package Algorithms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
 * 
 1.ѡ�����򣺲��ȶ���ʱ�临�Ӷ� O(n^2)
 ѡ������Ļ���˼���ǶԴ�����ļ�¼���н���n-1��Ĵ�����i�鴦���ǽ�L[i..n]����С����L[i]����λ�á�����������i�鴦��֮��ǰi����¼��λ���Ѿ�����ȷ���ˡ� 
 2.���������ȶ���ʱ�临�Ӷ� O(n^2)
 ��������Ļ���˼���ǣ�����i-1�鴦���,L[1..i-1]���ź��򡣵�i�鴦�����L[i]����L[1..i-1]���ʵ�λ�ã�ʹ��L[1..i] �����ź�������С�Ҫ�ﵽ���Ŀ�ģ����ǿ�����˳��Ƚϵķ��������ȱȽ�L[i]��L[i-1]�����L[i-1]�� L[i]����L[1..i]���ź��򣬵�i�鴦��ͽ����ˣ����򽻻�L[i]��L[i-1]��λ�ã������Ƚ�L[i-1]��L[i-2]��ֱ���ҵ�ĳһ��λ��j(1��j��i-1)��ʹ��L[j] ��L[j+1]ʱΪֹ��ͼ1��ʾ�˶�4��Ԫ�ؽ��в�������Ĺ��̣�����Ҫ(a),(b),(c)���β��롣
 3.ð�������ȶ���ʱ�临�Ӷ� O(n^2)
 ð�����򷽷�����򵥵����򷽷������ַ����Ļ���˼���ǣ����������Ԫ�ؿ������������еġ����ݡ�����С��Ԫ�رȽ��ᣬ�Ӷ�Ҫ���ϸ�����ð�������㷨������Ҫ����������ݡ����д������ɱ顣��νһ�鴦�������Ե����ϼ��һ��������У���ʱ��ע���������ڵ�Ԫ�ص�˳���Ƿ���ȷ�����������������Ԫ�ص�˳�򲻶ԣ������ᡱ��Ԫ�������棬�ͽ������ǵ�λ�á���Ȼ������һ��֮�󣬡����ᡱ��Ԫ�ؾ͸��������λ�ã��������֮�󣬡����ᡱ��Ԫ�ؾ͸����˴θ�λ�á������ڶ��鴦��ʱ���������λ���ϵ�Ԫ�����ǡ����ᡱԪ�أ����Բ��ؼ�顣һ��أ���i�鴦��ʱ�����ؼ���i��λ�����ϵ�Ԫ�أ���Ϊ����ǰ��i-1��Ĵ�����������ȷ���ź��� 
 4.�����򣺲��ȶ���ʱ�临�Ӷ� O(nlog n)
 ��������һ������ѡ����������������У���A[n]��������ȫ��������˳��洢�ṹ��������ȫ��������˫�׽��ͺ��ӽ��֮������ڹ�ϵ��ѡ����С��Ԫ�ء� 
 5.�鲢�����ȶ���ʱ�临�Ӷ� O(nlog n)
 �������������������д洢��ͬһ���������ڵ�λ���ϣ�������ΪA[l..m]��A[m+1..h]�������ǹ鲢Ϊһ���������У����洢��A[l..h]�� 
 6.�������򣺲��ȶ���ʱ�临�Ӷ� ������ O(nlogn) ���ʱ��O(n^2)
 ���������Ƕ�ð�������һ�ֱ��ʸĽ������Ļ���˼����ͨ��һ��ɨ���ʹ���������еĳ����ܴ���ȵؼ��١���ð�������У�һ��ɨ��ֻ��ȷ�������ֵ�����Ƶ���ȷλ�ã������������еĳ��ȿ���ֻ����1����������ͨ��һ��ɨ�裬����ȷ��ĳ����������Ϊ��׼��ɣ�����߸���������С���ұ߸�����������Ȼ������ͬ���ķ����������������ߵ�����ֱ����׼�������ֻ��һ��Ԫ��Ϊֹ��
 7.ϣ�����򣺲��ȶ���ʱ�临�Ӷ� ƽ��ʱ�� O(nlogn) ���ʱ��O(n^s) 1<s<2
 ��ֱ�Ӳ��������㷨�У�ÿ�β���һ������ʹ��������ֻ����1���ڵ㣬���ҶԲ�����һ����û���ṩ�κΰ���������Ƚ������Զ���루��Ϊ ������������ʹ�����ƶ�ʱ�ܿ�����Ԫ�أ������һ�αȽϾͿ����������Ԫ�ؽ�����D.L.shell��1959�����������������������㷨��ʵ������һ˼�롣�㷨�Ƚ�Ҫ�����һ������ĳ������d�ֳ������飬ÿ���м�¼���±����d.��ÿ����ȫ��Ԫ�ؽ�������Ȼ������һ����С�������������У���ÿ�����ٽ������򡣵���������1ʱ������Ҫ����������ֳ�һ�飬������ɡ�
 8.���������ȶ� ��ʱ�临�Ӷ�ΪO(nlog(r)m)rΪ��ȡ�Ļ�����mΪ�������ǱȽ������������㷨������������λ���и�ɲ�ͬ�����֣����������Ҳ�������ַ����������ֺ�ʱ��
 �����д��Ƚ���ֵ(������)ͳһΪͬ������λ����,��λ�϶̵���ǰ�油��. Ȼ��, �����λ��ʼ, ���ν���һ������.���������λ����һֱ�����λ��������Ժ�, ���оͱ��һ����������.��������ķ�ʽ���Բ���LSD��Least significantdigital����MSD��Most significantdigital����LSD������ʽ�ɼ�ֵ�����ұ߿�ʼ���ʺϵ�λ������MSD���෴���ɼ�ֵ������߿�ʼ�ʺϸ�λ��
 */
/**
 * 
 * 
 * @ClassName: Sort
 * 
 * @Description: TODO(ʵ��һЩ�����㷨�����򵥵Ĳ����ٶ�)
 * 
 * @author xieydd xieydd@gmail.com
 * 
 * @date 2017-8-4 ����3:06:17
 * 
 * 
 */
public class Sort {
	public static void main(String[] args) {
		int[] src = getRandomIntArr(1000000);
		long startTimes = System.currentTimeMillis();

		// insertSort(src);//100000�������򻨷�ʱ��4��
		// shellSort(src);// 100000�������򻨷�ʱ��1��
		// selectSort(src);//100000�������򻨷�ʱ��4��
		//bubbleSort(src);// 100000�������򻨷�ʱ��21��
		//quickSort(src, 0, src.length-1);//10000000�������򻨷�ʱ��1��
		//mergingSort(src, 0, src.length - 1);//100000�������򻨷�ʱ��5��
		radixSort(src,src.length);//1000000�������򻨷�ʱ��17��
		
		long endTimes = System.currentTimeMillis();
		long times = endTimes - startTimes;
		// SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String datas = data.format(new Date(times));
		System.out.println(src.length + "�������򻨷�ʱ��" + times / 1000 + "��");
	}
	
	public static void radixSort(int[] src,int d){
		
		//����λ����Ӧ����
		int n =1;
		//����ÿһλ�����Ľ����������һ�ε����������
		int k =0;
		
		//����ÿһ�������Ľ������һλ����������ͬ�����ַ���ͬһ��Ͱ��
		int[][] bucket = new int[10][src.length];
		//���ڱ���ÿһ��Ͱ���ж��ٸ�����
		int[] order = new int[src.length];
		
		
		while(n<d){
			for(int num:src) {//�������е����ݷŵ���Ӧ��Ͱ��
				int digit = (num/n)%10;
				bucket[digit][order[digit]] = num;
				order[digit]++;//��λ��Ϊdigit�ĸ���
			}
			
			for(int i =0;i<10;i++) {//��ǰһ��ѭ�����ɵ�Ͱ�����ݸ��ǵ�ԭ�����б�����һλ����Ľ��
				if(order[i]!=0) {//ֻҪͰ��������
					for(int j =0;j<order[i];j++) {
						src[k] = bucket[i][j];
						k++;
					}
				}
				order[i]=0;//��Ͱ��ļ�����0��������һλ������
			}
			n*=10;
			k=0;//��k��0Ϊ��һ�ֱ���λ������,��Ϊÿһ�ζ��������е�
		}
		showArr(src);
	} 
	
	
	
	
	
	/**
	 * 
	 * 
	 * @Title: mergingSort
	 * 
	 * @Description: TODO(�鲢����)�����������������ϣ������ϲ���һ���µ������ ���Ѵ��������з�Ϊ���ɸ������У�ÿ��������������ġ�Ȼ���ٰ����������кϲ�Ϊ������������
	 * 
	 * @param @param src
	 * @param @param left
	 * @param @param right �趨�ļ�
	 * 
	 * @return void ��������
	 * 
	 * @throws
	 */
	public static void mergingSort(int[] src, int left, int right) {
		if (left >= right) {
			return;
		}
		//System.out.println(left + "," + right);
		int center = (left + right) / 2;
		//���������еݹ�
		mergingSort(src, left, center);
		//�ұ�������еݹ�
		mergingSort(src, center + 1, right);
		//���Һϲ�
		merge(src, left, right, center);
		//showArr(src);
	}
	/**
	 * 
	* @Title: merge 
	
	* @Description: TODO �鲢�����н��е��ã�(�鲢:�鲢ǰ�����������Լ�����)
	
	* @param @param src
	* @param @param left
	* @param @param right
	* @param @param center    �趨�ļ� 
	
	* @return void    �������� 
	
	* @throws
	 */
	public static void merge(int[] data, int left, int right, int center) {
		System.out.println(left + "," + center + "," + right);

		int[] tmpArr = new int[data.length];
		//��ָ��
		int mid = center + 1;
		//��¼��ʱ��������� 
		int third = left;
		//��ָ��
		int tmp = left;
		
		while (left <= center && mid <= right) {
			//����С�����Ƶ���������
			if (data[left] <= data[mid]) {
				tmpArr[third++] = data[left++];
			} else {
				tmpArr[third++] = data[mid++];
			}
		}
		//��ʣ��Ĳ������η�����ʱ����
		//���ұ�ʣ�������������
		while (mid <= right) {
			tmpArr[third++] = data[mid++];
		}
		//�����ʣ�������������
		while (left <= center) {
			tmpArr[third++] = data[left++];
		}
		//�������鸲��ԭ����(left��right�����ݱ����Ƶ�ԭ����)
		while (tmp <= right) {
			data[tmp] = tmpArr[tmp++];
		}
	}
	/**
	 * 
	 * 
	 * @Title: quickSort
	 * 
	 * @Description: TODO(��������)
	 * 
	 * @param @param src
	 * @param @param low
	 * @param @param high �趨�ļ�
	 * 
	 * @return void ��������
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
		int tmp = list[low];// ѡ��һ����Ϊ����
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
		list[low] = tmp;// ѭ�������ǽ�ԭ���趨�ĵ�һ�����ŵ�����λ
		return low;// �õ���λ��
	}

	/**
	 * 
	 * 
	 * @Title: bubbleSort
	 * 
	 * @Description: TODO(ð�������㷨)
	 * 
	 * @param @param src �趨�ļ�
	 * 
	 * @return void ��������
	 * 
	 * @throws
	 */
	public static void bubbleSort(int[] src) {
		int temp = 0;
		for (int i = 0; i < src.length - 1; i++) {
			for (int j = 0; j < src.length - 1 - i; j++) {// ע�⽫���ķŵ�����Ͳ�����
				if (src[j] > src[j + 1]) {
					// ��ź�С��ǰ
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
	 * @Description: TODO(��ѡ������)
	 * 
	 * @param @param src �趨�ļ�
	 * 
	 * @return void ��������
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
				if (temp > src[j]) {// ����С��ѡ��������ǵ���λ��
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
	 * @Description: TODO(ϣ��������С��������ʵ�����Ƿ������ķ���)
	 * 
	 * @param @param src �趨�ļ�
	 * 
	 * @return void ��������
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
	 * @Description: TODO(�򵥲����㷨 ע��forѭ��������������j���趨
	 *               ȡ�����ݺ�ǰ�����е���Ƚϳ���С�ڵļ��������ִ��ڵĻ���ֵ��ָ����λ��)
	 * 
	 * @param @param src �趨�ļ�
	 * 
	 * @return void ��������
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
	 * ��Ȼ�ǿ���ȡ������������ţ�����Ҫ�ж����Ƿ������棬�����������Ļ���ǳ�ռ�ڴ棬�Ľ�(���Եõ�����Ϊn��������ֵΪ1��n�Ҳ����ظ�)
	 * ���ֺô���,�������ȡ��Χ����С,���Ҷž��˴�����ʱ����ִ��ɾ������ʱ������ƿ��
	 */
	/**
	 * 
	 * 
	 * @Title: getRandomIntArr
	 * 
	 * @Description: TODO(������Ҫ���������:����ʹ�������ĸ�������Ǽ򵥵��жϼ���)
	 * 
	 * @param @param n
	 * @param @return �趨�ļ�
	 * 
	 * @return int[] ��������
	 * 
	 * @throws
	 */
	public static int[] getRandomIntArr(int n) {

		// �õ���������
		int[] arrayA = new int[n];
		int[] arrayB = new int[n];

		// ���arrayAΪ˳���1,2,3,...n
		for (int i = 0; i < arrayA.length; i++) {
			arrayA[i] = i + 1;
		}

		Random r = new Random();
		int end = n - 1;
		for (int j = 0; j < n; j++) {

			int ranIndex = r.nextInt(end + 1);
			arrayB[j] = arrayA[ranIndex];
			// �����һ�������ǵõ����������Ȼ��ɾ��������Ǹ���
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
	 * @Description: TODO(��ʾ��ӡ����)
	 * 
	 * @param @param arr �趨�ļ�
	 * 
	 * @return void ��������
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
