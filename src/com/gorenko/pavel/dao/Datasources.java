package com.gorenko.pavel.dao;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "datasources")
@XmlRootElement
public class Datasources {
    public List<Datasource> datasource = new ArrayList<Datasource>();
}
