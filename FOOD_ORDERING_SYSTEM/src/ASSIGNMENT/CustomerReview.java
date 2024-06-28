/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

/**
 *
 * @author Athelene
 */
public class CustomerReview 
{

    static void add(CustomerReview customerReview) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String customerName;
    private String review;

    public CustomerReview(String customerName, String review) 
    {
        this.customerName = customerName;
        this.review = review;
    }

    public String toString() 
    {
        return "CustomerReview{customerName='" + customerName + "', review='" + review + "'}";
    }
}
