package com.dongverine.wol.model;

import lombok.Data;

@Data
public class MacAddr {
    String name;
    String ip;
    String mac;
    public MacAddr(String name,String mac){
        this.name = name;
        this.mac = mac;
    }
}
