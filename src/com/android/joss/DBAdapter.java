package com.android.joss;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DBAdapter {
    // database name
    public static final String DATABASE_NAME = "UserDB";
    // table names
    public static final String TABLE_NAME = "tableone";
    public static final String TABLE_NAME2 = "tablecats";
    
    
    // database version
    public static final int DATABASE_VERSION = 1;
 
    // defining columns
    public static final String COL_ROWID = "rowid";
    public static final String COL_DATE_TIME = "date_time";
    public static final String COL_PROTEIN = "protein";
    public static final String COL_CARBS = "carbs";
    public static final String COL_SUGAR = "sugar";
    public static final String COL_CHOLESTROL = "chlolestrol";
    public static final String COL_FAT = "fat";
    public static final String COL_CALORIES = "calories";
    public static final String COL_FIBRE = "fibre";
    public static final String COL_VITAMINA = "vitaminA";
    public static final String COL_POTASIUM = "potasium";
    public static final String COL_SODIUM = "sodium";
    
    public static final String COL_CARTID = "cartid";
    public static final String COL_NUTRNAME = "nutrientname";
    public static final String COL_NUTRVALUE = "nutrientvalue";
    public static final String COL_VISIBILITY= "visibility";
    
    public static final String COL_NAME = "name";
    
    
    
    
 
    
    Context context;
 
    DBHelper dbHelper;
 
    SQLiteDatabase db;
 
    public DBAdapter(Context c) {
        // TODO Auto-generated constructor stub
        this.context = c;
        dbHelper = new DBHelper(context);
    }
 
    DBAdapter openDatabase() {
        db = dbHelper.getWritableDatabase();
        return this;
 
    }
 
    void close() {
        dbHelper.close();
    }
 
 
    
    //Inserting to the records table 
    long insertRecord(String date_time, String protein,String carbs,
    		String sugar, String cholestrol,String fat,String calories,String 
    		fibre,String vitaminA,String potasium,String sodium) {
    	
    	
        ContentValues con = new ContentValues();
        con.put(COL_DATE_TIME, date_time);
        con.put(COL_PROTEIN, protein);
        con.put(COL_CARBS, carbs);
        con.put(COL_SUGAR, sugar);
        con.put(COL_CHOLESTROL, cholestrol);
        con.put(COL_FAT, fat);
        
        con.put(COL_CALORIES, calories);
        con.put(COL_FIBRE, fibre);
        con.put(COL_VITAMINA, vitaminA);
        con.put(COL_POTASIUM, potasium);
        con.put(COL_SODIUM, sodium);
        
 
        return db.insert(TABLE_NAME, null, con);
 
    }
    
    //inserting to the Carts table
    long insertCart(String date_time, String protein,String carbs,
    		String sugar, String cholestrol,String fat,String calories,String 
    		fibre,String vitaminA,String potasium,String sodium) {
    	
    	
        ContentValues con = new ContentValues();
        con.put(COL_DATE_TIME, date_time);
        con.put(COL_PROTEIN, protein);
        con.put(COL_CARBS, carbs);
        con.put(COL_SUGAR, sugar);
        con.put(COL_CHOLESTROL, cholestrol);
        con.put(COL_FAT, fat);
        con.put(COL_CALORIES, calories);
        con.put(COL_FIBRE, fibre);
        con.put(COL_VITAMINA, vitaminA);
        con.put(COL_POTASIUM, potasium);
        con.put(COL_SODIUM, sodium);
        
 
        return db.insert("tablecatss", null, con);
 
    }
    
     //Method for fetching all records
    Cursor getAllRecords(){
        String[] columns = {COL_ROWID,COL_DATE_TIME,COL_PROTEIN,COL_CARBS,COL_SUGAR,COL_CHOLESTROL
        		,COL_FAT,COL_FIBRE,COL_VITAMINA,COL_POTASIUM,COL_SODIUM};
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
         
    }
    
    long insertProduct(String name,String size){
    	  ContentValues con = new ContentValues();
          con.put("name", name);
          con.put("size", size);
         
          
   
          return db.insert("product", null, con);
    	
    }
    
    Cursor getProductnames(){
    	
    	Cursor c= db.rawQuery("Select  name, size from product ORDER BY name COLLATE NOCASE",null);
    	
    	
    	return  c;
    }
    
    
    
    
  //Methods for fetching all carts
    Cursor getAllCarts(){
       //String[] columns = {COL_ROWID,COL_DATE_TIME,COL_PROTEIN,COL_CARBS,COL_SUGAR,COL_CHOLESTROL,
        		//COL_FAT,COL_FIBRE,COL_VITAMINA,COL_POTASIUM,COL_SODIUM};*/
        
        
    // return db.query(TABLE_NAME2, columns, null, null, null, null, null);
      Cursor c= db.rawQuery("Select DISTINCT cartid  from cart_items",null);   
        		return c;
    }
    
    
    /** cartsummary queries*/
    //increased
    Cursor getcartSummaryINc(String datetime){
        
    	String[] i={"0","1",datetime};
    Cursor c= db.rawQuery("Select  nutrientname, Sum(nutrientvalue)  from cart_items where " +
     		"nutrientvalue >=?  and visibility=? and cartid=? group by nutrientname",i);   
         		return c;
     }
    
  //increased
    Cursor getcartSummaryDEc(String datetime){
        
    	String[] i={"0","1",datetime};
     Cursor c= db.rawQuery("Select nutrientname, Sum(nutrientvalue)  from cart_items where " +
      		"nutrientvalue <?  and visibility=? and cartid=? group by nutrientname",i);   
       
       
         		return c;
         		
         		
     }
    
    //insert
    
    long insertSummary(String cartid, String nutrientname,String nutrientvalue, 
    		int visibility) {
    	
    	
        ContentValues con = new ContentValues();
        
        con.put(COL_CARTID, cartid);
        con.put(COL_NUTRNAME, nutrientname);
        con.put(COL_NUTRVALUE, nutrientvalue);
        con.put(COL_VISIBILITY, visibility);
        
        
        return db.insert("cart_items", null, con);
    
    }
    
    
    //protein summary
    Double getProteinSum(String date){
    	//ELECT * FROM TABLE ORDER BY ID DESC LIMIT 1
    	
    	String[] today={date};
    Cursor c= db.rawQuery("Select protein from tableone where date_time=? order by rowid desc limit 1" , today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p=c.getDouble(0);// (double) c.getInt(0);            
        }
    	return p;
    }
    
    //carbs summary
    Double getCarbsSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select carbs from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p= c.getDouble(0);            
        }
    	return p;
    }
    
    //sugar sumary
    Double getSugarSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select sugar from tableone where date_time=? order by rowid desc limit 1", today);
    	int p=1;
   
			
    	if(c.moveToFirst()) {
            p= c.getInt(0);            
        }
    	return (double) p;
    }
    
    //cholestrol summary
    Double getCholestrolSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select chlolestrol from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p= c.getDouble(0);            
        }
    	return p;
    }
    
    
    //fat summary
    Double getFatSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select fat from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p= c.getDouble(0);            
        }
    	return  p;
    }
    
  //fat summary
    Double getCaloriesSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select calories from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p=  c.getDouble(0);            
        }
    	return p;
    }
    
  //fat summary
    double getFibreSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select fibre from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p= c.getDouble(0);            
        }
    	return p;
    }
  //fat summary
    Double getVitaminASum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select vitaminA from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p= c.getDouble(0);            
        }
    	return (double) p;
    }
    
  //fat summary
    Double getPotasiumSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select potasium from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
    	   
		
    	if(c.moveToFirst()) {
            p=  c.getDouble(0);            
        }
    	return p;
    }
    
  //fat sum
    Double getSodiumSum(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select sodium from tableone where date_time=? order by rowid desc limit 1", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p= c.getDouble(0);            
        }
    	return p;
    }
    
    
    
  /**  
    
    //fat summary
    int getFibre(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(fibre) from tableone where date_time=?", today);
    	int p=0;
   
			
    	if(c.moveToFirst()) {
            p= c.getInt(0);            
        }
    	return p;
    }
    
    
    
    //  
    
     
    void deleteAllRecords(){
        db.delete(TABLE_NAME, null, null);
    }
    
     
    void deleteOneRecord(String rowid){
        db.delete(TABLE_NAME, rowid +"="+COL_ROWID, null);
    }
     
    
    
    
    /**
     * THese methods are meant to read from the carts table only
     * 
    Double getProteinCart(String date){
    	
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(protein) from tablecatss where date_time=?", today);
    	Double p=0.0;
   
			
    	if(c.moveToFirst()) {
            p= (double) c.getInt(0);            
        }
    	return p;
    }
    
    //carbs cart item
    Double getCarbsCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(carbs) from tablecatss where date_time=?", today);
    	Double p=0.0;
    	   
		
    	if(c.moveToFirst()) {
            p= (double) c.getInt(0);            
        }    	return p;
    }
    
    //sugar sumary
    Double getSugarCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(sugar) from tablecatss where date_time=?", today);
    	Double p=0.0;
    	   
		
    	if(c.moveToFirst()) {
            p= (double) c.getInt(0);            
        }
    	return p;
    }
    
    //cholestrol cart item
    Double getCholestrolCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(chlolestrol) from tablecatss where date_time=?", today);
    	Double p=0.0;
    	   
		
    	if(c.moveToFirst()) {
            p= (double) c.getInt(0);            
        }    	return p;
    }
    
    
    //fat cart item
    Double getFatCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(fat) from tablecatss where date_time=?", today);
    	Double p=0.0;
    	   
		
    	if(c.moveToFirst()) {
            p= (double) c.getInt(0);            
        }    	return p;
    }
    
  //calories cart item
    int getcCaloriesCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(calories) from tablecatss where date_time=?", today);
    	int p=0;
   
			
    	if(c.moveToFirst()) {
            p= c.getInt(0);  
          
        }
    	return p;
    }
    
  // fibre item
    int getFibreCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(fibre) from tablecatss where date_time=?", today);
    	int p=0;
   
			
    	if(c.moveToFirst()) {
            p= c.getInt(0);  
          
        }
    	return p;
    }
    
  //vitaminA cart item
    Double getVitaminACart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(vitaminA) from tablecatss where date_time=?", today);
    	Double p=0.0;
    	   
		
    	if(c.moveToFirst()) {
            p= (double) c.getInt(0);            
        }    	return p;
    }
    
  //potasium cart item
    int getPotasiumCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(potasium) from tablecatss where date_time=?", today);
    	int p=0;
   
			
    	if(c.moveToFirst()) {
            p= c.getInt(0);  
          
        }
    	return p;
    }
    
  //sodium cart item
    int getsodiumCart(String date){
    	String[] today={date};
    	Cursor c= db.rawQuery("Select SUM(sodim) from tablecatss where date_time=?", today);
    	int p=0;
   
			
    	if(c.moveToFirst()) {
            p= c.getInt(0);  
          
        }
    	return p;
    }**/
    
    //Delete methods
    public void DeleteSumming(){
    	db.delete("cart_items", null, null);
    	//db.rawQuery("delet * from cart_items", null);
    }
    
    public void DeleteAllnutrients(){
    	db.delete(TABLE_NAME, null, null);
    
    }
    
    
    
    
 
     
    
    class DBHelper extends SQLiteOpenHelper {
 
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }
 
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
        	
        String CREATE_TABLE = "create table tableone(rowid INTEGER PRIMARY KEY AUTOINCREMENT, " +
            		"date_time TEXT NOT NULL,protein TEXT,carbs TEXT,sugar TEXT, chlolestrol TEXT,fat " +
            		"TEXT,calories TEXT,fibre TEXT,vitaminA TEXT,sodium TEXT,potasium TEXT)";
         
        String CREATE_TABLE2 = "create table tablecatss(rowid INTEGER PRIMARY KEY AUTOINCREMENT, " +
            		"date_time TEXT NOT NULL,protein TEXT,carbs TEXT,sugar TEXT, chlolestrol TEXT,fat " +
            		"TEXT,calories TEXT,fibre TEXT,vitaminA TEXT,sodium TEXT,potasium TEXT)";
        
        String CREATE_TABLE3 = "create table cart_items(rowid INTEGER PRIMARY KEY AUTOINCREMENT," +
        		"cartid TEXT NOT NULL, " +
        		"nutrientname TEXT,nutrientvalue TEXT,visibility INTEGER)";
        
     String CREATE_PRODUCT = "create table product(rowid INTEGER PRIMARY KEY AUTOINCREMENT,"
     + "name TEXT,size TEXT )";
        
        
    
             
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_PRODUCT);
        	
            
            
         //vdb.close();   
        }
 
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
 
        }
 
    }
 
}