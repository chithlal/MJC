package com.developer.chithlal.mjc.app.UserProfile;

class SliderItem {
    private String imageUrl;
    private String description;
    private int id;

    public SliderItem(String imageUrl, String description, int id) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
