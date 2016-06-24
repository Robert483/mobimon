package Interface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SonPham on 6/24/2016.
 */
public interface SaleListenner {

    public void buyItemCompleted(int price);
    public void sellItemCompleted(int price);
}
