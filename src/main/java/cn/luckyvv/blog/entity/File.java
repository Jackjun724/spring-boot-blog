package cn.luckyvv.blog.entity;

public class File {
    private Integer id;

    private String title;

    private String url;

    private Integer status;

    public File(Integer id, String title, String url, Integer status) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.status = status;
    }

    public File() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}