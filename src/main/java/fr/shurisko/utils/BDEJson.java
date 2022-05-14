package fr.shurisko.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BDEJson {

    private final JSONParser jsonParser;
    private final JSONObject jsonObject;

    public BDEJson(String content) throws ParseException {
        jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(content);
    }

    public Object getElementFromJSON(String elementIndex) {
        return this.jsonObject.get(elementIndex);
    }

}
