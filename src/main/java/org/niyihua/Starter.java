package org.niyihua;

import cn.hutool.core.util.StrUtil;
import org.niyihua.encrypt.constant.EncryptMethod;
import org.niyihua.encrypt.constant.EncryptType;
import org.niyihua.encrypt.encrypt.*;
import org.niyihua.encrypt.encrypt.RsaUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.niyihua.encrypt.constant.EncryptMethod.*;
import static org.niyihua.encrypt.constant.EncryptType.加密;
import static org.niyihua.encrypt.constant.EncryptType.解密;


public class Starter {
    private static final JFrame FRAME = new JFrame("加密小工具");
    private static final JTextArea INPUT_TEXT_AREA = new JTextArea(3, 30);
    private static final JTextArea OUTPUT_TEXT_AREA = new JTextArea(3, 30);
    private static final JTextArea KEY_TEXT_AREA = new JTextArea(2, 20);
    private static final JComboBox ENCRYPT_MODE = new JComboBox();
    private static final EncryptMethod[] ENCRYPT_METHODS = {RSA, EncryptMethod.MD5,SM2,SM4,AES,DES};
    private static final EncryptType[] ENCRYPT_TYPES = {加密,解密};
    private static final JComboBox COMBO_BOX_ENCRYPT_TYPES = new JComboBox();
    private static final Map<EncryptMethod,Encrypt> ENC_MAP = new HashMap();
    private static final String DESIGN_BY = "-- powered by nyh";
    static {
        INPUT_TEXT_AREA.setLineWrap(false);
        OUTPUT_TEXT_AREA.setLineWrap(true);
        ENCRYPT_MODE.setModel(new DefaultComboBoxModel(ENCRYPT_METHODS));
        COMBO_BOX_ENCRYPT_TYPES.setModel(new DefaultComboBoxModel(ENCRYPT_TYPES));
        ENCRYPT_MODE.setBounds(15, 15, 100, 25);
        ENC_MAP.put(RSA,new RsaUtils());
        ENC_MAP.put(SM2,new SM2Util());
        ENC_MAP.put(SM4,new SM4Util());
        ENC_MAP.put(AES,new AES());
        ENC_MAP.put(DES,new DES());
    }

    public static void main(String[] args){
        FRAME.setSize(400, 450);
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(10,30,350,80);
        jScrollPane.setViewportView(INPUT_TEXT_AREA);
        panel.add(jScrollPane);
        JLabel inputLabel = new JLabel();
        inputLabel.setText("输入：");
        inputLabel.setBounds(10,5,300,15);
        panel.add(inputLabel);
        JLabel executeLabel = new JLabel();
        executeLabel.setText("秘钥：");
        executeLabel.setBounds(10,115,40,30);//秘钥label
        JScrollPane jScrollPane2 = new JScrollPane();
        jScrollPane2.setBounds(10,145,350,60);//秘钥
        jScrollPane2.setViewportView(KEY_TEXT_AREA);
        panel.add(jScrollPane);
        panel.add(jScrollPane2);
        panel.add(executeLabel);
        JLabel outputLabel = new JLabel();
        outputLabel.setText("输出：");
        outputLabel.setBounds(10,270,60,30);
        panel.add(outputLabel);
        JScrollPane jScrollPane3 = new JScrollPane();
        jScrollPane3.setBounds(10,300,350,90);//输出text
        jScrollPane3.setViewportView(OUTPUT_TEXT_AREA);
        panel.add(jScrollPane3);
        ENCRYPT_MODE.setBounds(10,230,60,30);//select
        COMBO_BOX_ENCRYPT_TYPES.setBounds(80,230,60,30);//select type
        panel.add(COMBO_BOX_ENCRYPT_TYPES);
        panel.add(ENCRYPT_MODE);
        JButton execute = new JButton("执行");
        JButton clear = new JButton("清空");
        execute.setBounds(300,230,60,30);
        clear.setBounds(230,230,60,30);
        panel.add(clear);
        panel.add(execute);
        clear.addActionListener(e->{
            OUTPUT_TEXT_AREA.setText("");
            INPUT_TEXT_AREA.setText("");
            KEY_TEXT_AREA.setText("");
        });
        ENCRYPT_MODE.addActionListener(e -> {
            if(e.getActionCommand().equals("comboBoxChanged")){
                if(Objects.equals(ENCRYPT_MODE.getSelectedItem(),MD5)){
                    KEY_TEXT_AREA.setEnabled(false);
                }else{
                    KEY_TEXT_AREA.setEnabled(true);
                }
            }
        });
        execute.addActionListener(e -> {
            String text = INPUT_TEXT_AREA.getText();
            String result = null;
            if(StrUtil.isEmpty(text)){
                OUTPUT_TEXT_AREA.setText("");
                return;
            }
            Object selectItem = ENCRYPT_MODE.getSelectedItem();
            if(Objects.equals(selectItem,MD5)){
                result = org.niyihua.encrypt.encrypt.MD5.md5(text);
                OUTPUT_TEXT_AREA.setText(result);
                return;
            }
            if(Objects.equals(COMBO_BOX_ENCRYPT_TYPES.getSelectedItem(),加密)){
                result = ENC_MAP.get(selectItem).encrypt(text, KEY_TEXT_AREA.getText());
            }else{
                result = ENC_MAP.get(selectItem).decrypt(text, KEY_TEXT_AREA.getText());
            }
            OUTPUT_TEXT_AREA.setText(result);
        });
        JLabel design = new JLabel(DESIGN_BY);
        design.setFont(new Font("宋体",Font.PLAIN,12));
        design.setBounds(250,380,120,40);
        panel.add(design);
        FRAME.setContentPane(panel);
        FRAME.setVisible(true);
    }

}
