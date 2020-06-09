package jvd.ir.digiknew.Main;

import java.util.List;

import jvd.ir.digiknew.Model.Banner;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainBannerAdapter extends SliderAdapter {

    List<Banner> banners;

    public MainBannerAdapter(List<Banner> banners){
        this.banners =banners;

    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {

        viewHolder.bindImageSlide(banners.get(position).getmUrlBanner());

    }


}
