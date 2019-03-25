import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

public class LogFileOper implements LogFileOperApi {

    private String logFileName="file.log";

    public LogFileOper(String logFileName) {
        if (logFileName!=null){
            this.logFileName = logFileName;
        }
    }

    @Override
    public List<LogBean> readLogFile() {
        List<LogBean> list = null;
        ObjectInputStream in= null;
        //code
        return list;
    }

    @Override
    public void writeLogFile(List<LogBean> logs) {
        File file = new File(logFileName);
        ObjectOutputStream out = null;
        //code
    }
}
