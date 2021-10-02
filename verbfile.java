import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class verbfile {
    //我们希望在实现上面的各种功能的时候，有一个选项， 就是把动词的各种变形都归为它的原型来统计。
    //具体步骤把一篇文章里面的所有的变形单词用正则表达式替换为第一个单词的样式
    public static String[] changewords()throws Exception{
        //得到所有一样的单词
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\29469\\Desktop\\workspace\\filecount\\src\\main\\resources\\verbfile.txt"));

        StringBuffer sb = new StringBuffer();
        String text =null;
        while ((text=br.readLine())!= null){
            sb.append(text);// 将读取出的字符追加到stringbuffer中
        }
        br.close();  // 关闭读入流

        String str = sb.toString().toLowerCase(); // 将stringBuffer转为字符并转换为小写
        String[] words = str.split("[^((a-zA-Z)\\s)]+");  // 非单词的字符来分割，得到所有单词
        return words;
    }
    public static void main(String args[])throws  Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要输入的前几个单词的最高输出频率:");
        int n=scanner.nextInt();
        verbfile v=new verbfile();
        String[] getwords=v.changewords();//得到了一样的词语
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\29469\\Desktop\\workspace\\filecount\\src\\main\\resources\\hu.txt"));

        StringBuffer sb = new StringBuffer();
        String text =null;
        while ((text=br.readLine())!= null){
            sb.append(text);// 将读取出的字符追加到stringbuffer中
        }
        br.close();  // 关闭读入流

        String str = sb.toString().toLowerCase(); // 将stringBuffer转为字符并转换为小写
        String[] words = str.split("[^(a-zA-Z)]+");  // 非单词的字符来分割，得到所有单词
        Map<String ,Integer> map = new HashMap<String, Integer>() ;
        Integer[] count=new Integer[getwords.length];
        for(int j=0;j<getwords.length;j++){
            count[j]=0;
        }
        for(String word :words){
            if(map.get(word)==null){  // 若不存在说明是第一次，则加入到map,出现次数为1
                int p=0;
                for(int j=0;j<getwords.length;j++){
                    if(getwords[j].indexOf(word)!=-1){//是这个的字串
                        System.out.println(word);
                        p=1;
                        count[j]++;
                        map.put(getwords[j],count[j]);
                        j=getwords.length;
                    }
                }
                if(p==0){//第一次出现且没有字串
                    map.put(word,1);
                }
            }else{
                map.put(word,map.get(word)+1);  // 若存在，次数累加1
            }
        }
        System.out.println(map);
        // 排序
        List<Map.Entry<String ,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());

        Comparator<Map.Entry<String,Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
                int i=left.getValue()-right.getValue();//当出现次数相同时，按照字典顺序排序
                if(i==0) {
                    return (right.getKey().compareTo(left.getKey()));
                }
                return (left.getValue().compareTo(right.getValue()));
            }
        };
        // 集合默认升序升序
        Collections.sort(list,comparator);
        for(int i=0;i<n;i++){// 由高到低输出
            System.out.println(list.get(list.size()-i-1).getKey() +":"+list.get(list.size()-i-1).getValue());
        }
    }
}
