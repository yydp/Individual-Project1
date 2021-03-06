import java.io.File;
import java.util.List;

public class getfile {
    public static void main(String args[]){
        //读取文件下所有的目录放在一个lisarray这个数组里面
        File file=new File("D:/");
        File[] files=file.listFiles();
        readFiles(files);
    }
    //得到所有的目录
    public static File[] listFile(){//是文件夹的基础上读取所有的文件
        return listFile();
    }
    //读取所有的目录
    public static void readFiles(File[] files){
        if(files==null){
            return;
        }
        else{
            for(File file:files){
                if(file.isFile()){//如果是文件
                    wordfile w=new wordfile();
                    try {
                        w.wordlist(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                  }
                }else if(file.isDirectory()){//如果是文件夹，继续读取所有的文件
                    readFiles(file.listFiles());
                }
            }
        }
    }
}
