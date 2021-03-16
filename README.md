# corba

** These commands are only valid for windows terminal

-----------------------------------------------
############# Compiling files #################
-----------------------------------------------

1 . open terminal

2 . go to correct folder using cd command

3 . open another terminal and type the command
-> tnameserv

3 . compile the idl file (it will generate BankAppfolder) [in the first cmd]
->  idlj -fall Bank.idl

4 . compile the client code
->  javac BankClient.java

5 . compile the server code
->  javac BankServer.java 

6 . compile files in BankApp folder
->  cd BankApp
->  javac *.java 

7 . run following code to initialize the connection
->  orbd -ORBInitialPort 1050 -ORBInitialHost localhost

---------------------------------------------------
############# Running Server Code #################
---------------------------------------------------

1 . open new terminal

2 . go to correct folder using cd command 

3 . type following command to run the client code
->  java BankServer -ORBInitialPort 1050 -ORBInitialHost localhost

---------------------------------------------------
############# Running Client Code #################
---------------------------------------------------

1 . open new terminal 

2 . go to correct folder using cd command 

3 . type following command to run the client code
->  java BankClient -ORBInitialPort 1050 -ORBInitialHost localhost
