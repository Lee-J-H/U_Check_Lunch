package team_3eu.mjclunch;


import android.os.Bundle;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import static team_3eu.mjclunch.Naver_map_api.*;

public class NaverMap extends NMapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_map);
        mapLayout = findViewById(R.id.naverapimap);
        nMapResourceProvider = new NMapViewerResourceProvider(this);
        mapOverlayManager = new NMapOverlayManager(this, mMapView, nMapResourceProvider);
        Naver_map_api.init.in_inte(this);
        Naver_map_api.init.in_inite1(false);
    }
}