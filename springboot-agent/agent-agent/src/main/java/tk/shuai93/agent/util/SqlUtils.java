package tk.shuai93.agent.util;



import tk.shuai93.agent.properties.DbProperties;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public enum SqlUtils {

    INSTANCE;

    private Log log = LogFactory.getLog(SqlUtils.class);

    private DbProperties dbProperties;

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    public SqlUtils build(DbProperties dbProperties){
        this.dbProperties = dbProperties;
        return this;
    }


    public Connection getConn() {
        if(threadLocal.get() == null){
            Connection conn = null;
            try {
                Class.forName(dbProperties.getDriverClassName());
                conn = DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getUserName(), dbProperties.getPassword());
                threadLocal.set(conn);
            } catch (Exception e) {
                log.error("获取连接失败：" + e.getMessage(),e);
            }
            return conn;
        }

        return threadLocal.get();
    }

    public void closeConn(Connection conn) {
        if(conn != null){
            DbUtils.closeQuietly(conn);
            threadLocal.remove();
        }
    }


}