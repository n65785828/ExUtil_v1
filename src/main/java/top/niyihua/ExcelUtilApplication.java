package top.niyihua;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import top.niyihua.converter.BgConverter;
import top.niyihua.entity.ExData;
import top.niyihua.entity.ExDataCalculate;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelUtilApplication {

    private static List<ExData> readData = null;

    public static void main(String[] args) throws Exception {
        final JFrame jf = new JFrame("Excel小工具");
        jf.setSize(400, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 创建文本区域, 用于显示相关信息
        final JTextArea msgTextArea = new JTextArea(10, 30);
        msgTextArea.setLineWrap(true);
        panel.add(msgTextArea);

        JButton openBtn = new JButton("打开");
        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileOpenDialog(jf, msgTextArea);
            }
        });
        panel.add(openBtn);

        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileSaveDialog(jf, msgTextArea);
            }
        });
        panel.add(saveBtn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    /*
     * 打开文件
     */
    private static void showFileOpenDialog(Component parent, JTextArea msgTextArea) {
        // 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();

        // 设置默认显示的文件夹为当前文件夹
        fileChooser.setCurrentDirectory(new File("."));

        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 设置是否允许多选
        fileChooser.setMultiSelectionEnabled(true);

        // 添加可用的文件过滤器（FileNameExtensionFilter 的第一个参数是描述, 后面是需要过滤的文件扩展名 可变参数）
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
        // 设置默认使用的文件过滤器
        fileChooser.setFileFilter(new FileNameExtensionFilter("excel(*.xlsx, *.xls)", "xlsx", "xls"));

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            File file = fileChooser.getSelectedFile();
            try(InputStream is = new FileInputStream(file);){
                ExcelReader reader = ExcelUtil.getReader(is);
                reader.addHeaderAlias("代码", "code");
                reader.addHeaderAlias("名称", "name");
                reader.addHeaderAlias("涨幅%", "upV");
                reader.addHeaderAlias("涨速%", "upSpeed");
                reader.addHeaderAlias("开盘%", "openP");
                reader.addHeaderAlias("现量", "nowVolume");
                reader.addHeaderAlias("流通市值Z", "liuTongZ");
                reader.addHeaderAlias("总金额", "totalMoney");
                reader.addHeaderAlias("开盘金额", "openMoney");
                reader.addHeaderAlias("封单额", "closeVar");
                reader.addHeaderAlias("流通市值", "flowMarketVaR");
                reader.addHeaderAlias("换手%", "changeHand");
                reader.addHeaderAlias("现价", "nowPrice");
                msgTextArea.append("读取中....... " + "\n\n");
                List<ExData> exData = reader.readAll(ExData.class);
                // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
                // File[] files = fileChooser.getSelectedFiles();
                readData = exData;
                reader.close();
                msgTextArea.append("读取成功: " + file.getAbsolutePath() + "\n\n");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /*
     * 选择文件保存路径
     */
    private static void showFileSaveDialog(Component parent, JTextArea msgTextArea) {
        // 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();

        // 设置打开文件选择框后默认输入的文件名
        fileChooser.setSelectedFile(new File("output.xlsx"));

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"保存", 则获取选择的保存路径
            File file = fileChooser.getSelectedFile();
            msgTextArea.append("文件处理中............. " + "\n\n");
            ExcelWriter writer = ExcelUtil.getWriter(file.getAbsolutePath());
            writer.addHeaderAlias("code", "代码");
            writer.addHeaderAlias("name", "名称");
            writer.addHeaderAlias("upV", "涨幅%");
            writer.addHeaderAlias("upSpeed", "涨速%");
            writer.addHeaderAlias("openP", "开盘%");
            writer.addHeaderAlias("nowVolume", "现量");
            writer.addHeaderAlias("liuTongZ", "流通市值Z");
            writer.addHeaderAlias("totalMoney", "总金额");
            writer.addHeaderAlias("openMoney", "开盘金额");
            writer.addHeaderAlias("closeVar", "封单额");
            writer.addHeaderAlias("flowMarketVaR", "流通d市值");
            writer.addHeaderAlias("changeHand", "换手%");
            writer.addHeaderAlias("nowPrice", "现价");
            List<ExDataCalculate> dt = readData.stream().map(BgConverter::convert).collect(Collectors.toList());
            List<ExData> collect = dt.stream().map(BgConverter::revert).collect(Collectors.toList());
            writer.write(collect);
            writer.close();
            msgTextArea.append("处理完毕，文件保存到: " + file.getAbsolutePath() + "\n\n");
        }
    }

}
