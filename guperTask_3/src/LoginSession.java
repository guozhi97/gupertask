public enum  LoginSession {
    INSTANCE;
    private User user;

    public User getUser() {
        return (User) user.clone();
    }

    public void setUser(User user) {
        this.user = user;
    }



}
