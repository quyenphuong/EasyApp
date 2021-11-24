package com.example.easyapp.model;

import java.io.Serializable;

    public class SeriaModel  implements Serializable {

        int image;
        String name;


        public SeriaModel (int image, String name) {
            this.image = image;
            this.name = name;


        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }




