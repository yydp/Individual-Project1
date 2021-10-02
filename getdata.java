import java.util.Scanner;

public class getdata {
    //练习数值计算。找出一个整数数组中子数组之和的最大值，例如：数组[1, -2, 3, 5, -1]，
    // 返回8（因为符合要求的子数组是 [3, 5]）；数组[1, -2, 3, -8, 5, 1]，返回6（因为符合要求的子数组是 [5, 1]）;
    // 数组[1, -2, 3,-2, 5, 1]，返回7（因为符合要求的子数组是 [3, -2, 5, 1]）。
    public static void main(String args[]){
        int n;//数组有几个数
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        int[] num=new int[n];
        for(int i=0;i<n;i++){
            num[i]=scanner.nextInt();
        }
        int maxnum=0;//记录最大值
        int k=0;//结束位置
        int s=0;//起始位置
        for(int i=0;i<n-1;i++){//开始的指针
            int max=num[i];//1
            for(int j=i+1;j<n;j++){//结束的指针
                if(max>max+num[j]){
                    if(maxnum<max){
                        maxnum=max;//1
                        k=j-1;//最后指针的位置
                        s=i;
                    }
                }else{
                    if(maxnum<max+num[j]){
                        maxnum=max+num[j];//2 7
                        k=j;//3
                        s=i;
                    }
                }
                max+=num[j];//-1
            }
        }
        System.out.println("起始位置："+num[s]+"结束位置："+num[k]);
        System.out.println(maxnum);
    }
}
