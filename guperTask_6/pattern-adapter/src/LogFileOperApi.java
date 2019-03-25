import java.util.List;

public interface LogFileOperApi {
    public List<LogBean> readLogFile();
    public void writeLogFile(List<LogBean> logs);
}
