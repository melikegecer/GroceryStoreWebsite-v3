package ch.unil.doplab.grocerystorewebsite.v3.beans;

import ch.unil.doplab.grocerystorewebsite.v3.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v3.models.Users;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    private static Users currentUser;
    private String username = "";
    private String password = "";

    public String userLogsIn() {
        try {
            Users user = findByUsername();
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "/MainPage/LoginPage.xhtml?faces-redirect=true";
    }

    protected Users findByUsername() throws DoesNotExistException {
        Query query = em.createNamedQuery("Users.findByUsername", Users.class);
        List<Users> users = query.setParameter("username", username).getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

    public String userLogsout() {
        currentUser = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    public static Users getUserLoggedIn() {
        return currentUser;
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
