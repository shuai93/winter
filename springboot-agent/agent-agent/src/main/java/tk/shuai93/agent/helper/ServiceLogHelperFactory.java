package tk.shuai93.agent.helper;


import com.mysql.cj.util.StringUtils;
import tk.shuai93.agent.model.ServiceLog;
import tk.shuai93.agent.properties.DbProperties;
import tk.shuai93.agent.properties.ServiceLogProperties;

import tk.shuai93.agent.util.JsonUtils;
import tk.shuai93.agent.util.SqlUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class ServiceLogHelperFactory {

    private Properties properties;

    public ServiceLogHelperFactory(Properties properties) {
        this.properties = properties;
    }


    public ServiceLogHelper getServiceLogHelper(){
        SqlUtils sqlUtils = SqlUtils.INSTANCE.build(getDbProperties());
        return ServiceLogHelper.INSTACNE.build(sqlUtils);
    }

    private DbProperties getDbProperties(){
        String userName = properties.getProperty(DbProperties.USER_NAME_KEY);
        String password = properties.getProperty(DbProperties.PASSWORD_KEY);
        String url = properties.getProperty(DbProperties.URL_KEY);
        if(StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password) || StringUtils.isNullOrEmpty(url)){
            throw new RuntimeException("userName or password or url is empty");
        }
        return new DbProperties(url,userName,password);
    }

    public ServiceLogProperties getServiceLogProperties(){
        String skipLogServiceNames = properties.getProperty(ServiceLogProperties.SKIP_LOG_SERVICE_NAMES_KEY);
        if(StringUtils.isNullOrEmpty(skipLogServiceNames)){
            return new ServiceLogProperties();
        }

        List<String> skipLogServiceNameList = new ArrayList<>();
        if(skipLogServiceNames.contains(ServiceLogProperties.SPITE)){
            String[] skipLogServiceNameArr = skipLogServiceNames.split(ServiceLogProperties.SPITE);
            skipLogServiceNameList = Arrays.asList(skipLogServiceNameArr);
        }else{
            skipLogServiceNameList.add(skipLogServiceNames);
        }

        return new ServiceLogProperties(skipLogServiceNameList);
    }



    public ServiceLog createServiceLog(Object[] args, Method method){
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setMethodName(method.getName());
        serviceLog.setReqArgs(JsonUtils.array2json(args));
        serviceLog.setServiceName(method.getDeclaringClass().getName());
        return serviceLog;

    }
}