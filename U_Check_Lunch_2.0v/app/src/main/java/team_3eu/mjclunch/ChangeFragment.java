package team_3eu.mjclunch;

import android.content.Context;

import static team_3eu.mjclunch.var_Naver_Db.*;

public class ChangeFragment {
    public static String[][] dbAll, dbKor, dbChi, dbJap, dbWest, dbEtc;

    public static void getDB(String menu, Context context){
        DB_Android DBA = new DB_Android(context);
        DB_Android.GetData Data = new DB_Android.GetData();
        Data.execute("http://34.74.154.52/retest.php", "0", menu);  // 에뮬-"10.0.2.2
    }

    public static void setDB() {
        dbAll = new String[db_All.size()][];

        for(int i=0; i<db_All.size(); i++)
        {
            String[] row = db_All.get(i);
            dbAll[i] = new String[row.length];

            for(int j=0; j<row.length; j++)
                dbAll[i][j] = row[j];
        }

        dbKor = new String[db_Kor.size()][];

        for(int i=0; i<db_Kor.size(); i++)
        {
            String[] row = db_Kor.get(i);
            dbKor[i] = new String[row.length];

            for(int j=0; j<row.length; j++)
                dbKor[i][j] = row[j];
        }

       dbChi = new String[db_Chi.size()][];

        for(int i=0; i<db_Chi.size(); i++)
        {
            String[] row = db_Chi.get(i);
            dbChi[i] = new String[row.length];

            for(int j=0; j<row.length; j++)
                dbChi[i][j] = row[j];
        }

        dbJap = new String[db_Jap.size()][];

        for(int i=0; i<db_Jap.size(); i++)
        {
            String[] row = db_Jap.get(i);
            dbJap[i] = new String[row.length];

            for(int j=0; j<row.length; j++)
                dbJap[i][j] = row[j];
        }

        dbWest = new String[db_West.size()][];

        for(int i=0; i<db_West.size(); i++)
        {
            String[] row = db_West.get(i);
            dbWest[i] = new String[row.length];

            for(int j=0; j<row.length; j++)
                dbWest[i][j] = row[j];
        }

        dbEtc = new String[db_Etc.size()][];

        for(int i=0; i<db_Etc.size(); i++)
        {
            String[] row = db_Etc.get(i);
            dbEtc[i] = new String[row.length];

            for(int j=0; j<row.length; j++)
                dbEtc[i][j] = row[j];
        }
    }

    public static void onDB(String menu){
        dbSize.clear();
        switch (menu){
            case "전체":
                for(int i=0; i<db_All.size(); i++)
                    dbSize.add(dbAll[i][1]);
                break;
            case "한식":
                for(int i=0; i<db_Kor.size(); i++)
                    dbSize.add(dbKor[i][1]);
                break;
            case "중식":
                for(int i=0; i<db_Chi.size(); i++)
                    dbSize.add(dbChi[i][1]);
                break;
            case "일식":
                for(int i=0; i<db_Jap.size(); i++)
                    dbSize.add(dbJap[i][1]);
                break;
            case "양식":
                for(int i=0; i<db_West.size(); i++)
                    dbSize.add(dbWest[i][1]);
                break;
            case "기타":
                for(int i=0; i<db_Etc.size(); i++)
                    dbSize.add(dbEtc[i][1]);
                break;
        }
    }
}
