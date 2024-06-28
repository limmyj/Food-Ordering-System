/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;
import java.time.LocalDateTime;

/**
 *
 * @author Athelene
 */
interface Notification 
{
    static void generateAndSendReceipt(String id, int amount) 
    {
        File_Operation.generateReceipt(id, amount);
    }
}


    
    
