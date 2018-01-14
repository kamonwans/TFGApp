package com.project.finalyear.thaispellinggame.model;

/**
 * Created by Namwan on 11/20/2017.
 */
import android.view.View;

import com.project.finalyear.thaispellinggame.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserModel {

    public String name;
    public String image;
    public boolean status;

    public UserModel() {

    }

    public UserModel(String name, String image ,boolean status) {
        this.name = name;
        this.image = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
