package Object;

import android.graphics.Bitmap;

import com.firebase.client.DataSnapshot;

/**
 * Created by Ryan L. Vu on 6/11/2016.
 */
public class Equipment extends Item {

    private int atk;
    private int def;
    private Bitmap largeImage;

    public Equipment(DataSnapshot snapshot) {
        this.setName(snapshot.child("name").getValue().toString());
        this.setDescription(snapshot.child("description").getValue().toString());
        String img = snapshot.child("image").getValue().toString();
        //this.setImage(Item.getBitmapFromURL(img));

        this.setBuyPrice(Integer.parseInt(snapshot.child("buyPrice").getValue().toString()));
        this.setSellPrice(Integer.parseInt(snapshot.child("sellPrice").getValue().toString()));

        this.atk = Integer.parseInt(snapshot.child("atk").getValue().toString());
        this.def = Integer.parseInt(snapshot.child("def").getValue().toString());
        String largeimg = snapshot.child("largeImg").getValue().toString();
        //this.largeImage = Item.getBitmapFromURL(largeimg);
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

    public Bitmap getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(Bitmap largeImage) {
        this.largeImage = largeImage;
    }

    public String getDetail() {
        String detail = "Mô tả: " + getDescription()
                + "\nSức tấn công: " + getAtk()
                + "\nSức phòng thủ: " + getDef()
                + "\nGiá mua: " + getBuyPrice()
                + "\nGiá bán: " + getSellPrice();
        return detail;
    }

}
