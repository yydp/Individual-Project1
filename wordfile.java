
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

//统计单词出现最高
public class wordfile {

    public static void main(String [] args)throws Exception  {
        wordfile w=new wordfile();
        File file=new File("C:\\Users\\29469\\Desktop\\workspace\\filecount\\src\\main\\resources\\hu.txt");
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要输入的前几个单词的最高输出频率:");
        int n=scanner.nextInt();
        List<Map.Entry<String ,Integer>> list = w.wordlist(file);
        for(int i=0;i<n;i++){// 由高到低输出
            System.out.println(list.get(list.size()-i-1).getKey() +":"+list.get(list.size()-i-1).getValue());
        }


    }

    public static List wordlist(File file) throws Exception{


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
            if(map.get(word)==null){  // 若不存在说明是第一次，则加入到map,出现次数为1
                map.put(word,1);
            }else{
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
        return list;
    }

}
