package jvd.ir.digiknew.Main;

import java.util.List;

import jvd.ir.digiknew.Model.Slider;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    List<Slider> sliders;

    public MainSliderAdapter(List<Slider> sliders){
        this.sliders =sliders;

    }

    @Override
    public int getItemCount() {
        return sliders.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {

        viewHolder.bindImageSlide(sliders.get(position).getmUrlSlider());

    }
}
