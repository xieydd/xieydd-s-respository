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
 */
/**
 * 

* @ClassName: Sort 

* @Description: TODO(ʵ��һЩ�����㷨�����򵥵Ĳ����ٶ�) 

* @author xieydd xieydd@gmail.com  

* @date 2017-8-4 ����3:06:17 

*
 */
public class Sort {
	public static void main(String[] args) {
		int[] src = getRandomIntArr(100000);
		long startTimes = System.currentTimeMillis();

		//insertSort(src);//100000�������򻨷�ʱ��4��
		//shellSort(src);//100000�������򻨷�ʱ��1��
		selectSort(src);//100000�������򻨷�ʱ��4��
		
		long endTimes = System.currentTimeMillis();
		long times = endTimes - startTimes;
		//SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String datas = data.format(new Date(times));
		System.out.println(src.length+"�������򻨷�ʱ��"+times/1000+"��");
	}

	
	/**
	 *  
	
	* @Title: selectSort 
	
	* @Description: TODO(��ѡ������) 
	
	* @param @param src    �趨�ļ� 
	
	* @return void    �������� 
	
	* @throws
	 */
	public static void selectSort(int[] src) {
		
		int position=0;
		for(int i =0;i<src.length;i++) {
			int temp = src[i];
			position = i;
			int j = i+1;
			for(;j<src.length;j++) {
				if(temp>src[j]){
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
	
	* @Title: shellSort 
	
	* @Description: TODO(ϣ��������С��������ʵ�����Ƿ������ķ���) 
	
	* @param @param src    �趨�ļ� 
	
	* @return void    �������� 
	
	* @throws
	 */
	public static void shellSort(int[] src) {

		int d = (int) Math.ceil(src.length / 2);
		int temp;
		
		while (d>0) {
			for(int i = d;i<src.length;i++) {
				
				temp = src[i];
				int j = i - d;
				while(j>=0&&temp<src[j]) {
					src[j+d] = src[j];
					j = j -d;
				}
				src[j+d] = temp;
				//System.out.println("ϣ�����򣺵�" + i + "������Ϊ��");
				//showArr(src);
			}
			 d = d/2;
		}
		showArr(src);
	}

	/**
	 * 
	
	* @Title: insertSort 
	
	* @Description: TODO(�򵥲����㷨 ע��forѭ��������������j���趨 ȡ�����ݺ�ǰ�����е���Ƚϳ���С�ڵļ��������ִ��ڵĻ���ֵ��ָ����λ��) 
	
	* @param @param src    �趨�ļ� 
	
	* @return void    �������� 
	
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
			//System.out.println("�������򣺵�" + i + "������Ϊ��");
			//showArr(src);
		}
		showArr(src);
	}

	
	/*
	 * public static int[] getIntArr() { int[] a = { 9, 5, 3, 7, 1, 2, 4, 6, 8,
	 * 13, 78, 45, 90, 101, 111, 34, 43 }; return a; }
	 */
	/*��Ȼ�ǿ���ȡ������������ţ�����Ҫ�ж����Ƿ������棬�����������Ļ���ǳ�ռ�ڴ棬�Ľ�(���Եõ�����Ϊn��������ֵΪ1��n�Ҳ����ظ�)
	���ֺô���,�������ȡ��Χ����С,���Ҷž��˴�����ʱ����ִ��ɾ������ʱ������ƿ��*/
	/**
	 * 
	
	* @Title: getRandomIntArr 
	
	* @Description: TODO(������Ҫ���������:����ʹ�������ĸ�������Ǽ򵥵��жϼ���) 
	
	* @param @param n
	* @param @return    �趨�ļ� 
	
	* @return int[]    �������� 
	
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
	
	* @Title: showArr 
	
	* @Description: TODO(��ʾ��ӡ����) 
	
	* @param @param arr    �趨�ļ� 
	
	* @return void    �������� 
	
	* @throws
	 */
	public static void showArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			System.out.print(", ");
		}
		System.out.println();
	}
}
