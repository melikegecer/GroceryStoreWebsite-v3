/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.unil.doplab.grocerystorewebsite.v3.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "FOODS")
@NamedQueries({
    @NamedQuery(name = "Foods.findAll", query = "SELECT f FROM Foods f"),
    @NamedQuery(name = "Foods.findByFoodId", query = "SELECT f FROM Foods f WHERE f.foodId = :foodId"),
    @NamedQuery(name = "Foods.findByFoodName", query = "SELECT f FROM Foods f WHERE f.foodName = :foodName"),
    @NamedQuery(name = "Foods.findByFoodPrice", query = "SELECT f FROM Foods f WHERE f.foodPrice = :foodPrice"),
    @NamedQuery(name = "Foods.findByIngredients", query = "SELECT f FROM Foods f WHERE f.ingredients = :ingredients")})
public class Foods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FOOD_ID")
    private Integer foodId;
    @Column(name = "FOOD_NAME")
    private String foodName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FOOD_PRICE")
    private Double foodPrice;
    @Column(name = "INGREDIENTS")
    private String ingredients;
    @ManyToMany(mappedBy = "foodsList")
    private List<Users> usersList;

    public Foods() {
    }

    public Foods(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodId != null ? foodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foods)) {
            return false;
        }
        Foods other = (Foods) object;
        if ((this.foodId == null && other.foodId != null) || (this.foodId != null && !this.foodId.equals(other.foodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Food{"
                + "\nName=" + foodName
                + "\nPrice=" + foodPrice
                + "\nIngredients=" + ingredients;
    }

}
