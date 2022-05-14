package fr.shurisko.routes.connection;

import fr.shurisko.BDEServer;
import fr.shurisko.account.BDEAccount;
import fr.shurisko.utils.BDEJson;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jetty.util.security.Credential;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class LoginRoute {

    public static class LoginMessage {
        public String message;

        public LoginMessage(String message) {
            this.message = message;
        }
    }

    private final BDEServer main;
    public LoginRoute (BDEServer main) {
        this.main = main;
    }

    public LoginMessage postLogin(Request request, Response response) {
        try {
            BDEJson json = new BDEJson(request.body());
            String username = (String) json.getElementFromJSON("name");
            String password = (String) json.getElementFromJSON("password");
            if (username == null || password == null) return new LoginMessage("Missing Field or Invalid Data !");
            BDEAccount account = main.bdeManager.getAccountFromEmail(username);
            if (account == null) return new LoginMessage("the name or the password is incorrect");
            if (account.getAccountPassword().equals(DigestUtils.md5Hex(
                    main.systemEnv.get("SALT_PREFIX") + password + main.systemEnv.get("SALT_SUFFIX")
            ))) {
                String token = main.tokenManager.addToken(account);
                request.session(true);
                request.session().attribute("token", token);
                return new LoginMessage("Success, Welcome " + account.getAccountName());
            }
            return new LoginMessage("the name or the password is incorrect !");

        } catch (ParseException ignored) { return new LoginMessage("Incorrect JSON !"); }
    }

    public LoginMessage postRegister(Request request, Response response) {
        try {
            BDEJson json = new BDEJson(request.body());
            String username = (String) json.getElementFromJSON("name");
            String password = (String) json.getElementFromJSON("password");
            if (username == null || password == null) return new LoginMessage("Missing Field or Invalid Data !");
            BDEAccount account = main.bdeManager.getAccountFromEmail(username);
            if (account != null) return new LoginMessage("An account has the same email !");
            account = new BDEAccount(username, password);
            main.bdeStorage.saveAccount(account);
            main.bdeManager.getListAccount().add(account);
            return new LoginMessage("You have created a new account !");
        } catch (ParseException ignored) { return new LoginMessage("Incorrect JSON !"); }
    }

}
