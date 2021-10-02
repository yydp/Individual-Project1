import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class partfile {
    File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public static void main(String [] args) {
        partfile p=new partfile();
        File file=new File("C:\\Users\\29469\\Desktop\\workspace\\filecount\\src\\main\\resources\\hu.txt");
        try {
            p.listword(file);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void listword(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));

        StringBuffer sb = new StringBuffer();
        String text = null;
        while ((text = br.readLine()) != null) {
            sb.append(text);// 将读取出的字符追加到stringbuffer中
        }
        br.close();  // 关闭读入流

        String str = sb.toString().toLowerCase(); // 将stringBuffer转为字符并转换为小写
        //得到里面所有的字母
        String words=str.replaceAll("[^(a-zA-Z)]+","");
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < words.length(); i++) {
            Character c = words.charAt(i);//返回指定处的字母
            if(map.get(c)==null){
                map.put(c,1);// 若不存在说明是第一次，则加入到map,出现次数为1
            }else{
                map.put(c,map.get(c)+1);//存在在基础上加1
            }
        }
        //排序
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());

        Comparator<Map.Entry<Character, Integer>> comparator = new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> left, Map.Entry<Character, Integer> right) {
                int i=left.getValue()-right.getValue();
                if(i==0) {
                    return (right.getKey().compareTo(left.getKey()));
                }
                return (left.getValue().compareTo(right.getValue()));
            }
        };
        // 集合默认升序升序
        Collections.sort(list,comparator);
        //输出结果
        for(int i=0;i<map.size();i++){// 由高到低输出
            System.out.println(list.get(list.size()-i-1).getKey() +":"+String.format("%.2f",list.get(list.size()-i-1).getValue()*1.0/words.length()));
        }

    }
}
