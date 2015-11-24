package db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	/**
	 * Province�������
	 * SQLiteOpenHelper���������д�������� һ������onCreate һ������onUpgrade
	 * getReadableDatabase() ��getWritableDatabase()��
	 * ���������������Դ������һ�����е����ݿ⣨������ݿ��Ѵ���
	 * ��ֱ�Ӵ򿪣����򴴽�һ���µ����ݿ⣩��������һ���ɶ����ݿ���ж�д�����Ķ���
	 * ��ͬ���ǣ������ݿⲻ��д���ʱ������̿ռ�������getReadableDatabase()�������صĶ���
	 * ����ֻ���ķ�ʽȥ�����ݿ⣬��getWritableDatabase()�����򽫳����쳣��
	 */
	public static final String CREATE_PROVINCE="create table Province("
			+
			"id integer primary key autoincrement" +
			"province_name text" +
			"province_code text" +
			")";
	/**
	 * City�������
	 */
	public static final String CREATE_CITY="create table City("
			+
			"id integer primary key autoincrement" +
			"city_name text" +
			"city_code text" +
			"province_id integer" +
			")";
	/**
	 * County�������
	 */
	public static final String CREATE_COUNTY="create table County("
			+
			"id integer primary key autoincrement" +
			"county_name text" +
			"county_code text" +
			"city_id integer" +
			")";
	/**��һ��������Context�����ûʲô��˵�ģ�����Ҫ�������ܶ����ݿ���в�����
	 * �ڶ������������ݿ������������ݿ�ʱʹ�õľ�������ָ�������ơ�
	 * �������������������ڲ�ѯ���ݵ�ʱ�򷵻�һ���Զ����Cursor��һ�㶼�Ǵ���null��
	 * ���ĸ�������ʾ��ǰ���ݿ�İ汾�ţ� �����ڶ����ݿ��������������
	 * ������SQLiteOpenHelper ��ʵ��֮���ٵ�������getReadableDatabase()��getWritableDatabase()�������ܹ��������ݿ��ˣ�
	 * ���ݿ��ļ�������/data/data/<package name>/databases/Ŀ¼�¡�
	 * ��ʱ����д��onCreate()����Ҳ��õ�ִ�У�����ͨ����������ȥ����һЩ��������߼���
	*/
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub 
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVINCE);//����Province��
		db.execSQL(CREATE_CITY);//����City��
		db.execSQL(CREATE_COUNTY);//����County��
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
