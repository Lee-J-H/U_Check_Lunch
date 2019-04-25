package team_3eu.mjclunch;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;


import static team_3eu.mjclunch.var_Naver_Db.*;


/**
 * Created by LeeMJ on 2018. 11. 8..
 */

public class Naver_map_api extends NMapActivity {
    public static NMapView mMapView;
    public static ViewGroup mapLayout;

    public static NMapResourceProvider nMapResourceProvider;
    public static NMapOverlayManager mapOverlayManager;

    static Boolean clickable = true;

    public static class init {

        static Context mContext;

        public static void in_inte(final Context context) {
            mMapView = new NMapView(context);
            nMapResourceProvider = new NMapViewerResourceProvider(context);
            mapOverlayManager = new NMapOverlayManager(context, mMapView, nMapResourceProvider);
            mMapView.setNcpClientId(Client_ID); // 클라이언트 아이디 값 설정
            mMapView.setClickable(clickable);
            mMapView.setEnabled(true);
            mMapView.setFocusable(true);
            mMapView.setFocusableInTouchMode(true);
            mMapView.setScalingFactor(1.8f);
            mContext = context;
        }

        public static void in_inite1(final Boolean a) {
            final NMapController mMapController;
            mMapController = mMapView.getMapController();
            mMapController.setMapCenter(new NGeoPoint(mapx, mapy), 13);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setMarker_NaverMap(a);
                }
            }, 200);
            mMapView.requestFocus();
            mapLayout.addView(mMapView);
            mMapView.setOnMapViewTouchEventListener(new NMapView.OnMapViewTouchEventListener() {
                @Override
                public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {}

                @Override
                public void onLongPressCanceled(NMapView nMapView) {}

                @Override
                public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {}

                @Override
                public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {}

                @Override
                public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {}

                @Override
                public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {
                    if(a) {
                        Intent intent1 = new Intent(mContext, NaverMap.class);
                        mContext.startActivity(intent1);
                    }
                }
            });
        }
    }


    public static void setMarker_NaverMap(boolean a) {
        int markerId = NMapPOIflagType.PIN;
        int markerId2 = NMapPOIflagType.SPOT;
        NMapPOIdata poiData = new NMapPOIdata(2, nMapResourceProvider);
        poiData.beginPOIdata(2);
        poiData.addPOIitem(mapx, mapy, names, markerId, 0);
        if(!a) poiData.addPOIitem(126.924404, 37.584097, "정문", markerId2, 0); //126.924404, 37.584097
        poiData.endPOIdata();
        NMapPOIdataOverlay poiDataOverlay = mapOverlayManager.createPOIdataOverlay(poiData, null);
        poiDataOverlay.showAllPOIdata(0);
        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);  //좌표 클릭시 말풍선 리스
    }


    public static NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
        @Override
        public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {}
        @Override
        public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {
            if (nMapPOIitem != null) {} else {}
        }
    };

}



