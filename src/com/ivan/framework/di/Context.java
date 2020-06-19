package com.ivan.framework.di;

import java.util.HashMap;
import java.util.Map;

public class Context {
    public Map<String, Object> mapObject;

    public Context(){
        mapObject = new HashMap<>();
    }

    public void parseOurXml() {
        
    }

    public Object getById(String object) {
        return mapObject.get(object);
    }
}
