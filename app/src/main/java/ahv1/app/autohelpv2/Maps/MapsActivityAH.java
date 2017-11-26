package ahv1.app.autohelpv2.Maps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import ahv1.app.autohelpv2.Activity.Comentario;
import ahv1.app.autohelpv2.MainActivity;
import ahv1.app.autohelpv2.R;
import ahv1.app.autohelpv2.fragment.ForumDAO;


public class MapsActivityAH extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationrequest;
    private Location lastlocation;
    private Marker currentlocation;
    public static final int REQUEST_LOCATION_CODE=99;
    int PROXIMITY_RADIUS=3000;
    double latitude ;
    double longitude ;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ah);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)checklp();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //inicializa toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarMaps);
        toolbar.setTitle("Oficinas Proximas");
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        Bundle extra  = getIntent().getExtras();
        String user ="";

        if(!extra.equals(null)){
            user = ", "+extra.getString("usuario");
        }

        LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        boolean isOn = manager.isProviderEnabled( LocationManager.GPS_PROVIDER);

        if(!isOn){
            AlertDialog.Builder alert =  new AlertDialog.Builder(MapsActivityAH.this)
                    .setTitle("Hey"+user+"!")
                    .setMessage("Sua localização está desativada!")
                    .setPositiveButton("Ativar Localização", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 1);
                        }
                    });

            alert.create().show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            buildgoogleapiclient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }else Toast.makeText(this,"Permissão negada",Toast.LENGTH_LONG).show();
        }
    }

    public String geturl(double latitude, double longitude, String np) {
        StringBuilder googleplacesurl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleplacesurl.append("location="+latitude+","+longitude);
        googleplacesurl.append("&radius="+PROXIMITY_RADIUS);
        googleplacesurl.append("&type="+np);
        googleplacesurl.append("&key="+"AIzaSyBiGqSwBwDzz5ZP2D7uHseoShtXdQO0FXQ");
        //places
        return googleplacesurl.toString();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            buildgoogleapiclient();
            mMap.setMyLocationEnabled(true);

        }


    }
    protected  synchronized void buildgoogleapiclient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        client.connect();
    }


    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        lastlocation=location;
        if (currentlocation !=null) currentlocation.remove();
        Log.d("lat = ",""+latitude);
        LatLng latlng=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markeroptions = new MarkerOptions();
        markeroptions.position(latlng);
        markeroptions.title("Posição atual");
        markeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        currentlocation= mMap.addMarker(markeroptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(13));
        if (client!=null)LocationServices.FusedLocationApi.removeLocationUpdates(client,  this);
        String oficina = "car_repair";
        String url = geturl(latitude,longitude,oficina);
        Object datatranfer[]=new Object[2];
        getnearbyplaces getnearbyplacesdata= new getnearbyplaces();
        getnearbyplacesdata.setLatAtual(latlng);
        datatranfer[0]=mMap;
        datatranfer[1]=url;
        getnearbyplacesdata.execute(datatranfer);
        Toast.makeText(MapsActivityAH.this,"Oficinas próximas",Toast.LENGTH_LONG).show();

    }

    public boolean checklp(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }else ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            return false;
        }else return true;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationrequest=new LocationRequest();
        locationrequest.setInterval(100);
        locationrequest.setFastestInterval(1000);
        locationrequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(client,locationrequest,this);}
    }

    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}