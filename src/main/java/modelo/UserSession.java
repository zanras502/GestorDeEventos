
package modelo;

public class UserSession {
    private static UserSession instance;

    private String userId;
    private String userName;

    private UserSession(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public static UserSession getInstace(String userId, String userName) {
        if(instance == null) {
            instance = new UserSession(userId, userName);
        }
        return instance;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void cleanUserSession() {
        userId = "";
        userName = "";
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
