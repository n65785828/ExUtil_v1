package org.niyihua;

import org.niyihua.encrypt.constant.EncryptMethod;
import org.niyihua.encrypt.constant.EncryptType;
import org.niyihua.encrypt.utils.StrUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static org.niyihua.encrypt.constant.EncryptMethod.*;
import static org.niyihua.encrypt.constant.EncryptType.加密;
import static org.niyihua.encrypt.constant.EncryptType.解密;


public class Starter {
    private static final JFrame FRAME = new JFrame("加密小工具");
    private static final String DESIGN_BY = "-- powered by nyh";
    private static final EncryptType[] ENCRYPT_TYPES = {加密,解密};
    private static final EncryptMethod[] ENCRYPT_METHODS = {RSA, EncryptMethod.MD5,SM2,SM4,AES,DES};
    private static final JTextArea INPUT_TEXT_AREA = new JTextArea(3, 30);
    private static final JTextArea OUTPUT_TEXT_AREA = new JTextArea(3, 30);
    private static final JTextArea KEY_TEXT_AREA = new JTextArea(2, 20);
    private static final JComboBox<EncryptMethod> ENCRYPT_MODE = new JComboBox();
    private static final JComboBox COMBO_BOX_ENCRYPT_TYPES = new JComboBox();
    private static final JPanel PANEL = new JPanel();

    public static void main(String[] args){
        FRAME.setSize(390, 460);
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paintPanel();
        FRAME.setContentPane(PANEL);
        FRAME.setVisible(true);
    }

    private static void paintPanel() {
        PANEL.setLayout(null);
        addInputTextArea();
        addKeyTextArea();
        addOutputTextArea();
        addExecuteButton();
        addClearButton();
        addSwitchTypeBox();
        addSwitchMethodBox();
        addDesignByLabel();
    }


    /**
     * 添加选择加密方式
     */
    private static void addSwitchMethodBox() {
        COMBO_BOX_ENCRYPT_TYPES.setModel(new DefaultComboBoxModel(ENCRYPT_TYPES));
        COMBO_BOX_ENCRYPT_TYPES.setBounds(80,230,60,30);
        PANEL.add(COMBO_BOX_ENCRYPT_TYPES);
    }

    /**
     * 加密或解密选择框
     */
    private static void addSwitchTypeBox() {
        ENCRYPT_MODE.setModel(new DefaultComboBoxModel(ENCRYPT_METHODS));
        ENCRYPT_MODE.setBounds(10,230,60,30);
        ENCRYPT_MODE.addActionListener(e -> {
            if(e.getActionCommand().equals("comboBoxChanged")){
                if(Objects.equals(ENCRYPT_MODE.getSelectedItem(),MD5)){
                    KEY_TEXT_AREA.setEnabled(false);
                }else{
                    KEY_TEXT_AREA.setEnabled(true);
                }
            }
        });
        PANEL.add(ENCRYPT_MODE);
    }

    /**
     * 添加执行按钮
     */
    private static void addExecuteButton() {
        JButton execute = new JButton("执行");
        execute.addActionListener(e -> {
            String text = INPUT_TEXT_AREA.getText();
            String result;
            if(StrUtil.isEmpty(text)){
                OUTPUT_TEXT_AREA.setText("");
                return;
            }
            EncryptType encryptType = (EncryptType) COMBO_BOX_ENCRYPT_TYPES.getSelectedItem();
            EncryptMethod selectEncryptMethod = (EncryptMethod) ENCRYPT_MODE.getSelectedItem();
            switch (encryptType){
                case 加密:
                    result = selectEncryptMethod.getEncryption().encrypt(text, KEY_TEXT_AREA.getText());
                    break;
                case 解密:
                    result = selectEncryptMethod.getEncryption().decrypt(text, KEY_TEXT_AREA.getText());
                    break;
                default:
                    result = "";

            }
            OUTPUT_TEXT_AREA.setText(result);
        });
        execute.setBounds(300,230,60,30);
        PANEL.add(execute);
    }

    /**
     * 添加清空按钮
     */
    private static void addClearButton() {
        JButton clear = new JButton("清空");
        clear.setBounds(230,230,60,30);
        clear.addActionListener(e->{
            OUTPUT_TEXT_AREA.setText("");
            INPUT_TEXT_AREA.setText("");
            KEY_TEXT_AREA.setText("");
        });
        PANEL.add(clear);
    }

    /**
     * 添加输出标签与文本框
     */
    private static void addOutputTextArea() {
        //输出标签
        JLabel outputLabel = new JLabel();
        outputLabel.setText("输出：");
        outputLabel.setBounds(10,270,60,30);
        PANEL.add(outputLabel);
        //输出文本框
        JScrollPane outPutScrollPane = new JScrollPane();
        OUTPUT_TEXT_AREA.setLineWrap(true);
        outPutScrollPane.setBounds(10,300,350,90);//输出text
        outPutScrollPane.setViewportView(OUTPUT_TEXT_AREA);
        PANEL.add(outPutScrollPane);
    }

    /**
     * 添加秘钥文本框
     */
    private static void addKeyTextArea() {
        //秘钥label
        JLabel keyLabel = new JLabel();
        keyLabel.setText("秘钥：");
        keyLabel.setBounds(10,115,40,30);
        PANEL.add(keyLabel);
        //秘钥输入框
        JScrollPane keyScrollPane = new JScrollPane();
        keyScrollPane.setBounds(10,145,350,60);
        keyScrollPane.setViewportView(KEY_TEXT_AREA);
        PANEL.add(keyScrollPane);
    }

    /**
     * 添加输入文本框
     */
    private static void addInputTextArea() {
        //输入label
        JLabel inputLabel = new JLabel();
        inputLabel.setText("输入：");
        inputLabel.setBounds(10,5,300,15);
        PANEL.add(inputLabel);
        //输入框
        INPUT_TEXT_AREA.setLineWrap(false);
        JScrollPane inputScrollPane = new JScrollPane();
        inputScrollPane.setBounds(10,30,350,80);
        inputScrollPane.setViewportView(INPUT_TEXT_AREA);
        PANEL.add(inputScrollPane);
    }

    /**
     * 添加designBy
     */
    private static void addDesignByLabel() {
        JLabel design = new JLabel(DESIGN_BY);
        design.setFont(new Font("宋体",Font.PLAIN,12));
        design.setBounds(250,380,120,40);
        PANEL.add(design);
    }

}
