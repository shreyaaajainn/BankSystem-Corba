import BankApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;
import java.lang.*;

public class BankClient
{
  static BankInterface BankImpl;
  static int flag=1;
  static double x=0.0d;
  static double y=0.0d;
  
  public static void main(String args[])
    {
        
      try{
                 System.out.println("\n----------------------------------------------");
                 System.out.println("BankClient: Looking up BankServer...");
           
                // create and initialize the ORB
                ORB orb = ORB.init(args, null);

                // get the root naming context
                org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references("NameService");

                // Use NamingContextExt instead of NamingContext. 
                // This is part of the Interoperable naming Service.  
                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

                // resolve the Object Reference in Naming
                String name = "BankOperations";
                BankImpl = BankInterfaceHelper.narrow(ncRef.resolve_str(name));

                System.out.println("BankClient: Obtained a handle on server object: \n\n" + BankImpl);

                Scanner sc=new Scanner(System.in);
      
                flag=1;

                do
                {
		System.out.println("--------------------------------------------------");

                System.out.print("\nBankClient: Enter Deposit Amount : ");
                x=sc.nextDouble();

                System.out.print("\nBankClient: Enter the Number of Years : ");
                y=sc.nextDouble();

                System.out.println("--------------------------------------------------");

                System.out.println("\nBankClient: Total Interest: \t= "+BankImpl.getFixedDeposit(x,y));
				System.out.println("\nBankClient: Interest Earned Amount: \t= "+BankImpl.getTotalAmount(x,y));
				// System.out.println("\nBankClient: Simple Interest Earned Amount: \t= "+BankImpl.getInterest(x,y));

                System.out.println("--------------------------------------------------");
                System.out.println("Continue?[1:Yes|0:No]: ");
                flag=sc.nextInt();
                }
                while (flag!=0);

                BankImpl.shutdown();
         }
            catch (Exception e) 
            {   
                 //System.err.println("ERROR: " + e);   
                 //e.printStackTrace(System.out);   
                 System.out.println("\n----------------------------------------------");
                 System.out.println("BankClient: Some Error Has Occurred !");
                 System.out.println("\n----------------------------------------------");
            }

            System.out.println("\nBankServer: Exiting...");
            System.out.println("\n----------------------------------------------");
        }
}
     





