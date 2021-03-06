package kale.db;

import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;
import kale.db.model.NewsInfo;
import kale.db.network.NetworkService;
import kale.dbinding.data.ObservableBitmap;
import kale.dbinding.data.ObservableCharSequence;
import kale.dbinding.data.ObservableArrayList;

/**
 * @author Kale
 * @date 2015/12/24
 */
class GameListPresenter {

    private final ObservableArrayList<NewsInfo> mList; // 界面的数据对象

    GameListPresenter() {
        mList = new ObservableArrayList<>();
    }

    /**
     * 这个当然可以放在构造方法中进行，我这里为了说明view层调用p的方法，强制加入了一个回调。
     */
    boolean init(final Activity activity, ObservableCharSequence name, ObservableBitmap bmp) {
        name.set("Need for Speed");
        // 一般图片加载都是用url，这里为了简单用了bitmap
        bmp.set(BitmapFactory.decodeResource(activity.getResources(), R.drawable.speed_icon));
        return true;
    }

    void loadData() {
        List<NewsInfo> data = NetworkService.loadDataFromNetwork();
        // load data from network
        mList.addAll(0, data); // don't need to call notifyDataSetChanged()
    }

    RecyclerView.Adapter getAdapter() {
        return new CommonRcvAdapter<NewsInfo>(mList) {
            @NonNull
            @Override
            public AdapterItem<NewsInfo> createItem(Object o) {
                return new GameItem();
            }
        };
    }

}
    