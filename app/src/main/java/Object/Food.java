package Object;

import com.firebase.client.DataSnapshot;

/**
 * Created by Ryan L. Vu on 6/11/2016.
 */
public class Food extends Item {

    private int hp;

    public Food(DataSnapshot snapshot) {
        this.setName(snapshot.child("name").getValue().toString());
        this.setDescription(snapshot.child("description").getValue().toString());
        this.setLinkimage(snapshot.child("image").getValue().toString());


        this.setBuyPrice(Integer.parseInt(snapshot.child("buyPrice").getValue().toString()));
        this.setSellPrice(Integer.parseInt(snapshot.child("sellPrice").getValue().toString()));
        this.hp = Integer.parseInt(snapshot.child("hp").getValue().toString());
        this.setStatus("server");
    }

    public Food() {
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getDetail() {
        String detail = "Mô tả: " + getDescription()
                + "\nHồi máu: " + getHp()
                + "\nGiá mua: " + getBuyPrice()
                + "\nGiá bán: " + getSellPrice();
        return detail;
    }

}
