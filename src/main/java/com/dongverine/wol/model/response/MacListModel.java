package com.dongverine.wol.model.response;

import com.dongverine.wol.model.MacAddr;
import lombok.Getter;

import java.util.List;

@Getter
public class MacListModel {
    private List<MacAddr> list;
    private String error = null;
    public MacListModel(List<MacAddr> list){
        this.list = list;
    }
}
