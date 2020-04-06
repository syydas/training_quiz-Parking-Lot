package utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

public class JDBCUtil {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static String DRIVER;

    static {
        try {
            //加载文件
            Properties pro = new Properties();
            ClassLoader classLoader = utils.JDBCUtil.class.getClassLoader();
            java.net.URL pathURL = classLoader.getResource("jdbc.properties");
            String path = Objects.requireNonNull(pathURL).getPath();
            pro.load(new FileReader(path));

            //获取数据
            URL = pro.getProperty("url");
            USER = pro.getProperty("user");
            PASSWORD = pro.getProperty("password");
            DRIVER = pro.getProperty("driver");

            //注册驱动
            Class.forName(DRIVER);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDB() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void releaseSource(Connection conn, Statement pre) {
        if (null != pre) {
            try {
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void releaseSource(Connection conn, Statement pre, ResultSet res) {
        if (null != res) {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        releaseSource(conn, pre);
    }
}

