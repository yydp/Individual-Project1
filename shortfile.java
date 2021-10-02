import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class shortfile {
    //参数 <number> 说明要输出多少个词的短语，并按照出现频率排列。同一频率的词组， 按照字典序来排列。
    public static void main(String args[]){
        shortfile s=new shortfile();
        try {
            s.listwords();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void listwords()throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("要输出多少个词的短语:");
        int n=scanner.nextInt();
        //输出几个词的短语就是中间有n-1个词的短语

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\29469\\Desktop\\workspace\\filecount\\src\\main\\resources\\shortwords.txt"));

        StringBuffer sb = new StringBuffer();
        String text =null;
        while ((text=br.readLine())!= null){
            sb.append(text);// 将读取出的字符追加到stringbuffer中
        }
        br.close();  // 关闭读入流

        String str = sb.toString().toLowerCase(); // 将stringBuffer转为字符并转换为小写
        String[] targetwords = str.split("[^((a-zA-Z)\\s)]+");  // 非单词的字符来分割，得到所有单词
        //得到中间有几个空格的单词
        String[] dotwords;
        String[] words=new String[targetwords.length];
        int p=0;
        for(int i=0;i<targetwords.length;i++){
            dotwords=targetwords[i].split("[^((a-zA-Z))]+");
            if(dotwords.length==n){
                words[p++]=targetwords[i];
            }
        }
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

        for(int i=0;i<list.size();i++){// 由高到低输出
            if(list.get(list.size()-i-1).getKey()!=null){
                System.out.println(list.get(list.size()-i-1).getKey() +":"+list.get(list.size()-i-1).getValue());
            }

        }
    }
}
