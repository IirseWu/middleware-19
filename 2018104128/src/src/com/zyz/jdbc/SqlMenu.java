package com.zyz.jdbc;

import com.zyz.util.CloseSqlite;
import com.zyz.util.DBUtil;
import org.junit.Test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SqlMenu {
    @Test
    /**
     * �½����ݱ�
     */
    public static void createTable() throws SQLException {
        Connection conn = DBUtil.getConn();
        Statement stmt = conn.createStatement();

        //�½��� number, name, code, unit, price, total
//        String sql_create = "CREATE TABLE goods_info " +
//                "(number         VARCHAR(50)    NOT NULL, " +
//                " name          VARCHAR(255)     NOT NULL, " +
//                " code          VARCHAR(100)     , " +
//                " unit          VARCHAR(100)      , " +
//                " price          DOUBLE      NOT NULL, " +
//                " total          INT)";
        String sql_create = "CREATE TABLE goods_record " +
                "(number         VARCHAR(50)    NOT NULL, " +
                " name          VARCHAR(255)     NOT NULL, " +
                " status          VARCHAR(255)     , " +
                " code          VARCHAR(100)     , " +
                " time          VARCHAR(100)      , " +
                " price          DOUBLE      NOT NULL, " +
                " total          INT)";
        System.out.println("����ɹ�");

        stmt.executeUpdate(sql_create);
        CloseSqlite.close(conn, stmt);
    }

    @Test
    /**
     * ɾ�����ݱ�
     */
    public static void deleteTable() throws SQLException {
        Connection conn = DBUtil.getConn();
        Statement stmt = conn.createStatement();

        String sql = "DROP TABLE goods_record";
        stmt.executeUpdate(sql);
        CloseSqlite.close(conn, stmt);
    }


    @Test
    /**
     * ��������
     */
    public static void firstInsertData(String number, String name, String code, String unit, Double price, int total) throws SQLException {
        Connection conn = DBUtil.getConn();

        //��������
        String sql = "INSERT INTO goods_info (number, name, code, unit,  price, total) " +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, number);
        pstmt.setString(2, name);
        pstmt.setString(3, code);
        pstmt.setString(4, unit);
        pstmt.setDouble(5, price);
        pstmt.setInt(6, total);

        pstmt.executeUpdate();
        CloseSqlite.close(conn, pstmt);
    }


    @Test
    /**
     * ��ѯ����
     */
    public static String[][] selectData(String number, int lens) throws SQLException {
        Connection conn = DBUtil.getConn();
        Statement stmt = conn.createStatement();
        String[][] dataSet;
        ResultSet rs = null;
        //��ѯ����
        if(number.equals("all")){
            dataSet = new String[lens][6];
            String sql = "SELECT * FROM goods_info LIMIT ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lens);
            rs = pstmt.executeQuery();
        }else {
            lens = SqlMenu.selectRows(number);
            dataSet = new String[lens][6];
            String params = "%"+number+"%";

            String sql = "SELECT * FROM goods_info WHERE name LIKE ? OR number LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, params);
            pstmt.setString(2, params);
            rs = pstmt.executeQuery();
        }

        int i = 0;
        while (rs.next() && i<lens){
            dataSet[i][0] = rs.getString("number");
            dataSet[i][1] = rs.getString("name");
            dataSet[i][2] = rs.getString("code");
            dataSet[i][3] = rs.getString("unit");
            dataSet[i][4] = rs.getDouble("price")+"";
            dataSet[i][5] = rs.getInt("total")+"";
            i++;
        }
        CloseSqlite.close(conn, stmt);
        return dataSet;
    }

    @Test
    /**
     * ��������
     */
    public static void updateTotalData(String number, int total) throws SQLException {
        Connection conn = DBUtil.getConn();
        Statement stmt = conn.createStatement();

        String sql = "UPDATE goods_info SET total=? WHERE number =?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, total);
        pstmt.setString(2, number);
        pstmt.executeUpdate();

        CloseSqlite.close(conn, stmt);
    }

    @Test
    /**
     * ��������
     */
    public static void updatePriceData(String number, Double price) throws SQLException {
        Connection conn = DBUtil.getConn();
        Statement stmt = conn.createStatement();

        String sql = "UPDATE goods_info SET price=? WHERE number =?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, price);
        pstmt.setString(2, number);
        pstmt.executeUpdate();

        CloseSqlite.close(conn, stmt);
    }

    @Test
    /**
     * ��ѯ������Ʒ�����ͼ۸񣬲���ʾ�������
     */
    public static String[] selectBaseInfo(String number, String tableName) throws SQLException {
        String[] dataSet = new String[2];
        Connection conn = DBUtil.getConn();

        if(number==""){
            String sql = "SELECT SUM(total) AS allTotal, SUM(total*price) AS allPrice FROM "+tableName;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            dataSet[0] = rs.getString("allTotal");
            dataSet[1] = rs.getString("allPrice");
            CloseSqlite.close(conn, stmt);
        }else {
            String sql = "SELECT SUM(total) AS allTotal, SUM(total*price) AS allPrice FROM "+tableName+" WHERE name LIKE ? OR number LIKE ?";
            String params = "%" + number + "%";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, params);
            pstmt.setString(2, params);
            ResultSet rs = pstmt.executeQuery();

            dataSet[0] = rs.getString("allTotal");
            dataSet[1] = rs.getString("allPrice");
            CloseSqlite.close(conn, pstmt);
        }

        return dataSet;
    }

    /**
     *
     * @param number
     * @return
     * @throws SQLException
     * ��ѯ�����ж�����
     */

    public static int selectRows(String number) throws SQLException {
        int rows = 0;
        Connection conn = DBUtil.getConn();

        String sql = "SELECT Count(*) AS rows FROM goods_info WHERE name LIKE ? OR number LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        String params = "%"+number+"%";
        pstmt.setString(1,params);
        pstmt.setString(2,params);

        ResultSet rs = pstmt.executeQuery();
        rows = rs.getInt("rows");
        CloseSqlite.close(conn, pstmt);

        return rows;
    }

    /**
     *
     * @param
     * @return
     * @throws SQLException
     */

    public static int selectRecordRows(String start, String end, String status) throws SQLException {
        int rows = 0;
        Connection conn = DBUtil.getConn();

        String sql = "SELECT Count(*) AS rows FROM goods_record WHERE time >= ? AND time < ? AND status = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,start);
        pstmt.setString(2,end);
        pstmt.setString(3,status);

        ResultSet rs = pstmt.executeQuery();
        rows = rs.getInt("rows");
        CloseSqlite.close(conn, pstmt);

        return rows;
    }

    /**
     * ��ѯ��ʷ��¼��Ʒ��Ϣ
     */
    public static String[][] selectRecordData(String start, String end, String status) throws SQLException {
        Connection conn = DBUtil.getConn();
        int lens = selectRecordRows(start, end, status);
        String dataSet[][] = new String[lens][7];
        String sql = "SELECT * FROM goods_record WHERE time >= ? AND time < ? AND status = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, start);
        pstmt.setString(2, end);
        pstmt.setString(3, status);

        ResultSet rs = pstmt.executeQuery();

        int i =0;
        while (rs.next() && i<lens){
            dataSet[i][0] = rs.getString("status");
            dataSet[i][1] = rs.getString("number");
            dataSet[i][2] = rs.getString("name");
            dataSet[i][3] = rs.getString("code");
            dataSet[i][4] = rs.getDouble("price")+"";
            dataSet[i][5] = rs.getInt("total")+"";
            dataSet[i][6] = rs.getString("time");
            i++;
        }

        CloseSqlite.close(conn, pstmt);

        return dataSet;
    }

    /**
     * ��ѯ��ʷ��¼������Ʒ�����ͼ۸񣬲���ʾ�������
     */
    public static String[] selectRecordBaseInfo(String start, String end, String status) throws SQLException {
        String[] dataSet = new String[2];
        Connection conn = DBUtil.getConn();

        String sql = "SELECT SUM(total) AS allTotal, SUM(total*price) AS allPrice FROM goods_record WHERE " +
                "time >= ? AND time < ? AND status = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, start);
        pstmt.setString(2, end);
        pstmt.setString(3, status);
        ResultSet rs = pstmt.executeQuery();

        dataSet[0] = rs.getInt("allTotal")+"";
        dataSet[1] = rs.getInt("allPrice")+"";
        CloseSqlite.close(conn, pstmt);

        return dataSet;
    }

    public static void insertRecordData(String[] dataSet, String status) throws SQLException {
        Connection conn = DBUtil.getConn();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        String time = sdf.format(d);

        //��������
        String sql = "INSERT INTO goods_record (status, number, name, code, price, total, time) " +
                "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, status);
        pstmt.setString(2, dataSet[0]);
        pstmt.setString(3, dataSet[1]);
        pstmt.setString(4, dataSet[2]);
        pstmt.setDouble(5, Double.parseDouble(dataSet[4]));
        pstmt.setInt(6, Integer.parseInt(dataSet[5]));
        pstmt.setString(7, time);

        pstmt.executeUpdate();

        CloseSqlite.close(conn, pstmt);
    }
}
