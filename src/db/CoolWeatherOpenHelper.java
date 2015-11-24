package db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	/**
	 * Province表创建语句
	 * SQLiteOpenHelper抽象类得重写两个方法 一个创建onCreate 一个更新onUpgrade
	 * getReadableDatabase() 和getWritableDatabase()。
	 * 这两个方法都可以创建或打开一个现有的数据库（如果数据库已存在
	 * 则直接打开，否则创建一个新的数据库），并返回一个可对数据库进行读写操作的对象。
	 * 不同的是，当数据库不可写入的时候（如磁盘空间已满）getReadableDatabase()方法返回的对象
	 * 将以只读的方式去打开数据库，而getWritableDatabase()方法则将出现异常。
	 */
	public static final String CREATE_PROVINCE="create table Province("
			+
			"id integer primary key autoincrement" +
			"province_name text" +
			"province_code text" +
			")";
	/**
	 * City表创建语句
	 */
	public static final String CREATE_CITY="create table City("
			+
			"id integer primary key autoincrement" +
			"city_name text" +
			"city_code text" +
			"province_id integer" +
			")";
	/**
	 * County表创建语句
	 */
	public static final String CREATE_COUNTY="create table County("
			+
			"id integer primary key autoincrement" +
			"county_name text" +
			"county_code text" +
			"city_id integer" +
			")";
	/**第一个参数是Context，这个没什么好说的，必须要有它才能对数据库进行操作。
	 * 第二个参数是数据库名，创建数据库时使用的就是这里指定的名称。
	 * 第三个参数允许我们在查询数据的时候返回一个自定义的Cursor，一般都是传入null。
	 * 第四个参数表示当前数据库的版本号， 可用于对数据库进行升级操作。
	 * 构建出SQLiteOpenHelper 的实例之后，再调用它的getReadableDatabase()或getWritableDatabase()方法就能够创建数据库了，
	 * 数据库文件会存放在/data/data/<package name>/databases/目录下。
	 * 此时，重写的onCreate()方法也会得到执行，所以通常会在这里去处理一些创建表的逻辑。
	*/
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub 
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVINCE);//创建Province表
		db.execSQL(CREATE_CITY);//创建City表
		db.execSQL(CREATE_COUNTY);//创建County表
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
