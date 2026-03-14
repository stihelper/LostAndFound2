package model;

public class Item {

    private int id;
    private String name;
    private String description;
    private String status;
    private String imagePath;
    private String createdAt;

    public Item(int id, String name, String description, String status, String imagePath, String createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getImagePath() { return imagePath; }
    public String getCreatedAt() { return createdAt; }

    public void setStatus(String status) {
        this.status = status;
    }

    public String display() {
        return "ID: " + id +
               "<br>Name: " + name +
               "<br>Description: " + description +
               "<br>Status: " + status +
               "<br>Date: " + (createdAt != null ? createdAt : "N/A");
    }
}
