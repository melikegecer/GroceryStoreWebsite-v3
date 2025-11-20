package ch.unil.doplab.grocerystorewebsite.v3.beans;

import ch.unil.doplab.grocerystorewebsite.v3.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v3.models.Foods;
import ch.unil.doplab.grocerystorewebsite.v3.models.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
@Named(value = "foodBean")
@SessionScoped
public class FoodBean implements Serializable {

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    private String foodName = "";

    @Transactional
    public void addFoodToShoppingCart() {
        Users user = LoginBean.getUserLoggedIn();
        try {
            Foods f = findFoodByNameInTheStore(foodName);
            user.getFoodsList().add(f);
            em.merge(user);
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.foodName = "";
    }

    @Transactional
    public void removeFoodFromShoppingCart() {
        Users user = LoginBean.getUserLoggedIn();
        try {
            if (doesFoodExistInShoppingCart(user, foodName)) {
                Foods food = findFoodByNameInShoppingCart(user, foodName);
                List uList = user.getFoodsList();
                uList.remove(food);
                em.merge(user);
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.foodName = "";
    }

    private boolean doesFoodExistInShoppingCart(Users user, String foodName) {
        try {
            return findFoodByNameInShoppingCart(user, foodName) != null;
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    private Foods findFoodByNameInTheStore(String foodName) throws DoesNotExistException {
        Query query = em.createNamedQuery("Foods.findByFoodName");
        List<Foods> foods = query.setParameter("foodName", foodName).getResultList();
        if (foods.size() > 0) {
            return foods.get(0);
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    private Foods findFoodByNameInShoppingCart(Users user, String foodName) throws DoesNotExistException {
        for (Foods food : user.getFoodsList()) {
            if (food.getFoodName().equals(foodName)) {
                return food;
            }
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    public ArrayList<Foods> getFoods() {
        Query query = em.createNamedQuery("Foods.findAll");
        return new ArrayList<>(query.getResultList());
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
