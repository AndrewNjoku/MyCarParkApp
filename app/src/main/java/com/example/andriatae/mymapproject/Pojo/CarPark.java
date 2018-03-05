package com.example.andriatae.mymapproject.Pojo;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class CarPark extends RealmObject{

        @PrimaryKey
        @SerializedName("id")
        private Integer id;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lng")
        @Expose
        private String lng;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("cost_per_minute")
        @Expose
        private String costPerMinute;
        @SerializedName("max_reserve_time_mins")
        @Expose
        private Integer maxReserveTimeMins;
        @SerializedName("min_reserve_time_mins")
        @Expose
        private Integer minReserveTimeMins;
        @SerializedName("is_reserved")
        @Expose
        private Boolean isReserved;
        @SerializedName("reserved_until")
        @Expose
        private String reservedUntil;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCostPerMinute() {
            return costPerMinute;
        }

        public void setCostPerMinute(String costPerMinute) {
            this.costPerMinute = costPerMinute;
        }

        public Integer getMaxReserveTimeMins() {
            return maxReserveTimeMins;
        }

        public void setMaxReserveTimeMins(Integer maxReserveTimeMins) {
            this.maxReserveTimeMins = maxReserveTimeMins;
        }

        public Integer getMinReserveTimeMins() {
            return minReserveTimeMins;
        }

        public void setMinReserveTimeMins(Integer minReserveTimeMins) {
            this.minReserveTimeMins = minReserveTimeMins;
        }

        public Boolean getIsReserved() {
            return isReserved;
        }

        public void setIsReserved(Boolean isReserved) {
            this.isReserved = isReserved;
        }

        public String getReservedUntil() {
            return reservedUntil;
        }

        public void setReservedUntil(String reservedUntil) {
            this.reservedUntil = reservedUntil;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("id", id).append("lat", lat).append("lng", lng).append("name", name).append("costPerMinute", costPerMinute).append("maxReserveTimeMins", maxReserveTimeMins).append("minReserveTimeMins", minReserveTimeMins).append("isReserved", isReserved).append("reservedUntil", reservedUntil).toString();
        }

    }