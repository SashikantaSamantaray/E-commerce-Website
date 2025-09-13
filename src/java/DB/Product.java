package DB;

public class Product {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean featured;
    private String categoryName; 

    private String imageUrl;

public String getImageUrl() {
    return imageUrl;
}
public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
}


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isFeatured() {
        return featured;
    }
    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
