    import java.util.*;
    import java.lang.*;
    import BankApp.*;
    import org.omg.CosNaming.*;
    import org.omg.CosNaming.NamingContextPackage.*;
    import org.omg.CORBA.*;
    import org.omg.PortableServer.*;
    import org.omg.PortableServer.POA;
    import java.util.Properties;

class BankInterfaceImpl extends BankInterfacePOA  // This is the servant class
{
    private ORB orb;

    public void setORB(ORB orb_val) 
    {   
        orb = orb_val;
    }

    /* List of functions specified in IDL file
        string add(in double x, in double y);
        string subtract(in double a, in double b);
        string multiply(in double x, in double y);
        string divide(in double a, in double b);
        string random();
        oneway void shutdown();
        
     if IDL file would have following function
     void multiply(in double x, in double y, out double result);
     then implement it here like
     
    public void multiply(double x, double y, org.omg.CORBA.DoubleHolder result) 
    {  
      result.value = x * y;  
    }
    */

    //implement getFixedDeposit(double,double) method
    public String getFixedDeposit(double x, double y)
    {
        double A,P,r,n,t,I;
        double temp;
        P=x;		//deposit amount
        r = 0.10; 	//interest rate
        t=y;		//time period
        n=4;		//Compounded Interest Frequency
        temp=(1+r/n);
        A=P*Math.pow(temp,(n*t));
        I=A-P;
        double res= Math.round(I);
        return Double.toString(res);
    }

    //implement getTotalAmount(double,double) method
    public String getTotalAmount(double x, double y)
    {
        double A,P,r,n,t,I;
        double temp;
        P=x;		//deposit amount
        r = 0.10; 	//interest rate
        t=y;		//time period
        n=4;		//Compounded Interest Frequency
        temp=(1+r/n);
        A=P*Math.pow(temp,(n*t));
        double res= Math.round(A);
        return Double.toString(res);
    }
    // public String getInterest(double x, double y)
    // {
    //     double A,P,r,n,t,I;
    //     double temp;
    //     P=x;        //deposit amount
    //     r = 0.10;   //interest rate
    //     t=y;        //time period
    //     n=4;        //Compounded Interest Frequency
    //     // temp=(1+r/n);
    //     // A=P*Math.pow(temp,(n*t));
    //     A = (P * r * t)/100;
    //     double res= Math.round(A);
    //     return Double.toString(res);
    // }

    // implement shutdown() method
    public void shutdown()
    {
            orb.shutdown(false);
    }
}

public class BankServer
{
    public static void main(String args[]) 
   {

    try{
            // Create and initialize the ORB  
         ORB orb = ORB.init(args, null);  
        
         // get reference to rootpoa & activate the POAManager
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();

        // create servant and register it with the ORB
        BankInterfaceImpl BankImpl = new BankInterfaceImpl();
        BankImpl.setORB(orb);

        // get object reference from the servant
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(BankImpl);
        BankInterface href = BankInterfaceHelper.narrow(ref);

        // get the root naming context
        org.omg.CORBA.Object objRef =orb.resolve_initial_references("NameService");

        // Use NamingContextExt which is part of the Interoperable
        // Naming Service (INS) specification.
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        // bind the Object Reference in Naming
        String name = "BankOperations";
        NameComponent path[] = ncRef.to_name( name );
        ncRef.rebind(path, href);
        System.out.println("\n----------------------------------------------");
        System.out.println("BankServer: Ready and waiting...");
        System.out.println("\n----------------------------------------------");

        // wait for invocations from clients
        orb.run();
        }
            catch (Exception e) 
            {   
                 //System.err.println("ERROR: " + e);   
                 //e.printStackTrace(System.out);   

                 System.out.println("\n----------------------------------------------");
                 System.out.println("BankServer: Some Error Has Occurred !");
                 System.out.println("\n----------------------------------------------");
            }
            System.out.println("\nBankServer: Exiting...");
            System.out.println("\n----------------------------------------------");
        
        }
    }
