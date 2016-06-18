package Object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Thai Son on 18/06/2016.
 */
public class DAO {

    private Context context;
    private static final String TAG_CURRENT = "CurrentSet";
    public DAO(Context context)
    {
        this.context = context;
    }

    public Equipment getCurrentHead()
    {
        Equipment head = new Equipment();
        String jsonText = readFromFile(context);
        if(jsonText == "")
        {
            try {
                // get input stream for text
                InputStream is = context.getAssets().open("blazing-fire-3588-export.json");
                // check size
                int size = is.available();
                // create buffer for IO
                byte[] buffer = new byte[size];
                // get data to buffer
                is.read(buffer);
                // close stream
                is.close();
                // set result to TextView
                jsonText = new String(buffer);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("map.json", context.MODE_PRIVATE));
                outputStreamWriter.write(jsonText);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(jsonText != "")
        {
            try {
                JSONObject jRoot = new JSONObject(jsonText);
                JSONObject curr = jRoot.getJSONObject(TAG_CURRENT);
                JSONObject jhead = curr.getJSONObject("Head");
                head.setAtk(jhead.getInt("atk"));
                head.setDef(jhead.getInt("def"));
                if(jhead.getString("largeImg") != "")
                {
                    File imgFile = new  File(jhead.getString("largeImg"));

                    if(imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        head.setLargeImage(myBitmap);

                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return head;
    }

    public Equipment getCurrentBody()
    {
        Equipment body = new Equipment();
        String jsonText = readFromFile(context);
        if(jsonText == "")
        {
            try {
                // get input stream for text
                InputStream is = context.getAssets().open("blazing-fire-3588-export.json");
                // check size
                int size = is.available();
                // create buffer for IO
                byte[] buffer = new byte[size];
                // get data to buffer
                is.read(buffer);
                // close stream
                is.close();
                // set result to TextView
                jsonText = new String(buffer);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("map.json", context.MODE_PRIVATE));
                outputStreamWriter.write(jsonText);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(jsonText != "")
        {
            try {
                JSONObject jRoot = new JSONObject(jsonText);
                JSONObject curr = jRoot.getJSONObject(TAG_CURRENT);
                JSONObject jhead = curr.getJSONObject("Body");
                body.setAtk(jhead.getInt("atk"));
                body.setDef(jhead.getInt("def"));
                if(jhead.getString("largeImg") != "")
                {
                    File imgFile = new  File(jhead.getString("largeImg"));

                    if(imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        body.setLargeImage(myBitmap);

                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return body;
    }

    public Equipment getCurrentFoot()
    {
        Equipment foot = new Equipment();
        String jsonText = readFromFile(context);
        if(jsonText == "")
        {
            try {
                // get input stream for text
                InputStream is = context.getAssets().open("blazing-fire-3588-export.json");
                // check size
                int size = is.available();
                // create buffer for IO
                byte[] buffer = new byte[size];
                // get data to buffer
                is.read(buffer);
                // close stream
                is.close();
                // set result to TextView
                jsonText = new String(buffer);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("map.json", context.MODE_PRIVATE));
                outputStreamWriter.write(jsonText);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(jsonText != "")
        {
            try {
                JSONObject jRoot = new JSONObject(jsonText);
                JSONObject curr = jRoot.getJSONObject(TAG_CURRENT);
                JSONObject jhead = curr.getJSONObject("Foot");
                foot.setAtk(jhead.getInt("atk"));
                foot.setDef(jhead.getInt("def"));
                if(jhead.getString("largeImg") != "")
                {
                    File imgFile = new  File(jhead.getString("largeImg"));

                    if(imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        foot.setLargeImage(myBitmap);

                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return foot;
    }

    public Equipment getCurrentWing()
    {
        Equipment wing = new Equipment();
        String jsonText = readFromFile(context);
        if(jsonText == "")
        {
            try {
                // get input stream for text
                InputStream is = context.getAssets().open("blazing-fire-3588-export.json");
                // check size
                int size = is.available();
                // create buffer for IO
                byte[] buffer = new byte[size];
                // get data to buffer
                is.read(buffer);
                // close stream
                is.close();
                // set result to TextView
                jsonText = new String(buffer);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("data.json", context.MODE_PRIVATE));
                outputStreamWriter.write(jsonText);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(jsonText != "")
        {
            try {
                JSONObject jRoot = new JSONObject(jsonText);
                JSONObject curr = jRoot.getJSONObject(TAG_CURRENT);
                JSONObject jhead = curr.getJSONObject("Wing");
                wing.setAtk(jhead.getInt("atk"));
                wing.setDef(jhead.getInt("def"));
                if(jhead.getString("largeImg") != "")
                {
                    File imgFile = new  File(jhead.getString("largeImg"));

                    if(imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        wing.setLargeImage(myBitmap);

                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return wing;
    }

    private static String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("data.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
