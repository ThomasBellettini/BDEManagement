package fr.shurisko;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.shurisko.account.BDEAccount;
import fr.shurisko.account.BDEPermissionEnum;
import fr.shurisko.manager.BDEManager;
import fr.shurisko.routes.connection.LoginRoute;
import fr.shurisko.security.BDEToken;
import fr.shurisko.storage.BDEStorage;

import java.util.Map;

import static spark.Spark.*;

public class BDEServer {

    private final Gson gson = new GsonBuilder()
            .serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();

    public static BDEServer getInstance;
    public BDEManager bdeManager;
    public BDEToken tokenManager;
    public BDEStorage bdeStorage;

    public Map<String, String> systemEnv = System.getenv();

    private final LoginRoute loginRoute = new LoginRoute(this);

    private void loadCreationRoute() {
        path("/create", () -> {
            before("/*", (request, response) -> {
                if (request.session() == null || request.session().attribute("token") == null)
                    halt(401, "You need to be connected !");
                else if (tokenManager.isLogin(request.session().attribute("token")) == null)
                    halt(401, "invalid account !");
            });
            before("/account", (request, response) -> {
                BDEAccount account = tokenManager.isLogin(request.session().attribute("token"));
                if (!account.getPermissions().get(BDEPermissionEnum.CREATE_ACCOUNT)) halt(403, "You don't have the permission !");
            });
            post("/account", loginRoute::postRegister, gson::toJson);
        });
    }

    private void loadRoute() {
        notFound("Nop");
        path("/", () -> {
                post("/login", loginRoute::postLogin, gson::toJson);
        });
        path("/event", () -> {
            before("/*", (request, response) -> {
                if (request.session() == null || request.session().attribute("token") == null)
                    halt(401, "You need to be connected !");
                else if (tokenManager.isLogin(request.session().attribute("token")) == null)
                    halt(401, "invalid account !");
            });
        });
        loadCreationRoute();
    }

    public BDEServer (int serverPort) {
        getInstance = this;
        System.out.println("(BDE) Launching server manager ...");
        bdeManager = new BDEManager();
        tokenManager = new BDEToken();
        bdeStorage = new BDEStorage("./data/");

        System.out.println("(BDE) Loading all server data ...");
        bdeStorage.loadAllData();
        System.out.println("(BDE) Starting server & all routes ...");
        port(serverPort);
        loadRoute();
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
