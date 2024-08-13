package com.example.ghostfishingnet.Savior.application.models;

public class FilterMenuItem {

    public TableFilterItem getValue() {
        return value;
    }

    public void setValue(TableFilterItem value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private  TableFilterItem  value;
 private   String label;

   public FilterMenuItem(TableFilterItem value, String label){
       this.label = label;
       this.value = value;
   }
}
