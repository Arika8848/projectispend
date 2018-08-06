package domain;

/**
 * Created by Arika on 4/13/2018.
 */
public class Expense {
    String id;
    String name;
    String price;
    String expense_date;
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Expense(){

    }
    public Expense(String name, String price, String expense_date) {
        this.name = name;
        this.price = price;
        this.expense_date = expense_date;
    }


    public Expense(String id, String name, String price, String expense_date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expense_date = expense_date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(String expense_date) {
        this.expense_date = expense_date;
    }
}
