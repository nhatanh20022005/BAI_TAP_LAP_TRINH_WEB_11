package vn.bt10.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Form backing object for creating or editing categories.  Validation
 * annotations ensure the name field is supplied.
 */
public class CategoryForm {
    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;
    private String images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}