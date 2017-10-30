package ahv1.app.autohelpv2.Maps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amaury on 20/09/2017.
 */

public class datapaser {
    private HashMap<String,String> getplacel(JSONObject googleplacejson) {
        HashMap<String, String> googleplcesmap = new HashMap<>();
        String placename = "--NA--";
        String vicinity = "--NA--";
        String latitude = "";
        String longitude = "";
        String reference = "";

        try {
            if (!googleplacejson.isNull("name")) {

                    placename = googleplacejson.getString("name");
            }
            if (!googleplacejson.isNull("vicinity")) {

                vicinity = googleplacejson.getString("vicinity");
            }
            latitude = googleplacejson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googleplacejson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googleplacejson.getString("reference");

            googleplcesmap.put("place_name",placename);
            googleplcesmap.put("vicinity",vicinity);
            googleplcesmap.put("lat",latitude);
            googleplcesmap.put("lng",longitude);
            googleplcesmap.put("reference",reference);


        }catch (JSONException e) {
            e.printStackTrace();}
        return googleplcesmap;
    }
    private List<HashMap<String,String>> getplaces(JSONArray jsonArray){
        int count=jsonArray.length();
        List<HashMap<String,String>> placeslist= new ArrayList<>();
        HashMap<String,String> placemap=null;
        for(int i =0;i<count;i++){
            try {
                placemap= getplacel((JSONObject) jsonArray.get(i));
                placeslist.add(placemap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placeslist;

    }
    public List<HashMap<String,String>> parse(String jsondata){
        JSONArray jsonarray = null;
        JSONObject jsonobject;
        try {
            jsonobject = new JSONObject(jsondata);
            jsonarray = jsonobject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getplaces(jsonarray);
    }
}
