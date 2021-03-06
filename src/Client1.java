import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



class doRecieve1 extends Thread
{   Socket socket;
    Scanner in;
    PrintWriter out;
    Scanner inp;
    boolean loggedIN ;

    public doRecieve1(Socket socket,Scanner in , PrintWriter out , Scanner inp ){
        this.socket = socket ;
        this.in = in ;
        this.out = out ;
        this.inp = inp ;
        this.loggedIN = false ;
    }

    public void run()
    {
        while(in.hasNextLine()){
            var line = in.nextLine();
            if (line.startsWith("SUBMITNAME")) {
                System.out.print("Submit Name : ");
                out.println(inp.nextLine());
            } else if (line.startsWith("NAMEACCEPTED")) {
                System.out.println("-- Connected -- ");
                System.out.println("Chatter - " + line.substring(13) );
                if(!loggedIN){
                    doSent ds = new doSent(socket, in, out, inp);
                    ds.start();
                }

            } else if (line.startsWith("MESSAGE")) {
                System.out.println(" ---- "+ line.substring(8) + "\n");
            }
        }
    }
}

class doSent1 extends Thread
{   Socket socket;
    Scanner in;
    PrintWriter out;
    Scanner inp;

    public doSent1(Socket socket,Scanner in , PrintWriter out , Scanner inp ){
        this.socket = socket ;
        this.in = in ;
        this.out = out ;
        this.inp = inp ;
    }

    public void run()
    {
        while(true){
            out.println(inp.nextLine());
        }
    }
}

public class Client1 {


    public static void main(String[] args) throws IOException {

        var socket = new Socket("127.0.0.1", 25000);
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner inp = new Scanner(System.in);

        doRecieve dr = new doRecieve(socket, in, out, inp);
        dr.start();


    }

}