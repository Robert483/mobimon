package Object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * Created by Thai Son on 18/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static String DB_PATH = "/data/data/com.alarmnotification.mobimon/databases/";
    public static String DB_NAME = "Mobimon.sqlite";
    public static final int DB_VERSION = 1;

    public static final String TB_USER = "Users";

    private SQLiteDatabase myDB;
    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    @Override
    public synchronized void close(){
        if(myDB!=null){
            myDB.close();
        }
        super.close();
    }

    /***
     * Open database
     * @throws android.database.SQLException
     */
    public void openDataBase() throws android.database.SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /***
     * Copy database from source code assets to device
     * @throws IOException
     */
    public void copyDataBase() throws IOException{
        try {
            InputStream myInput = context.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;

            while((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.e("tle99 - copyDatabase", e.getMessage());
        }

    }

    /***
     * Check if the database doesn't exist on device, create new one
     * @throws IOException
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("tle99 - create", e.getMessage());
            }
        }
    }

    // ---------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------

    /***
     * Check if the database is exist on device or not
     * @return
     */
    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.e("tle99 - check", e.getMessage());
        }
        if (tempDB != null)
            tempDB.close();
        return tempDB != null ? true : false;
    }


    public Equipment CurrsorToEquipment(Cursor res){
        Equipment equipment = new Equipment();

        String name = res.getString(res.getColumnIndex("name"));
        String description = res.getString(res.getColumnIndex("description"));
        String image = res.getString(res.getColumnIndex("image"));
        int buyPrice = res.getInt(res.getColumnIndex("buyPrice"));
        int sellPrice = res.getInt(res.getColumnIndex("sellPrice"));
        int atk = res.getInt(res.getColumnIndex("atk"));
        int def = res.getInt(res.getColumnIndex("def"));
        String largeImage = res.getString(res.getColumnIndex("largeImage"));
        String status = res.getString(res.getColumnIndex("status"));
        int id = res.getInt(res.getColumnIndex("id"));
        String type = res.getString(res.getColumnIndex("type"));

        equipment.setName(name);
        equipment.setDescription(description);
        equipment.setImage(image);
        equipment.setBuyPrice(buyPrice);
        equipment.setSellPrice(sellPrice);
        equipment.setAtk(atk);
        equipment.setDef(def);
        equipment.setLargeImage(largeImage);
        equipment.setStatus(status);
        equipment.setId(id);
        equipment.setType(type);

        return equipment;
    }

    public Food CurrsorToFood(Cursor res){
        Food food = new Food();

        String name = res.getString(res.getColumnIndex("name"));
        String description = res.getString(res.getColumnIndex("description"));
        String image = res.getString(res.getColumnIndex("image"));
        int buyPrice = res.getInt(res.getColumnIndex("buyPrice"));
        int sellPrice = res.getInt(res.getColumnIndex("sellPrice"));
        int hp = res.getInt(res.getColumnIndex("hp"));
        String status = res.getString(res.getColumnIndex("status"));
        int id = res.getInt(res.getColumnIndex("id"));

        food.setName(name);
        food.setDescription(description);
        food.setImage(image);
        food.setBuyPrice(buyPrice);
        food.setSellPrice(sellPrice);
        food.setHp(hp);
        food.setStatus(status);
        food.setId(id);

        return food;
    }

    public Equipment getCurrentEquipment(String type){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from CurrentSet where type = '" + type + "'", null);
        res.moveToFirst();

        Equipment equipment = CurrsorToEquipment(res);
        return equipment;
    }

    public Equipment getCurrentHead(){
        return getCurrentEquipment("head");
    }

    public Equipment getCurrentBody(){
        return getCurrentEquipment("body");
    }

    public Equipment getCurrentFoot(){
        return getCurrentEquipment("foot");
    }

    public Equipment getCurrentWing(){
        return getCurrentEquipment("wing");
    }

    public void saveCurrentEquipment(Equipment eq, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", eq.getName());
        contentValues.put("description", eq.getDescription());
        contentValues.put("image", eq.getImage());
        contentValues.put("buyPrice", eq.getBuyPrice());
        contentValues.put("sellPrice", eq.getSellPrice());
        contentValues.put("atk", eq.getAtk());
        contentValues.put("def", eq.getDef());
        contentValues.put("largeImage", eq.getLargeImage());
        contentValues.put("status", eq.getStatus());



        db.update("Currentset", contentValues, "type = ? ", new String[]{type});

    }

    public void saveCurrentHead(Equipment eq){
        saveCurrentEquipment(eq, "head");
    }

    public void saveCurrentBody(Equipment eq){
        saveCurrentEquipment(eq,"body");
    }

    public void saveCurrentFoot(Equipment eq){
        saveCurrentEquipment(eq,"foot");
    }

    public void saveCurrentWing(Equipment eq){
        saveCurrentEquipment(eq, "wing");
    }


    public ArrayList<Equipment> getAllOwnedEquipment(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Equipment where form = 'bag'", null);
        res.moveToFirst();

        ArrayList<Equipment> equipments = new ArrayList<>();
        for(int i=0;i< res.getCount();i++) {
            equipments.add(CurrsorToEquipment(res));
            res.moveToNext();
        }
        return equipments;
    }

    public ArrayList<Equipment> getAllOwnedEquipment(String type){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Equipment where type = '" + type +
                "' and form = 'bag'", null);
        res.moveToFirst();


        ArrayList<Equipment> equipments = new ArrayList<>();
        for(int i=0;i< res.getCount();i++) {
            equipments.add(CurrsorToEquipment(res));
            res.moveToNext();
        }
        return equipments;
    }

    public ArrayList<Equipment> getAllOwnedHead(){
        return getAllOwnedEquipment("head");
    }

    public ArrayList<Equipment> getAllOwnedBody(){
        return getAllOwnedEquipment("body");
    }

    public ArrayList<Equipment> getAllOwnedFoot(){
        return getAllOwnedEquipment("foot");
    }

    public ArrayList<Equipment> getAllOwnedWing(){
        return getAllOwnedEquipment("wing");

    }



    public ArrayList<Food> getAllOwnedFood(){
        if(!checkDataBase())
            return null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Food where form = 'bag'", null);
        res.moveToFirst();

        ArrayList<Food> foods = new ArrayList<Food>();
        for(int i=0;i< res.getCount();i++) {
            foods.add(CurrsorToFood(res));
            res.moveToNext();
        }
        return foods;
    }

    public ArrayList<Equipment> getAllStoreEquipment(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Equipment where form = 'store'", null);
        res.moveToFirst();

        ArrayList<Equipment> equipments = new ArrayList<>();
        for(int i=0;i< res.getCount();i++) {
            equipments.add(CurrsorToEquipment(res));
            res.moveToNext();
        }
        return equipments;
    }


    public ArrayList<Equipment> getAllStoreEquipment(String type){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Equipment where type = '" + type +
                "' , form = 'store'", null);
        res.moveToFirst();

        ArrayList<Equipment> equipments = new ArrayList<>();
        for(int i=0;i< res.getCount();i++) {
            equipments.add(CurrsorToEquipment(res));
            res.moveToNext();
        }
        return equipments;
    }

    public ArrayList<Equipment> getAllStoreHead(){
        return getAllStoreEquipment("head");
    }

    public ArrayList<Equipment> getAllStoreBody(){
        return getAllStoreEquipment("body");
    }

    public ArrayList<Equipment>getAllStoreFoot(){
        return getAllStoreEquipment("foot");
    }

    public ArrayList<Equipment> getAllStoreWing(){
        return getAllStoreEquipment("wing");
    }

    public ArrayList<Food> getAllStoreFood(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Food where form = 'store'", null);
        res.moveToFirst();

        ArrayList<Food> foods = new ArrayList<>();
        for(int i=0;i< res.getCount();i++) {
            foods.add(CurrsorToFood(res));
            res.moveToNext();
        }
        return foods;
    }


    public void deleteItem(Item item) {
        if(item.getClass()==Food.class){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Food", "id = ? ", new String[] { item.getId()+"" });
        }
        else {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Equipment", "id = ? ", new String[] { item.getId()+"" });
        }
    }

    public void saveItemToBag(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(item.getClass() == Food.class){
            Food food = (Food) item;
            contentValues.put("name", food.getName());
            contentValues.put("description", food.getDescription());
            contentValues.put("image", food.getImage());
            contentValues.put("buyPrice", food.getBuyPrice());
            contentValues.put("sellPrice", food.getSellPrice());
            contentValues.put("hp", food.getHp());
            contentValues.put("form", "bag");
            contentValues.put("status", food.getStatus());

            long a = db.insert("Food", null, contentValues);
            a = a++;
        }
        else {
            Equipment eq = (Equipment) item;
            contentValues.put("name", eq.getName());
            contentValues.put("description", eq.getDescription());
            contentValues.put("image", eq.getImage());
            contentValues.put("buyPrice", eq.getBuyPrice());
            contentValues.put("sellPrice", eq.getSellPrice());
            contentValues.put("atk", eq.getAtk());
            contentValues.put("def", eq.getDef());
            contentValues.put("largeImage", eq.getLargeImage());
            contentValues.put("form", "bag");
            contentValues.put("status", eq.getStatus());
            contentValues.put("type", eq.getType());

            long a = db.insert("Equipment", null, contentValues);
            a = a++;
        }



    }
}
