/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

/**
 *
 * @author Athelene
 */
public class Vendor extends User
{
    private String id;
    private String password;
    private String email;
    private String username;
    private String contact_number;
    File_Operation fop;
	
	
    public Vendor(String Id, String pw, String em, String un, String con) 
    {
        super(Id, pw);
        id = Id;
        fop = new File_Operation();
        email = em;
        username = un;
		
    }
	
    public Vendor(String Id, String pw) 
    {
        super(Id, pw);
        email = null;
        username = null;
        contact_number = null;
        fop = new File_Operation();
    }
	
    //Getter
    public String getId() 
    {
        return id;
    }
    
    public String getPassword() 
    {
        return password;
    }
    
    public String getUsername() 
    {
        return username;
    }
    
    public String getEmail() 
    {
        return email;
    }
    
    public String getContact() 
    {
        return contact_number;
    }
	
    //Setter
    public void setId(String Id) 
    {
        id = Id;
    }
    
    public void setPassword(String pw) 
    {
        password = pw;
    }
    
    public void setUsername(String un)
    {
        username = un;
    }
    
    public void setEmail(String em)
    {
        email = em;
    }
    
    public void setContact(String cn) 
    {
        contact_number = cn;
    }	
    
    public void viewProfile() 
    {
        System.out.println("Vendor Profile Details:");
        System.out.println("ID: " + getId());
        System.out.println("Username: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("Contact Number: " + getContact());
        // Add other details as needed
    }
}
