package org.niyihua;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.niyihua.converter.BgConverter;
import org.niyihua.entity.ExData;
import org.niyihua.entity.ExDataCalculate;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.niyihua.util.NumberUtil.*;

public class ExcelUtilApplication {

    private static List<ExData> readData = null;
    private static int count = 0;
    private static int count1 = 0;
    private static Map<String,JCheckBox> record = new HashMap<>();
    private static JCheckBox jCheckBox00 = new JCheckBox("整数");

    //反数
    private static JCheckBox jCheckBox01 = new JCheckBox("1.1");
    private static JCheckBox jCheckBox02 = new JCheckBox("1.2");
    private static JCheckBox jCheckBox03 = new JCheckBox("最高");
    private static JCheckBox jCheckBox04 = new JCheckBox("最低");
    private static JCheckBox jCheckBox05 = new JCheckBox("今开");

    //对字数
    private static JCheckBox jCheckBox11 = new JCheckBox("1.1");
    private static JCheckBox jCheckBox12 = new JCheckBox("1.2");
    private static JCheckBox jCheckBox13 = new JCheckBox("最高");
    private static JCheckBox jCheckBox14 = new JCheckBox("最低");
    private static JCheckBox jCheckBox15 = new JCheckBox("今开");

    //叠字数
    private static JCheckBox jCheckBox21 = new JCheckBox("1.1");
    private static JCheckBox jCheckBox22 = new JCheckBox("1.2");
    private static JCheckBox jCheckBox23 = new JCheckBox("最高");
    private static JCheckBox jCheckBox24 = new JCheckBox("最低");
    private static JCheckBox jCheckBox25 = new JCheckBox("今开");

    //倍数
    private static JCheckBox jCheckBox31 = new JCheckBox("1.1");
    private static JCheckBox jCheckBox32 = new JCheckBox("1.2");
    private static JCheckBox jCheckBox33 = new JCheckBox("最高");
    private static JCheckBox jCheckBox34 = new JCheckBox("最低");
    private static JCheckBox jCheckBox35 = new JCheckBox("今开");

    //阶梯数
    private static JCheckBox jCheckBox41 = new JCheckBox("1.1");
    private static JCheckBox jCheckBox42 = new JCheckBox("1.2");
    private static JCheckBox jCheckBox43 = new JCheckBox("最高");
    private static JCheckBox jCheckBox44 = new JCheckBox("最低");
    private static JCheckBox jCheckBox45 = new JCheckBox("今开");

    //自定义数
    private static JCheckBox jCheckBox51 = new JCheckBox("1.1");
    private static JCheckBox jCheckBox52 = new JCheckBox("1.2");
    private static JCheckBox jCheckBox53 = new JCheckBox("最高");
    private static JCheckBox jCheckBox54 = new JCheckBox("最低");
    private static JCheckBox jCheckBox55 = new JCheckBox("今开");

    private static final JTextField inputDesign=new JTextField(30);
    static {
        record.put("00",jCheckBox00);
        record.put("01",jCheckBox01);
        record.put("02",jCheckBox02);
        record.put("03",jCheckBox03);
        record.put("04",jCheckBox04);
        record.put("05",jCheckBox05);
        record.put("11",jCheckBox11);
        record.put("12",jCheckBox12);
        record.put("13",jCheckBox13);
        record.put("14",jCheckBox14);
        record.put("15",jCheckBox15);
        record.put("21",jCheckBox21);
        record.put("22",jCheckBox22);
        record.put("23",jCheckBox23);
        record.put("24",jCheckBox24);
        record.put("25",jCheckBox25);
        record.put("31",jCheckBox31);
        record.put("32",jCheckBox32);
        record.put("33",jCheckBox33);
        record.put("34",jCheckBox34);
        record.put("35",jCheckBox35);
        record.put("41",jCheckBox41);
        record.put("42",jCheckBox42);
        record.put("43",jCheckBox43);
        record.put("44",jCheckBox44);
        record.put("45",jCheckBox45);
        record.put("51",jCheckBox51);
        record.put("52",jCheckBox52);
        record.put("53",jCheckBox53);
        record.put("54",jCheckBox54);
        record.put("55",jCheckBox55);
        File file = new File("excelUtilConf.conf");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try(FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            ) {
                byte[] buf = new byte[1024];
                String str = bufferedReader.readLine();
                String input = bufferedReader.readLine();
                if(StrUtil.isNotEmpty(str)){
                    String[] s = str.split(" ");
                    for (String s0 :s){
                        record.get(s0).setSelected(true);
                    }
                }
                if(StrUtil.isNotEmpty(input)){
                    inputDesign.setText(input);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        jCheckBox00.setSelected(true);
//        jCheckBox01.setSelected(true);
//        jCheckBox02.setSelected(true);
//        jCheckBox03.setSelected(true);
//        jCheckBox04.setSelected(true);
//        jCheckBox05.setSelected(true);
//        jCheckBox11.setSelected(true);
//        jCheckBox12.setSelected(true);
//        jCheckBox13.setSelected(true);
//        jCheckBox14.setSelected(true);
//        jCheckBox15.setSelected(true);
//        jCheckBox21.setSelected(true);
//        jCheckBox22.setSelected(true);
//        jCheckBox23.setSelected(true);
//        jCheckBox24.setSelected(true);
//        jCheckBox25.setSelected(true);
//        jCheckBox31.setSelected(true);
//        jCheckBox32.setSelected(true);
//        jCheckBox33.setSelected(true);
//        jCheckBox34.setSelected(true);
//        jCheckBox35.setSelected(true);
//        jCheckBox41.setSelected(true);
//        jCheckBox42.setSelected(true);
//        jCheckBox43.setSelected(true);
//        jCheckBox44.setSelected(true);
//        jCheckBox45.setSelected(true);
//        jCheckBox51.setSelected(true);
//        jCheckBox52.setSelected(true);
//        jCheckBox53.setSelected(true);
//        jCheckBox54.setSelected(true);
//        jCheckBox55.setSelected(true);
    }


    private static JButton next ;
    private static final JTextArea msgTextArea = new JTextArea(10, 30);
    private static final JTextArea overTextArea = new JTextArea(5, 30);
    private static final JFrame jf = new JFrame("Excel小工具");
//    private static final List<JCheckBox> J_CHECK_BOXES = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        jf.setSize(600, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel cards=new JPanel(new CardLayout());
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();


        dsignPanel2(panel2);

        // 创建文本区域, 用于显示相关信息
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


        next = new JButton("下一步");
        next.setEnabled(false);
        panel.add(next);
        cards.add(panel,"card1");    //向卡片式布局面板中添加面板1
        cards.add(panel2,"card2");    //向卡片式布局面板中添加面板2
        CardLayout cl=(CardLayout)(cards.getLayout());
        cl.show(cards,"card1");    //调用show()方法显示面板2
        next.addActionListener((e)->{
            cl.show(cards,"card2");
        });
        jf.setContentPane(cards);
        jf.setVisible(true);
    }

    private static void dsignPanel2(JPanel panel2) {
        panel2.setLayout(null);
        JLabel label1=new JLabel("整数:");
        JLabel label2=new JLabel("反数:");
        JLabel label3=new JLabel("对子数:");
        JLabel label4=new JLabel("叠子数:");
        JLabel label5=new JLabel("自定义数:");
        JLabel label6=new JLabel("倍数:");
        JLabel label7=new JLabel("阶梯数:");

        label1.setLayout(null);
        label1.setBounds(10,10,90,15);
        jCheckBox00.setBounds(70+30,10,60,15);
        label2.setBounds(10,30,100,15);
        jCheckBox01.setBounds(70+30,30,60,15);
        jCheckBox02.setBounds(130+30,30,60,15);
        jCheckBox03.setBounds(200+30,30,60,15);
        jCheckBox04.setBounds(270+30,30,60,15);
        jCheckBox05.setBounds(340+30,30,60,15);
        label3.setBounds(10,50,100,22);
        jCheckBox11.setBounds(70+30, 50,60,15);
        jCheckBox12.setBounds(130+30,50,60,15);
        jCheckBox13.setBounds(200+30,50,60,15);
        jCheckBox14.setBounds(270+30,50,60,15);
        jCheckBox15.setBounds(340+30,50,60,15);

        label4.setBounds(10,70,100,22);
        jCheckBox21.setBounds(70+30, 70,60,15);
        jCheckBox22.setBounds(130+30,70,60,15);
        jCheckBox23.setBounds(200+30,70,60,15);
        jCheckBox24.setBounds(270+30,70,60,15);
        jCheckBox25.setBounds(340+30,70,60,15);

        label5.setBounds(10,100,70,23);
        jCheckBox51.setBounds(70+30, 100,60,15);
        jCheckBox52.setBounds(130+30,100,60,15);
        jCheckBox53.setBounds(200+30,100,60,15);
        jCheckBox54.setBounds(270+30,100,60,15);
        jCheckBox55.setBounds(340+30,100,60,15);
        inputDesign.setBounds(450,100,120,22);
        panel2.add(inputDesign);

        label6.setBounds(10,125,70,15);
        jCheckBox31.setBounds(70+30, 125,60,15);
        jCheckBox32.setBounds(130+30,125,60,15);
        jCheckBox33.setBounds(200+30,125,60,15);
        jCheckBox34.setBounds(270+30,125,60,15);
        jCheckBox35.setBounds(340+30,125,60,15);

        label7.setBounds(10,145,145,15);
        jCheckBox41.setBounds(70+30, 145,60,15);
        jCheckBox42.setBounds(130+30,145,60,15);
        jCheckBox43.setBounds(200+30,145,60,15);
        jCheckBox44.setBounds(270+30,145,60,15);
        jCheckBox45.setBounds(340+30,145,60,15);

        panel2.add(label5);
        panel2.add(label1);
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(label6);
        panel2.add(label7);

        cd();

        panel2.add(jCheckBox00);

        panel2.add(jCheckBox01);
        panel2.add(jCheckBox02);
        panel2.add(jCheckBox03);
        panel2.add(jCheckBox04);
        panel2.add(jCheckBox05);

        panel2.add(jCheckBox11);
        panel2.add(jCheckBox12);
        panel2.add(jCheckBox13);
        panel2.add(jCheckBox14);
        panel2.add(jCheckBox15);

        panel2.add(jCheckBox21);
        panel2.add(jCheckBox22);
        panel2.add(jCheckBox23);
        panel2.add(jCheckBox24);
        panel2.add(jCheckBox25);

        panel2.add(jCheckBox31);
        panel2.add(jCheckBox32);
        panel2.add(jCheckBox33);
        panel2.add(jCheckBox34);
        panel2.add(jCheckBox35);


        panel2.add(jCheckBox41);
        panel2.add(jCheckBox42);
        panel2.add(jCheckBox43);
        panel2.add(jCheckBox44);
        panel2.add(jCheckBox45);

        panel2.add(jCheckBox51);
        panel2.add(jCheckBox52);
        panel2.add(jCheckBox53);
        panel2.add(jCheckBox54);
        panel2.add(jCheckBox55);



        overTextArea.setBounds(10,180,360,80);
        panel2.add(overTextArea);
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileSaveDialog(jf, overTextArea);
            }
        });
        saveBtn.setBounds(160,280,80,35);
        panel2.add(saveBtn);
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
            msgTextArea.append("读取中....... " + "\n\n");
            new Thread(()->{
                readExcel(msgTextArea, file);
            }).start();

        }
    }

    private static void readExcel(JTextArea msgTextArea, File file) {
        try(InputStream is = new FileInputStream(file)){
            ExcelReader reader = ExcelUtil.getReader(is);
            setAlias(reader);

            List<ExData> exData = reader.readAll(ExData.class);
            // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
            // File[] files = fileChooser.getSelectedFiles();
            readData = exData;
            reader.close();
            msgTextArea.append("读取成功: " + file.getAbsolutePath() + "\n\n");
            next.setEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
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
            new Thread(()->{
                dealWithExcel(msgTextArea, file);
            }).start();
        }
    }

    private static void dealWithExcel(JTextArea msgTextArea, File file) {
        ExcelWriter writer = ExcelUtil.getWriter(file.getAbsolutePath());
        List<Map<String,Object>> ds = new ArrayList<>();
        LinkedHashSet<ExDataCalculate> resultSet = new LinkedHashSet<>();
        List<ExDataCalculate> dt = readData.stream().map(BgConverter::convert).collect(Collectors.toList());
//            List<ExData> collect = dt.stream().map(BgConverter::revert).collect(Collectors.toList());
        filterData(resultSet, dt);


        resultSet.forEach(t->{
            Map<String,Object> map  = new LinkedHashMap<>();
            map.put("代码",t.getCode());
            map.put("名称",t.getName());
            map.put("涨幅%",t.getUpV()==null?"--":t.getUpV().doubleValue());
            map.put("涨速%",t.getUpSpeed()==null?"--":t.getUpSpeed().doubleValue());
            map.put("开盘%",t.getOpenP()==null?"--":t.getOpenP().doubleValue());
            map.put("现量",t.getNowVolume()==null?"--":t.getNowVolume().doubleValue());
            map.put("流通市值Z",t.getLiuTongZ()==null?"--":t.getLiuTongZ().toString()+"亿");
            map.put("总金额",t.getTotalMoney()==null?"--":t.getTotalMoney().doubleValue());
            map.put("开盘金额",t.getOpenMoney()==null?"--":t.getOpenMoney().doubleValue());
            map.put("封单额",t.getCloseVar()==null?"--":t.getCloseVar().doubleValue());
            map.put("流通市值",t.getFlowMarketVaR()==null?"--":t.getFlowMarketVaR().toString()+"亿");
            map.put("换手%", t.getChangeHand()==null?"--":t.getChangeHand().doubleValue());
            map.put("现价",t.getNowPrice()==null?"--":t.getNowPrice().doubleValue());
            map.put("1.1倍",t.getOnePointOneTime()==null?"--":t.getOnePointOneTime().doubleValue());
            map.put("1.2倍",t.getOnePointTwoTime()==null?"--":t.getOnePointTwoTime().doubleValue());
            map.put("量比",t.getLiangBi()==null?"--":t.getLiangBi().doubleValue());
            map.put("最高%",t.getBestHighPer()==null?"--":t.getBestHighPer().doubleValue());
            map.put("最高",t.getBestHigh()==null?"--":t.getBestHigh().doubleValue());
            map.put("最低%",t.getBestLowPer()==null?"--":t.getBestLowPer().doubleValue());
            map.put("最低",t.getBestLow()==null?"--":t.getBestLow().doubleValue());
            map.put("今开",t.getNowOpen()==null?"--":t.getNowOpen().doubleValue());
            map.put("总量",t.getAllVolume()==null?"--":t.getAllVolume().intValue());
            map.put("卖价",t.getSalePrice()==null?"--":t.getSalePrice().doubleValue());
            map.put("昨收",t.getYesterdayEnd()==null?"--":t.getYesterdayEnd().doubleValue());
            map.put("删选条件",t.getRemark());
            ds.add(map);
        });
        writer.write(ds);
        writer.close();
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File("excelUtilConf.conf"))) {
            Set<String> strings = record.keySet();
            for (String ss:strings) {
                JCheckBox jCheckBox = record.get(ss);
                if (jCheckBox.isSelected()){
                    fileOutputStream.write((ss+" ").getBytes(StandardCharsets.UTF_8));
                }
            }
            fileOutputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            if(StrUtil.isNotEmpty(inputDesign.getText())){
                fileOutputStream.write((inputDesign.getText()+"\n").getBytes(StandardCharsets.UTF_8));
            }
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        msgTextArea.append("处理完毕，文件保存到: " + file.getAbsolutePath() + "\n\n");
    }

    private static void filterData(LinkedHashSet<ExDataCalculate> resultSet, List<ExDataCalculate> dt) {
        //整数
        if(jCheckBox00.isSelected()){
            dt.forEach(t->{
                if((t.getCode().startsWith("30"))){
                    if(t.getOnePointTwoTime()!=null&&new BigDecimal(t.getOnePointTwoTime().intValue()).compareTo(t.getOnePointTwoTime())==0){
                        t.setRemark(t.getRemark()+"代码30开始，1.2倍为整数;");
                        resultSet.add(t);
                    }
                }else{
                    if(t.getOnePointOneTime()!=null&&new BigDecimal(t.getOnePointOneTime().intValue()).compareTo(t.getOnePointOneTime())==0){
                        t.setRemark(t.getRemark()+"代码非30开始，1.1倍为整数;");
                        resultSet.add(t);
                    }
                }
            });
        }
        //反数
        dt.forEach(t->{
            if(jCheckBox01.isSelected()&&isRevertNumber(t.getOnePointOneTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.1倍是反数;");
            }
            if(jCheckBox02.isSelected()&&isRevertNumber(t.getOnePointTwoTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.2倍是反数;");
            }
            if(jCheckBox03.isSelected()&&isRevertNumber(t.getBestHigh())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最高是反数;");
            }
            if(jCheckBox04.isSelected()&&isRevertNumber(t.getBestLow())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最低是反数;");
            }
            if(jCheckBox05.isSelected()&&isRevertNumber(t.getNowOpen())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"今开是反数;");
            }
        });
        //阶梯数
        dt.forEach(t->{
            if(jCheckBox41.isSelected()&&isStepNumber(t.getOnePointOneTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.1倍是阶梯数;");
            }
            if(jCheckBox42.isSelected()&&isStepNumber(t.getOnePointTwoTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.2倍是阶梯数;");
            }
            if(jCheckBox43.isSelected()&&isStepNumber(t.getBestHigh())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最高是阶梯数;");
            }
            if(jCheckBox44.isSelected()&&isStepNumber(t.getBestLow())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最低是阶梯数;");
            }
            if(jCheckBox45.isSelected()&&isStepNumber(t.getNowOpen())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"今开是阶梯数;");
            }
        });

        //倍数
        dt.forEach(t->{
            if(jCheckBox31.isSelected()&&isMutiNumber(t.getOnePointOneTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.1倍是倍数;");
            }
            if(jCheckBox32.isSelected()&&isMutiNumber(t.getOnePointTwoTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.2倍是倍数;");
            }
            if(jCheckBox33.isSelected()&&isMutiNumber(t.getBestHigh())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最高是倍数;");
            }
            if(jCheckBox34.isSelected()&&isMutiNumber(t.getBestLow())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最低是倍数;");
            }
            if(jCheckBox35.isSelected()&&isMutiNumber(t.getNowOpen())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"今开是倍数;");
            }
        });

        //对子数
        dt.forEach(t->{
            if(jCheckBox11.isSelected()&&isDuiZiNumber(t.getOnePointOneTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.1倍是对子数;");
            }
            if(jCheckBox12.isSelected()&&isDuiZiNumber(t.getOnePointTwoTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.2倍是对子数;");
            }
            if(jCheckBox13.isSelected()&&isDuiZiNumber(t.getBestHigh())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最高是对子数;");
            }
            if(jCheckBox14.isSelected()&&isDuiZiNumber(t.getBestLow())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最低是对子数;");
            }
            if(jCheckBox15.isSelected()&&isDuiZiNumber(t.getNowOpen())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"今开是对子数;");
            }
        });

        //叠子数
        dt.forEach(t->{
            if(jCheckBox21.isSelected()&&isDDNumber(t.getOnePointOneTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.1倍是叠子数;");
            }
            if(jCheckBox22.isSelected()&&isDDNumber(t.getOnePointTwoTime())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"1.2倍是叠子数;");
            }
            if(jCheckBox23.isSelected()&&isDDNumber(t.getBestHigh())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最高是叠子数;");
            }
            if(jCheckBox24.isSelected()&&isDDNumber(t.getBestLow())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"最低是叠子数;");
            }
            if(jCheckBox25.isSelected()&&isDDNumber(t.getNowOpen())){
                resultSet.add(t);
                t.setRemark(t.getRemark()+"今开是叠子数;");
            }
        });

        //自定义数
        String text = inputDesign.getText();
        if(StrUtil.isNotEmpty(StrUtil.trim(text))){
            String[] arrays = StrUtil.trim(text).split(" ");
            HashSet<String> set = new HashSet<>();
            set.addAll(Arrays.asList(arrays));
            dt.forEach(t->{
                if(jCheckBox51.isSelected()&&t.getOnePointOneTime()!=null&&set.contains(t.getOnePointOneTime().toString())){
                    resultSet.add(t);
                    t.setRemark(t.getRemark()+"1.1倍包含于自定义数（"+text+"）中;");
                }
                if(jCheckBox52.isSelected()&&t.getOnePointTwoTime()!=null&&set.contains(t.getOnePointTwoTime().toString())){
                    resultSet.add(t);
                    t.setRemark(t.getRemark()+"1.2倍含于自定义数（"+text+"）中;");
                }
                if(jCheckBox53.isSelected()&&t.getBestHigh()!=null&&set.contains(t.getBestHigh().toString())){
                    resultSet.add(t);
                    t.setRemark(t.getRemark()+"最高含于自定义数（"+text+"）中;");
                }
                if(jCheckBox54.isSelected()&&t.getBestLow()!=null&&set.contains(t.getBestLow().toString())){
                    resultSet.add(t);
                    t.setRemark(t.getRemark()+"最低含于自定义数（"+text+"）中;");
                }
                if(jCheckBox55.isSelected()&&t.getNowOpen()!=null&&set.contains(t.getNowOpen().toString())){
                    resultSet.add(t);
                    t.setRemark(t.getRemark()+"今开含于自定义数（"+text+"）中;");
                }
            });
        }

    }


    private static void setAlias(ExcelReader reader) {
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
        reader.addHeaderAlias("量比", "liangBi");
        reader.addHeaderAlias("最高%", "bestHighPer");
        reader.addHeaderAlias("最高", "bestHigh");
        reader.addHeaderAlias("最低%", "bestLowPer");
        reader.addHeaderAlias("最低", "bestLow");
        reader.addHeaderAlias("今开", "nowOpen");
        reader.addHeaderAlias("总量", "allVolume");
        reader.addHeaderAlias("卖价", "salePrice");
        reader.addHeaderAlias("昨收", "yesterdayEnd");
        //市盈(动)	细分行业	地区	振幅%	均价	内盘	外盘	内外比	买量	卖量	未匹配量	开盘换手Z	封成比	流通股(亿)	AB股总市值	强弱度%	活跃度	笔均量	笔换手	连涨天	昨涨幅%	3日涨幅%	20日涨幅%	60日涨幅%	年初至今%	年涨停天	换手Z	流通比例Z	市盈(TTM)	市盈(静)	贝塔系数	近日指标提示	短期形态	中期形态	长期形态	均涨幅%	实体涨幅%	回头波%	攻击波%	财务更新	上市日期	总股本(亿)	B/A股(亿)	H股(亿)	总资产(亿)	买价	净资产(亿)	少数股权(亿)	资产负债率%	流动资产(亿)	固定资产(亿)	无形资产(亿)	流动负债(亿)	货币资金(亿)	存货(亿)	应收帐款(亿)	预收账款(亿)	资本公积金(亿)	营业收入(亿)	营业成本(亿)	营业利润(亿)	投资收益(亿)	利润总额(亿)	税后利润(亿)	净利润(亿)	涨跌	扣非净利润(亿)	未分利润(亿)	经营现金流(亿)	总现金流(亿)	股东人数	人均持股	人均市值	利润同比%	收入同比%	市净率	市现率	市销率	股息率%	每股收益	每股净资	调整后净资	每股公积	每股未分配	每股现金流	权益比%	净益率%	毛利率%	营业利润率%	净利润率%	研发费用(亿)	员工人数	交易代码	自选日	自选价	自选收益%
        reader.addHeaderAlias("市盈(动)","r1");
        reader.addHeaderAlias("细分行业","r2");
        reader.addHeaderAlias("地区","r3");
        reader.addHeaderAlias("振幅%","r4");
        reader.addHeaderAlias("均价","r5");
        reader.addHeaderAlias("内盘","r6");
        reader.addHeaderAlias("外盘","r7");
        reader.addHeaderAlias("内外比","r8");
        reader.addHeaderAlias("买量","r9");
        reader.addHeaderAlias("卖量","r10");
        reader.addHeaderAlias("未匹配量","r11");
        reader.addHeaderAlias("开盘换手Z","r12");
        reader.addHeaderAlias("封成比","r13");
        reader.addHeaderAlias("流通股(亿)","r14");
        reader.addHeaderAlias("AB股总市值","r15");
        reader.addHeaderAlias("强弱度%","r16");
        reader.addHeaderAlias("活跃度","r17");
        reader.addHeaderAlias("笔均量","r18");
        reader.addHeaderAlias("笔换手","r19");
        reader.addHeaderAlias("连涨天","r20");
        reader.addHeaderAlias("昨涨幅%","r21");
        reader.addHeaderAlias("3日涨幅%","r22");
        reader.addHeaderAlias("20日涨幅%","r23");
        reader.addHeaderAlias("60日涨幅%","r24");
        reader.addHeaderAlias("年初至今%","r25");
        reader.addHeaderAlias("年涨停天","r26");
        reader.addHeaderAlias("换手Z","r27");
        reader.addHeaderAlias("流通比例Z","r28");
        reader.addHeaderAlias("市盈(TTM)","r29");
        reader.addHeaderAlias("市盈(静)","r30");
        reader.addHeaderAlias("贝塔系数","r31");
        reader.addHeaderAlias("近日指标提示","r32");
        reader.addHeaderAlias("短期形态","r33");
        reader.addHeaderAlias("中期形态","r34");
        reader.addHeaderAlias("长期形态","r35");
        reader.addHeaderAlias("均涨幅%","r36");
        reader.addHeaderAlias("实体涨幅%","r37");
        reader.addHeaderAlias("回头波%","r38");
        reader.addHeaderAlias("攻击波%","r39");
        reader.addHeaderAlias("财务更新","r40");
        reader.addHeaderAlias("上市日期","r41");
        reader.addHeaderAlias("总股本(亿)","r42");
        reader.addHeaderAlias("B/A股(亿)","r43");
        reader.addHeaderAlias("H股(亿)","r44");
        reader.addHeaderAlias("总资产(亿)","r45");
        reader.addHeaderAlias("买价","r46");
        reader.addHeaderAlias("净资产(亿)","r47");
        reader.addHeaderAlias("少数股权(亿)","r48");
        reader.addHeaderAlias("资产负债率%","r49");
        reader.addHeaderAlias("流动资产(亿)","r50");
        reader.addHeaderAlias("固定资产(亿)","r51");
        reader.addHeaderAlias("无形资产(亿)","r52");
        reader.addHeaderAlias("流动负债(亿)","r53");
        reader.addHeaderAlias("货币资金(亿)","r54");
        reader.addHeaderAlias("存货(亿)","r55");
        reader.addHeaderAlias("应收帐款(亿)","r56");
        reader.addHeaderAlias("预收账款(亿)","r57");
        reader.addHeaderAlias("资本公积金(亿)","r58");
        reader.addHeaderAlias("营业收入(亿)","r59");
        reader.addHeaderAlias("营业成本(亿)","r60");
        reader.addHeaderAlias("营业利润(亿)","r61");
        reader.addHeaderAlias("投资收益(亿)","r62");
        reader.addHeaderAlias("利润总额(亿)","r63");
        reader.addHeaderAlias("税后利润(亿)","r64");
        reader.addHeaderAlias("净利润(亿)","r65");
        reader.addHeaderAlias("涨跌","r66");
        reader.addHeaderAlias("扣非净利润(亿)","r67");
        reader.addHeaderAlias("未分利润(亿)","r68");
        reader.addHeaderAlias("经营现金流(亿)","r69");
        reader.addHeaderAlias("总现金流(亿)","r70");
        reader.addHeaderAlias("股东人数","r71");
        reader.addHeaderAlias("人均持股","r72");
        reader.addHeaderAlias("人均市值","r73");
        reader.addHeaderAlias("利润同比%","r74");
        reader.addHeaderAlias("收入同比%","r75");
        reader.addHeaderAlias("市净率","r76");
        reader.addHeaderAlias("市现率","r77");
        reader.addHeaderAlias("市销率","r78");
        reader.addHeaderAlias("股息率%","r79");
        reader.addHeaderAlias("每股收益","r80");
        reader.addHeaderAlias("每股净资","r81");
        reader.addHeaderAlias("调整后净资","r82");
        reader.addHeaderAlias("每股公积","r83");
        reader.addHeaderAlias("每股未分配","r84");
        reader.addHeaderAlias("每股现金流","r85");
        reader.addHeaderAlias("权益比%","r86");
        reader.addHeaderAlias("净益率%","r87");
        reader.addHeaderAlias("毛利率%","r88");
        reader.addHeaderAlias("营业利润率%","r89");
        reader.addHeaderAlias("净利润率%","r90");
        reader.addHeaderAlias("研发费用(亿)","r91");
        reader.addHeaderAlias("员工人数","r92");
        reader.addHeaderAlias("交易代码","r93");
        reader.addHeaderAlias("自选日","r94");
        reader.addHeaderAlias("自选价","r95");
        reader.addHeaderAlias("自选收益%","r96");
    }
    private static void cd() {
        jCheckBox00.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                count++;
                if(count==10){
                    JOptionPane.showMessageDialog(null, "别点啦，小仙女");
                }
                if(count==20){
                    JOptionPane.showMessageDialog(null, "你再点我就生气喽");
                }
                if(count==30){
                    JOptionPane.showMessageDialog(null, "骗你的啦，我没生气哦");
                }
                if(count==40){
                    JOptionPane.showMessageDialog(null, "小仙女,我喜欢你♥＾▽＾♥");
                }
                if(count==500){
                    JOptionPane.showMessageDialog(null, "♥♥♥♥ i love u ♥♥♥♥");
                }
            }
        });

        jCheckBox45.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                count1++;
                if(count1==52000){
                    JOptionPane.showMessageDialog(null, "♥♥♥♥ Marry me ,Little fairy ♥♥♥♥");
                }
            }
        });
    }
}
