package cn.luckyvv.blog.entity;

public class Login {
    private Integer id;

    private String user;

    private String password;

    private Integer typeId;

    public Login(Integer id, String user, String password, Integer typeId) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.typeId = typeId;
    }

    public Login() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}