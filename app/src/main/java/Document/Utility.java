package Document;

import android.content.Context;
import android.content.ContextWrapper;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SonPham on 6/22/2016.
 */
public class Utility {

    public static void saveImage(Context context, Bitmap b,String name){

        FileOutputStream out;
        try {
            out = context.openFileOutput(name, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getImageBitmap(Context context,String name){

        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        }
        catch(Exception e){
        }
        return null;
    }

    public static Bitmap getBitmapFromAssets(String fileName, Context context) {
        AssetManager assetManager = context.getAssets();

        InputStream istr = null;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(istr);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }
}
