import java.util.List;

public class LogOper implements LogFileOperApi {

    private LogDBOperApi api;

    public LogOper(LogDBOperApi api){
        this.api = api;
    }

    @Override
    public List<LogBean> readLogFile() {

        return api.getLog();
    }

    @Override
    public void writeLogFile(List<LogBean> logs) {
        for (LogBean i:logs){
            api.addLog(i);
        }
    }

}
