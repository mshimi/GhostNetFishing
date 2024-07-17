package com.example.ghostfishingnet.beans;

import jakarta.inject.Named;

public class FilterMenuItem {

    public FilterMenuBean.TableFilterItem getValue() {
        return value;
    }

    public void setValue(FilterMenuBean.TableFilterItem value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private  FilterMenuBean.TableFilterItem  value;
 private   String label;

   public FilterMenuItem(FilterMenuBean.TableFilterItem  value, String label){
       this.label = label;
       this.value = value;
   }
}
