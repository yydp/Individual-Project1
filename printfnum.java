public class printfnum {
    //写一个命令行程序， 输入1~20000内的所有素数，按每行5个打印出来，并分析程序中最费时的函数是什么， 如何改进？
    //判断那个是素数
    public static int listnum(int n){
        if(n==1){
            return 0;
        }
        if(n==2){
            return 1;
        }
        for(int i=2;i<n-1;i++){
            if(n%i==0){
                return 0;
            }
        }
        return 1;
    }
    public static void main(String args[]){
        printfnum p=new printfnum();
        for(int i=1;i<=20000;i++){

            if(p.listnum(i)==0){
                System.out.println("是素数");
            }else{
                System.out.println("不是素数");
            }
        }
    }
}
