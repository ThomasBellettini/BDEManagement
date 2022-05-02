package fr.shurisko;

import static spark.Spark.*;

public class BDEServer {

    public static BDEServer getInstance;

    public BDEServer (int serverPort) {
        getInstance = this;
        System.out.println("(BDE) Launching server manager ...");
        port(serverPort);
        System.out.println("(BDE) Server has start at the port " + serverPort);
    }

    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.err.println("(BDE) Error Usage: ./archive.jar <port>");
//            System.exit(84);
//        }
//        int port = 0;
//        try {
//            port = Integer.parseInt(args[0]);
//        } catch (NumberFormatException e) {
//            System.err.println("(BDE) Error: Port need to be a number !");
//            System.exit(84);
//        }

        new BDEServer(8085);
    }

}
