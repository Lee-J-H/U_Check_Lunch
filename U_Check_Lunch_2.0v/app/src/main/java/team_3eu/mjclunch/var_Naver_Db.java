package team_3eu.mjclunch;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by LeeMJ on 2018. 11. 8..
 */

public class var_Naver_Db {

    static String selMenu=null;

    static String id=null;
    static String name=null;
    static String time=null;
    static String newaddr=null;
    static String oldaddr=null;
    static String menu=null;
    static String tel=null;
    static String distance=null;
    static String price=null;

    static String tels=null;
    static String times = null;

    static double mapx, mapy ;
    static String addrs;
    static String names;


    static String prices = null;

    static final int on_start=1;
    static final int GetPoint=2;
    static final int Simple_start=3;
    static final int Normal_view_start =4;

    static final int Simple_image=5;
    static final int Normal_image=6;

    static final int All_Menu = 10;
    static final int Korea_Menu = 11;
    static final int China_Menu = 12;
    static final int Japan_Menu = 13;
    static final int West_Menu = 14;
    static final int Etc_Menu = 15;

    static final int Faild_internet = 20;


    //static ArrayList normalList = new ArrayList();
    static ArrayList<Bitmap> imgArray = new ArrayList<Bitmap>();
    static ArrayList<String> dbText = new ArrayList<String>();


    static ArrayList<String[]> db_All = new ArrayList<String[]>();
    static ArrayList<String[]> db_Kor = new ArrayList<String[]>();
    static ArrayList<String[]> db_Chi = new ArrayList<String[]>();
    static ArrayList<String[]> db_Jap = new ArrayList<String[]>();
    static ArrayList<String[]> db_West = new ArrayList<String[]>();
    static ArrayList<String[]> db_Etc = new ArrayList<String[]>();

    static ArrayList<Bitmap> Img_All = new ArrayList<Bitmap>();
    static ArrayList<Bitmap> Img_Kor = new ArrayList<Bitmap>();
    static ArrayList<Bitmap> Img_Chi = new ArrayList<Bitmap>();
    static ArrayList<Bitmap> Img_Jap = new ArrayList<Bitmap>();
    static ArrayList<Bitmap> Img_West = new ArrayList<Bitmap>();
    static ArrayList<Bitmap> Img_Etc = new ArrayList<Bitmap>();

    static ArrayList<String> dbSize = new ArrayList<String>();
    static Ct_List.CustomList adapter;

    static Boolean Mode_Nor_Sim=null; // 헨들러 심플 모드냐 노말 모드냐  t 심플 f 노말


    static String Client_ID = "Epa7_8HPQo_tbJr68T9Y";

}
