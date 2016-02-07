package inst;

import java.util.Date;

public class Transaction extends Trans {

    private int idCustomer;
    private int idProduct;
    private Date date;
    private double count;
    private double price;
    public double discount;

    public Transaction() {

    }

    public Transaction(int idCustomer, int idProduct, Date date, double count) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.date = date;
        this.count = count;
    }

    public Transaction(int idCustomer, int idProduct, Date date, double count, double price, double discount) {
        this(idCustomer, idProduct, date, count);
        this.price = price;
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount() {
        double disc = 0;

        double sum = price * count;
        if (sum >= 50 && sum < 100){
            disc = sum * 0.05;
        }else if (sum >= 100){
            disc = sum * 0.1;
        }

        this.discount = disc;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private void abc(){

    }

    @Override
    public String toString() {
        return getIdProduct() + " " + getIdCustomer() + " " + getDate();
    }
}
