public class Leader {
    public void dispatch(String cmd){
        if (cmd.equals("sale")){
            new SaleWorker().work();
        }else if (cmd.equals("produce")){
            new ProduceWorker().work();
        }else {
            System.out.println("no this method:"+cmd);
        }
    }
}
