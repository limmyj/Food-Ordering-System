/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

/**
 *
 * @author Athelene
 */
abstract class User 
{ 
    // Consider turning it into abstract class
    private String id;
    private String password;
    private String types;
    File_Operation fop;
	
    public User(String Id, String pw, String t)
    {
        id = Id;
        password = pw;
        types = "";
        fop = new File_Operation();
    }
	
    public User (String Id, String pw) 
    {
        id = Id;
        password = pw;
        fop = new File_Operation();
    }
    
    public User (String id) {
        this.id = id;
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
    
    public String getTypes() 
    {
        return types;
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
        
    public void setTypes(String t) 
    {
        types = t; 
    }	
    
    public abstract void viewProfile();
}
