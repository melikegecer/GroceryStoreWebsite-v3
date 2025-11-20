package ch.unil.doplab.grocerystorewebsite.v3.beans;

import ch.unil.doplab.grocerystorewebsite.v3.exceptions.AlreadyExistsException;
import ch.unil.doplab.grocerystorewebsite.v3.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v3.exceptions.InsufficientBalanceException;
import ch.unil.doplab.grocerystorewebsite.v3.models.Foods;
import ch.unil.doplab.grocerystorewebsite.v3.models.Users;
import java.io.Serializable;
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
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    private String email = "";
    private String username = "";
    private String firstName = "";
    private String lastName = "";
    private String password = "";
    private double amount = 0.0;

    @Transactional
    public void createAUser() {
        try {
            if (!emailExists() && !usernameExists()) {
                Users newUser = new Users();
                newUser.setUsername(username);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPassword(password.hashCode());
                em.persist(newUser);
            }
        } catch (AlreadyExistsException | DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.email = "";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
    }

    @Transactional
    public void increaseBalance() {
        Users user = LoginBean.getUserLoggedIn();
        user.setBalance(user.getBalance() + amount);
        em.merge(user);
        // empty value
        this.amount = 0.0;
    }

    @Transactional
    public void completeShopping() {
        Users user = LoginBean.getUserLoggedIn();
        try {
            double shoppingCartBalance = 0.0;
            for (Foods food : user.getFoodsList()) {
                shoppingCartBalance += food.getFoodPrice();
            }
            if (user.getBalance() >= shoppingCartBalance) {
                System.out.println("You bought foods=" + user.getFoodsList().toString() + ".");
                user.setBalance(user.getBalance() - shoppingCartBalance);
                user.getFoodsList().clear();
                em.merge(user);
            } else {
                throw new InsufficientBalanceException("Balance is not enough.");
            }
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean emailExists() throws AlreadyExistsException {
        Query query = em.createNamedQuery("Users.findByEmail");
        List<Users> users = query.setParameter("email", email).getResultList();
        return users.size() > 0;
    }

    private boolean usernameExists() throws DoesNotExistException {
        Query query = em.createNamedQuery("Users.findByUsername");
        List<Users> users = query.setParameter("username", username).getResultList();
        return users.size() > 0;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getAmount() {
        return amount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
