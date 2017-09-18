package com.gruposet.ecommerce.daos;

import java.util.ArrayList;

public interface InterfaceDao {
    
    public void insert();
    
    public void update();
    
    public void select();
    
    public void delete();
    
    public void list(String condition);
}
