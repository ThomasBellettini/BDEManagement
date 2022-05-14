package fr.shurisko.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.shurisko.BDEServer;
import fr.shurisko.account.BDEAccount;
import fr.shurisko.event.BDEEvent;

import java.io.*;
import java.util.Locale;
import java.util.Objects;

public class BDEStorage {

    private final Gson gson;

    private final File _ACCOUNT_DIR;
    private final File _EVENT_DIR;

    public BDEStorage(String directory) {
        gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
        final File tmp = new File(directory);
        if (!tmp.exists()) if (!tmp.mkdir()) System.exit(84);
        _ACCOUNT_DIR = new File(tmp, "/account/");
        _EVENT_DIR = new File(tmp, "/event/");
        if (!_EVENT_DIR.exists())
            if (!_EVENT_DIR.mkdir()) System.exit(84);
        if (!_ACCOUNT_DIR.exists())
            if (!_ACCOUNT_DIR.mkdir()) System.exit(84);
    }

    private void loadAccountData(String str) {
        final BDEAccount account = gson.fromJson(str, BDEAccount.class);
        if (account == null) return;
        BDEServer.getInstance.bdeManager.getListAccount().add(account);
    }

    private void loadEventData(String str) {
        final BDEEvent event = gson.fromJson(str, BDEEvent.class);
        if (event == null) return;
        BDEServer.getInstance.bdeManager.getListEvent().add(event);
    }

    public void loadAllData() {
        for (File file : Objects.requireNonNull(_ACCOUNT_DIR.listFiles())) {
            if (!file.getName().endsWith(".json")) continue;
            loadAccountData(readFile(file));
        }
        for (File file : Objects.requireNonNull(_EVENT_DIR.listFiles())) {
            if (!file.getName().endsWith(".json")) continue;
            loadEventData(readFile(file));
        }
    }

    private String readFile(File f) {
        try {
            final BufferedReader r = new BufferedReader(new FileReader(f));
            final StringBuilder t = new StringBuilder();
            String l;
            while((l = r.readLine()) != null)
                t.append(l);
            r.close();
            return t.toString();
        }  catch (IOException e) { return null; }
    }

    private void writeFile(File origin, String fileName, Object content) {
        final FileWriter fileWriter;
        File file = new File(origin, fileName + ".json");
        try {
            if (!file.exists()) if (!file.createNewFile()) return;
            fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(content));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void saveAccount(BDEAccount account) {
        writeFile(_ACCOUNT_DIR, account.getAccountUUID().toLowerCase(Locale.ROOT), account);
    }

    public void saveEvent(BDEEvent event) {
        writeFile(_EVENT_DIR, event.getEventUUID().toLowerCase(Locale.ROOT), event);
    }

    public void saveAllData() {
        for (BDEAccount account : BDEServer.getInstance.bdeManager.getListAccount())
            saveAccount(account);
        for (BDEEvent event : BDEServer.getInstance.bdeManager.getListEvent())
            saveEvent(event);
    }

}
