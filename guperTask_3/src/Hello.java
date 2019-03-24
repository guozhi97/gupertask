public class Hello {
    public static void main(String[] args){
        User user = new User();

        Login login = new LoginImpl();
        login.login(user);
        LoginSession.INSTANCE.setUser(user);

        User user1 = LoginSession.INSTANCE.getUser();
      //  user1.address=new Address("北京市","朝阳区","大望路");
        user1.address.city="湖北";
        System.out.println("user1:"+LoginSession.INSTANCE.getUser());
        System.out.println("user2:"+user1);
    }
}
