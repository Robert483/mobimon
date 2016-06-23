package Object;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.alarmnotification.mobimon.R;
import com.firebase.client.DataSnapshot;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import Document.Utility;

/**
 * Created by Ryan L. Vu on 6/11/2016.
 */
public class Equipment extends Item {

    private int atk;
    private int def;
    private String largeImage;
    private String LinklargeImage;
    private String type;

    public Equipment(DataSnapshot snapshot) {
        this.setName(snapshot.child("name").getValue().toString());
        this.setDescription(snapshot.child("description").getValue().toString());
        this.setLinkimage(snapshot.child("image").getValue().toString());

        this.setBuyPrice(Integer.parseInt(snapshot.child("buyPrice").getValue().toString()));
        this.setSellPrice(Integer.parseInt(snapshot.child("sellPrice").getValue().toString()));

        this.atk = Integer.parseInt(snapshot.child("atk").getValue().toString());
        this.def = Integer.parseInt(snapshot.child("def").getValue().toString());
        this.setLinklargeImage(snapshot.child("largeImg").getValue().toString());
        this.setType(snapshot.getKey());
        this.setStatus("server");
    }

    public Equipment() {
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public String getDetail() {
        String detail = "Mô tả: " + getDescription()
                + "\nSức tấn công: " + getAtk()
                + "\nSức phòng thủ: " + getDef()
                + "\nGiá mua: " + getBuyPrice()
                + "\nGiá bán: " + getSellPrice();
        return detail;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getLinklargeImage() {
        return LinklargeImage;
    }

    public void setLinklargeImage(String linklargeImage) {
        LinklargeImage = linklargeImage;
    }

    public void addLargeImageToImageView(ImageView imageView, Context context){
        if(getLargeImage() != null && getLargeImage().equals("")==false){
            Bitmap bitmap = Utility.getImageBitmap(context, getLargeImage());
            imageView.setImageBitmap(bitmap);
        }
        else {
            UrlImageViewHelper.setUrlDrawable(imageView, getLinklargeImage(), R.mipmap.ic_launcher);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
