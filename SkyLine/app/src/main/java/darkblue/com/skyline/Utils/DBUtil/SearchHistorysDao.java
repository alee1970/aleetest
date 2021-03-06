package darkblue.com.skyline.Utils.DBUtil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import darkblue.com.skyline.Bean.SearchHistorysBean;

public class SearchHistorysDao {
	
	private DBSearchHelper helper;

	private SQLiteDatabase db;

	public SearchHistorysDao(Context context) {
		helper = new DBSearchHelper(context);
	}




	public void addOrUpdate(String word){
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "select _id from t_historywords where historyword = ? ";
		Cursor cursor = db.rawQuery(sql, new String[]{word});
		if(cursor.getCount()>0){
			String sql_update = "update t_historywords set updatetime = ? where historyword = ? ";
			db.execSQL(sql_update, new String[]{System.currentTimeMillis()+"",word});
		}else{
			String sql_add = "insert into t_historywords(historyword,updatetime) values (?,?);";
			db.execSQL(sql_add, new String[]{word,System.currentTimeMillis()+""});
		}

		cursor.close();
		db.close();
	}

	/**
	 *
	 */

	public ArrayList<SearchHistorysBean> findAll(){
		ArrayList<SearchHistorysBean> data = new ArrayList<SearchHistorysBean>();;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("t_historywords", null, null, null, null, null, "updatetime desc");
		while(cursor.moveToNext()){
			SearchHistorysBean searchDBData = new SearchHistorysBean();
			searchDBData._id =cursor.getInt(cursor.getColumnIndex("_id"));
			searchDBData.historyword = cursor.getString(cursor.getColumnIndex("historyword"));
			searchDBData.updatetime = cursor.getLong(cursor.getColumnIndex("updatetime"));
			data.add(searchDBData);
		}
		cursor.close();
		db.close();
		return data;
	}


	/**
	 *删除所有数据
	 */

	public void deleteAll(){
		SQLiteDatabase db = helper.getReadableDatabase();
		db.execSQL("delete from t_historywords");
		db.close();
	}

	public ArrayList<SearchHistorysBean> queryData(Context context, String tempName) {
		ArrayList<SearchHistorysBean> data = new ArrayList<SearchHistorysBean>();;
		helper = new DBSearchHelper(context);
		Cursor cursor = helper.getReadableDatabase().rawQuery(
				"select id as _id,historyword from t_historywords where historyword like '%" + tempName + "%' order by updatetime desc ", null);

		while(cursor.moveToNext()){

			SearchHistorysBean searchDBData = new SearchHistorysBean();
			searchDBData._id =cursor.getInt(cursor.getColumnIndex("_id"));
			Log.d("SearchHistorysDao", cursor.getColumnIndex("_id")+"");
			searchDBData.historyword = cursor.getString(cursor.getColumnIndex("historyword"));
			Log.d("SearchHistorysDao", cursor.getString(cursor.getColumnIndex("historyword"))+"");

			searchDBData.updatetime = cursor.getLong(cursor.getColumnIndex("updatetime"));
			Log.d("SearchHistorysDao", cursor.getLong(cursor.getColumnIndex("updatetime"))+"");

			data.add(searchDBData);
		}

		return data;
	}
}
