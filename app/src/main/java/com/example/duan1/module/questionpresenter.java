package com.example.duan1.module;

import android.content.Context;



public class questionpresenter {
    private appData data;


    public questionpresenter(appData data) {
        this.data = data;
    }

    public void support() {
        data.getSuggest();
    }

    public void supDia() {
        data.goToSupportDialog();
    }

    public void itemDia(Context context) {
  data.goToItemDialog();
    }

    public void music() {
        data.setIvMusic();
    }
   public  void  getIMG()
   {
       data.getImageDrawable();
   }

}
