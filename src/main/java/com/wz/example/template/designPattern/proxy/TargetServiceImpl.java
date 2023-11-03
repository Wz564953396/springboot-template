package com.wz.example.template.designPattern.proxy;

import com.wz.example.template.designPattern.proxy.ITargetService;
import org.springframework.stereotype.Service;

@Service
public class TargetServiceImpl implements ITargetService {


    @Override
    public void execute() {
        System.out.println("TargetServiceImpl.execute()...");
    }
}
