package Tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetData {
	public static void main(String[] args) throws IOException {
		List<String[]> data = new ArrayList();
		 
        try {
            Scanner in = new Scanner(new File("file/train.txt"));
 
            while (in.hasNextLine()) {
                String str = in.nextLine();
 
                data.add(str.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        // list转化为数组
        String[][] result = data.toArray(new String[][] {});
 
        for (String[] strings : result) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

}


