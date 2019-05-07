import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;



public class Report {

    Connection con;
    Statement stmt;
    ResultSet rs;
    DBManager db = DBManager.getInstance("","");

    public void totalSales () {
        String reportSource =  System.getProperty("user.dir") +"/src/report/totalSales.jrxml";
        String reportDest = System.getProperty("user.dir")+"/src/report/Destination/totalSales.html";

        Map<String, Object> params = new HashMap<>();

        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            con=db.getConnection();


            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getTop5Customer() {
        String reportSource =  System.getProperty("user.dir") +"/src/report/top5Customer.jrxml";
        String reportDest = System.getProperty("user.dir") +"/src/report/Destination/top5Customer.html";

        Map<String, Object> params = new HashMap<String, Object>();

        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            con=db.getConnection();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getTop10Books() {
        String reportSource =  System.getProperty("user.dir") +"/src/report/top10Books.jrxml";
        String reportDest = System.getProperty("user.dir") +"/src/report/Destination/top10Books.html";

        Map<String, Object> params = new HashMap<String, Object>();

        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            con=db.getConnection();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
