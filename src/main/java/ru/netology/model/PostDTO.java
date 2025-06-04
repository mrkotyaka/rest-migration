package ru.netology.model;

public class PostDTO {
    private long id;
    private String content;
    private boolean removed;

    public PostDTO() {
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", removed=" + removed +
                '}';
    }

    public PostDTO(long id, String content) {
        this.id = id;
        this.content = content;
        this.removed = false;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
