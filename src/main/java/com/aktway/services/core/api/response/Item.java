package com.aktway.services.core.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Serializable {

    private String id;
    private String k;
    private String category;
    private List<String> vt;
    private List<Integer> sc;
    //private Flags flags;
    private String se;

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public List<String> getVt() {
        return vt;
    }

    public void setVt(List<String> vt) {
        this.vt = vt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Integer> getSc() {
        return sc;
    }

    public void setSc(List<Integer> sc) {
        this.sc = sc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return k.equals(item.k);

    }

    @Override
    public int hashCode() {
        return k.hashCode();
    }

    @Override
    public String toString() {
        return "Item{" +
                ", id='" + id + '\'' +
                "k='" + k + '\'' +
                ", category='" + category + '\'' +
                ", vt=" + vt +
                ", sc=" + sc +
                ", se=" + se +

                '}';
    }

}
