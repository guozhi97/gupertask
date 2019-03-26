import java.util.Observable;

public class   GPer extends Observable {
    private static GPer gPer=null;

    private GPer(){
        super();
    }

    public static GPer getInstance(){
        if (gPer==null){
            synchronized(GPer.class){
                if (gPer==null){
                    gPer = new GPer();
                }
            }
        }
        return gPer;
    }

    public boolean send(Question question){
        System.out.println(question.getUserName()+" send a message: "+question.getContent());
        setChanged();
        notifyObservers(question);
        return true;
    }

}
