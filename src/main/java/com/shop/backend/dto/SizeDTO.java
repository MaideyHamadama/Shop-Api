package com.shop.backend.dto;

import com.shop.backend.entity.AdultSize;
import com.shop.backend.entity.ChildSize;
import com.shop.backend.entity.Size;
import com.shop.backend.entity.SizeType;

public class SizeDTO {
    private SizeType type;
    private AdultSize adultSize;
    private ChildSize childSize;

    public SizeDTO() {
    }

    public SizeDTO(Size size) {
        this.type = size.getType();
        this.adultSize = size.getAdultSize();
        this.childSize = size.getChildSize();
    }


    public SizeType getType() {
        return type;
    }

    public void setType(SizeType type) {
        this.type = type;
    }

    public AdultSize getAdultSize() {
        return adultSize;
    }

    public void setAdultSize(AdultSize adultSize) {
        this.adultSize = adultSize;
    }

    public ChildSize getChildSize() {
        return childSize;
    }

    public void setChildSize(ChildSize childSize) {
        this.childSize = childSize;
    }
}