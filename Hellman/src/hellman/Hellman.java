
package hellman;
import java.io.*;
import java.util.*;
//  a class hellman 
public class Hellman {
    // generator function 
    public int generate(int g, int a, int p)
    {
        return (g ^ a) % p;
    }
    // a function to check if a number is a prime number
    public boolean is_prime(int p)
    {
    int k = 0;  // variable to count numbers of divisor of number p
    for(int i = 2; i <= p; i++)
    {
        if (p % i == 0)
        {
            k += 1;
        }
    }
    if(k > 1) // count of divisor must be equal to one for a number to a prime
        {
            return false;
        }
    else
        return true;
    }

// function to encrypt 
public int encrypt(char arr, int key)
{
       int cp = ((int)arr * key) + 97; // convert each charater to int and use m * k + 97
    
    return cp;
}
// a function to decrypt cipher text to get plain text
public char[] decrypt(int [] cyphered, int key)
{
    char plain[] = new char[cyphered.length];
    for(int i=0;i<cyphered.length;i++)
    {
        plain[i]= (char) ((cyphered[i] - 97) / key);
    }
    return plain;
}
    public static void main(String[] args) throws IOException {
        int p, g, a,b;  
        String message;
        System.out.println("..........WELCOME TO DIFFIE HELLMAN KEY EXCHANGE ..........");
        System.out.print("\n");
        Hellman ob = new Hellman();            // creating new object to access class methods
        Scanner input = new Scanner(System.in); // creating Scanner object to input values of variable
        while (true)                            // a loop to continuously prompt user to enter p until prime number is entered
                {
                System.out.print("enter the value of p: ");
                p = input.nextInt();
                if(!ob.is_prime(p))
                {
                    System.out.println("P is supposed to be a prime number!,try again");
                }
                else
                {
                    break; // break the loop when a prime number is entered
                }
                
                }
        System.out.print("\n");
        System.out.print("enter the value of primitive root g: ");
        g = input.nextInt();                  // get the value of primitive root and store in variable g
        System.out.print("\n");
        System.out.println("..........Alice..........");
        System.out.print("enter the value of a: ");   // Alice choose her private key a
        a = input.nextInt();                         // get value of a and store it in variable a
        int u = ob.generate(g, a, p);               //  she then computes u and send it to Bob
        System.out.print("\n");
        System.out.println("..........Bob..........");   
        System.out.print("enter the value of b: ");      // Bob choose his private key b
        b = input.nextInt();                            //  get it and store it in variable b
        int v = ob.generate(g, b, p);                  //   he computes v and send it to Alice
        System.out.print("\n");
        System.out.println("Alice publishes: " + u);  // print sent u by Alice
        System.out.print("\n");
        System.out.println("Bob publishes: " + v);   //  print sent v by Bob
        System.out.print("\n");
        int key_a = ob.generate(v,a,p);      //  then both calculate the key k which is the same for all of them
        int key_b = ob.generate(u,b,p);
        System.out.println("Alice's key: " + key_a); // print out the key
        System.out.print("\n");
        System.out.println("Bob's key: " + key_b);
        System.out.print("\n");
        BufferedReader ob1 = new BufferedReader(new InputStreamReader(System.in)); // creating Buffer Reader object to access method of Buffer Reader class
 	System.out.print("Enter a message to encrypt: "); 
 	message = ob1.readLine();                      // get the whole message using readline method in class Buffer Reader
        System.out.print("\n");
        int l = message.length();                     // store the lenght of message in variable l
        char arr[] = new char[l];
        for(int i=0; i< message.length(); i++)       // convert each character of message to a number and store them in array
        {
        arr[i] = message.charAt(i);
        }
        
        int ciphered[] = new int[l];                // allocate a memory to store cipher text
        for(int i=0; i < message.length(); i++)     // encrypt each value and store result in ciphered memmory
        {
             ciphered[i] = ob.encrypt(arr[i], key_a);
        }
        
        System.out.print("encrypted text is: ");   // print out the result after encryption
        for(int i=0; i < ciphered.length;i++)
        {
        System.out.print((char)ciphered[i]);      // typecast each value to a corresponding character
        }
        char plain[] = ob.decrypt(ciphered, key_a);  // decrypt the cipher text and store result in plain text memory
        System.out.print("\n\n");
        System.out.print("decrypted text is: ");
        System.out.println(plain);   // display the resultent plain text or orginal message
        System.out.print("\n");
        System.out.println("..........THANK YOU FOR USING DEFFIE HELLMAN KEY EXCHANGE..........");
    }
    
}
