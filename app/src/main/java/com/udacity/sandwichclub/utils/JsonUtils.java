package com.udacity.sandwichclub.utils;

import android.net.Uri;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if(json!=null && !json.isEmpty()){
            Log.d("JSONUtils",json);
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject name = jsonObject.getJSONObject("name");
                String mainName = name.getString("mainName");
                JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
                List<String> alsoKnownAsListString = JSONArrayToListString(alsoKnownAs);
                String placeOfOrigin = jsonObject.getString("placeOfOrigin");
                String description = jsonObject.getString("description");
                String imageUrlString =jsonObject.getString("image");
                Uri imageUri = Uri.parse(imageUrlString);
                JSONArray ingredientsJArray = jsonObject.getJSONArray("ingredients");
                List<String> ingredients = JSONArrayToListString(ingredientsJArray);


                return new Sandwich(mainName,alsoKnownAsListString,placeOfOrigin,description,imageUrlString,ingredients);
            }
            catch (JSONException e){}
        }

        return null;
    }

    public static List<String> JSONArrayToListString(JSONArray array){
        List<String> retVal = new ArrayList<String>();
        try{
            for (int i =0; i<array.length(); i++) {
                retVal.add(array.getString(i));
            }
        }
        catch (JSONException e){}

        return retVal;

    }
}
