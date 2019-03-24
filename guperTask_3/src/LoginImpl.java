public class LoginImpl implements Login {

    @Override
    public void login(User user) {
        user.age=22;
        user.name="xiao ming";
        user.address=new Address("beijing","海淀区","花园东路");

    }


}
