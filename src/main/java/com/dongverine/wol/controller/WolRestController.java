package com.dongverine.wol.controller;

import com.dongverine.wol.model.MacAddr;
import com.dongverine.wol.model.response.MacListModel;
import com.dongverine.wol.service.WolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class WolRestController {
    @Autowired
    WolService wolService;

    @RequestMapping(
            value="/wol/ajax/getWolList"
            ,method={RequestMethod.POST,RequestMethod.GET}
            ,produces="application/json;charset=utf8"
            )
    public ResponseEntity<MacListModel> getWolList(){
        List<MacAddr> comList = wolService.getList();
        //HttpHeaders headers= new HttpHeaders();
        //headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        //return new ResponseEntity<>(new RestMacListModel(comList), headers, HttpStatus.OK);
        return new ResponseEntity<>(new MacListModel(comList), HttpStatus.OK);
    }

    @RequestMapping(
            value="/wol/ajax/sendWol"
            ,method={RequestMethod.POST}
            ,produces="application/json;charset=utf8")
    public void sendWol(Integer key){
        log.info("sendWol key : {}",key);
        MacAddr mac = wolService.getList().get(key);
        String macAddr = mac.getMac();
        log.info("sendWol macAddr : {}",macAddr);
        wolService.actionWol(macAddr);
    }
}
