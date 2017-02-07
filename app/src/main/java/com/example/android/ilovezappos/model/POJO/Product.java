package com.example.android.ilovezappos.model.POJO;

import java.io.Serializable;

/**
 * Created by jennifernghinguyen on 2/3/17.
 */

public class Product implements Serializable {
    private String brandName;
    private String thumbnailImageUrl;
    private String originalPrice;
    private String price;
    private String percentOff;
    private String productUrl;
    private String productName;

    /**
     * constructor
     *
     * @param brandName
     * @param thumbnailImageUrl
     * @param originalPrice
     * @param price
     * @param percentOff
     * @param productUrl
     * @param productName
     */
    public Product(String brandName,
                   String thumbnailImageUrl,
                   String originalPrice,
                   String price,
                   String percentOff,
                   String productUrl,
                   String productName) {

        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.originalPrice = originalPrice;
        this.price = price;
        this.percentOff = percentOff;
        this.productUrl = productUrl;
        this.productName = productName;

    }

    /**
     * getter
     *
     * @return brandName
     */
    public String getBrandName() {
        return this.brandName;
    }

    /**
     * getter
     *
     * @return thumbnailImageUrl
     */
    public String getThumbnailImageUrl() {
        return this.thumbnailImageUrl;
    }

    /**
     * getter
     * @return productId
     */
    /*public int getProductId() {
        return this.productId;
    }*/

    /**
     * getter
     *
     * @return originalPrice
     */
    public String getOriginalPrice() {
        return this.originalPrice;
    }

    /**
     * getter
     * @return styleId
     */
    /*public int getStyleId() {
        return this.styleId;
    }*/

    /**
     * getter
     * @return colorId
     */
    /*public int getColorId() {
        return this.colorId;
    }*/

    /**
     * getter
     *
     * @return price
     */
    public String getPrice() {
        return this.price;
    }

    /**
     * getter
     *
     * @return percentOff
     */
    public String getPercentOff() {
        return this.percentOff;
    }

    /**
     * getter
     *
     * @return productUrl
     */
    public String getProductUrl() {
        return this.productUrl;
    }

    /**
     * getter
     *
     * @return productName
     */
    public String getProductName() {
        return this.productName;
    }

    public boolean hasDiscount(){
        if(!this.percentOff.equals("0%") && !this.percentOff.equals("") && this.percentOff !=null){
            return true;
        }else{
            return false;
        }
    }
}
