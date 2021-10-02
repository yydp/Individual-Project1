import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class shopword {
    //跳过文件里面的一些词
    //遇到"a",  "it", "the", "and", "this"

    public static String[]  listwords() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\29469\\Desktop\\workspace\\filecount\\src\\main\\resources\\stopwords.txt"));

        StringBuffer sb = new StringBuffer();
        String text =null;
        while ((text=br.readLine())!= null){
            sb.append(text);// 将读取出的字符追加到stringbuffer中
        }
        br.close();  // 关闭读入流

        String str = sb.toString().toLowerCase(); // 将stringBuffer转为字符并转换为小写
        String[] word = str.split("[^(a-zA-Z)]+");  // 非单词的字符来分割，得到所有单词
        return word;
    }
    public static void main(String args[]) throws Exception{
        shopword s=new shopword();
        String[] pastwords=s.listwords();
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要输入的前几个单词的最高输出频率:");
        int n=scanner.nextInt();

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

        for(String word :words){
            int dot=0;
            for(int i=0;i<pastwords.length;i++){
                if(pastwords[i].equals(word)){//说明出现了了常出现的词语
                    dot=1;
                }
            }

            if(map.get(word)==null&&dot==0){  // 若不存在说明是第一次，则加入到map,出现次数为1
                map.put(word,1);
            }else if(dot==0){
                map.put(word,map.get(word)+1);  // 若存在，次数累加1
            }
        }

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
