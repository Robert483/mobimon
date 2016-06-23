package Object;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.alarmnotification.mobimon.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import Document.Utility;

/**
 * Created by Ryan L. Vu on 6/11/2016.
 */
public abstract class Item {

    private int    buyPrice;
    private int    sellPrice;
    private String name;
    private String description;
    private String image;
    private String Linkimage;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getLinkimage() {
        return Linkimage;
    }

    public void setLinkimage(String linkimage) {
        Linkimage = linkimage;
    }

    public String getDetail() {
        return null;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addImageToImageView(ImageView imageView, Context context){
        if(getImage() != null && getImage().equals("")==false){
            Bitmap bitmap = null;
            if(getStatus().equals("server")){
                bitmap = Utility.getImageBitmap(context, getImage());
            }
            else {
                bitmap = Utility.getBitmapFromAssets(getImage(), context);
            }

            imageView.setImageBitmap(bitmap);
        }
        else {
            UrlImageViewHelper.setUrlDrawable(imageView, getLinkimage(), R.mipmap.ic_launcher);
        }
    }

    public Bitmap getBitmapImage(Context context){
        Bitmap bitmap = null;
        if(getImage() != null && getImage().equals("")==false){

            if(getStatus().equals("server")){
                bitmap = Utility.getImageBitmap(context, getImage());
            }
            else {
                bitmap = Utility.getBitmapFromAssets(getImage(), context);
            }
        }
        return  bitmap;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
