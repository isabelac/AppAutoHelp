package ahv1.app.autohelpv2.Maps;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amaury on 20/09/2017.
 */

public class getnearbyplaces extends AsyncTask<Object,String,String> {
    private String googleplacesdata;
    private GoogleMap mMap;
    public static LatLng latAtual;
    String url;

    public void setLatAtual(LatLng latAtual) {
        this.latAtual = latAtual;
    }

    @Override
    protected String doInBackground(Object... params) {
        mMap = (GoogleMap)params[0];
         url = (String)params[1];
        donwloadurl downloadurl = new donwloadurl();
        try {
            googleplacesdata = downloadurl.readurl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleplacesdata;
    }

    @Override
    protected void onPostExecute(String s) {
       List<HashMap<String , String>> nearbyplaceslist;
        datapaser parser = new datapaser();
        nearbyplaceslist= parser.parse(s);
        shownearbyplaces(nearbyplaceslist);
    }

    private void shownearbyplaces(List<HashMap<String,String>> nearbyplaceslist){
        for (int i =0 ; i<nearbyplaceslist.size(); i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String,String> googleplace = nearbyplaceslist.get(i);
            String placename = googleplace.get("place_name");
            String vicinity = googleplace.get("vicinity");
            double lat = Double.parseDouble(googleplace.get("lat"));
            double lng = Double.parseDouble(googleplace.get("lng"));

            LatLng latlng = new LatLng(lat,lng);
            markerOptions.position(latlng);
            markerOptions.title(placename+" : "+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latAtual));
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));


        }
    }
}
