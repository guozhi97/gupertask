import java.util.ArrayList;
import java.util.List;

public class Hello {
    public static void main(String [] args){
 //       LogFileOperApi api = new LogFileOper("file.log");
        LogDBOperApi dbOperApi= null;
        LogFileOperApi api = new LogOper(dbOperApi);
        List<LogBean> list = new ArrayList<>();
        list.add(new LogBean());
        api.writeLogFile(list);
        List list1 = api.readLogFile();
    }
}
