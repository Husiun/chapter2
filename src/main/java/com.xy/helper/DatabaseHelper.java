package com.xy.helper;

import com.xy.util.PropUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

/**
 * Created by issuser on 2017/9/24.
 *
 * 数据库连接，加载的帮助类，为了代码的复用，添加这个类
 *
 *
 */
public class DatabaseHelper {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
    //用ThreadLocal管理connection
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private static final QueryRunner queryRunner = new QueryRunner();//QueryRunner
    //获取数据库连接的参数加载
    private static final String DRIVER ;
    private static final String URL ;
    private static final String USERNAME ;
    private static final String PASSWORD ;

    static{
        Properties prop = PropUtil.loadProperty("config.properties");
        DRIVER = prop.getProperty("jdbc.driver");
        URL = prop.getProperty("jdbc.url");
        USERNAME = prop.getProperty("jdbc.username");
        PASSWORD = prop.getProperty("jdbc.password");
        try{
            Class.forName(DRIVER);
        }catch(Exception e){
            logger.error("load jdbc driver is wrong",e);
        }

    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = threadLocal.get();
        if(conn == null){
            try{
                conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            }catch (Exception e){
                logger.error("get connection is failure",e);
                throw new RuntimeException(e);
            }finally {
                threadLocal.set(conn);
            }
        }

        return conn;
    }

    /**
     * 关闭连接
     */
    public static void closeConnction(){
        Connection conn = threadLocal.get();
        if(conn != null){
            try{
                conn.close();
            }catch (Exception e){
                logger.error("close the connection is failuer");
                throw new RuntimeException(e);
            }finally {
                threadLocal.remove();//容器中移除
            }
        }
    }

    /**
     * 查询获取客户列表
     * @param entityClass
     * @param conn
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @author xy
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,Connection conn,String sql,Object...params){
        List<T> entityList = null;
        try{
            entityList = queryRunner.query(conn,sql,new BeanListHandler<T>(entityClass),params);
        }catch (Exception e){
            logger.error("query entity list failuer",e);
            throw new RuntimeException(e);
        }finally{
            closeConnction();
        }
        return entityList;

    }

    /**
     * 查询客户实体
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass,String sql,Object...params){
        T entity;
        try{
            Connection conn = getConnection();
            entity = queryRunner.query(conn,sql,new BeanHandler<T>(entityClass),params);

        }catch (Exception e){
            logger.error("query entity is failuer",e);
            throw new RuntimeException(e);
        }finally {
            closeConnction();
        }
        return entity;

    }
}
