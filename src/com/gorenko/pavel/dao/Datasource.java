package com.gorenko.pavel.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "datasource")
public class Datasource {

    @XmlElement(name = "source-name") public String sourceName;
    @XmlElement(name = "connection-url") public String connectionUrl;
    @XmlElement(name = "driver-class") public String driverClass;
    @XmlElement(name = "user-name") public String userName;
    @XmlElement(name = "password") public String password;

    public Datasource() {
    }

    public Datasource(String sourceName, String connectionUrl, String driverClass, String userName, String password) {
        this.sourceName = sourceName;
        this.connectionUrl = connectionUrl;
        this.driverClass = driverClass;
        this.userName = userName;
        this.password = password;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Datasources{" +
                "sourceName='" + sourceName + '\'' +
                ", connectionUrl='" + connectionUrl + '\'' +
                ", driverClass='" + driverClass + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
