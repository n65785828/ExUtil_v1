package org.niyihua;

import cn.hutool.core.util.StrUtil;
import org.niyihua.encrypt.encrypt.*;
import org.niyihua.encrypt.encrypt.RsaUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Starter {

    private static final JTextArea inputTextArea = new JTextArea(3, 30);
    private static final JTextArea outPutTextArea = new JTextArea(3, 30);
    private static final JTextArea keyTextArea = new JTextArea(2, 20);
    private static final JFrame jf = new JFrame("加密小工具");
    private static final JComboBox encryptMode = new JComboBox();
    private static final String[] encryptType = {"RSA","MD5","SM2","SM4","AES","DES"};
    private static final JComboBox type = new JComboBox();
    private static final String[] typeStr = {"加密","解密"};
    private static final Map<String ,Encrypt> ENC_MAP = new HashMap();
    static {
        inputTextArea.setLineWrap(false);
        outPutTextArea.setLineWrap(true);
        encryptMode.setModel(new DefaultComboBoxModel(encryptType));
        type.setModel(new DefaultComboBoxModel(typeStr));
        encryptMode.setBounds(15, 15, 100, 25);
        ENC_MAP.put("RSA",new RsaUtils());
        ENC_MAP.put("SM2",new SM2Util());
        ENC_MAP.put("SM4",new SM4Util());
        ENC_MAP.put("AES",new AES());
        ENC_MAP.put("DES",new DES());
    }

    public static void main(String[] args){
        jf.setSize(400, 450);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(10,30,350,80);
        jScrollPane.setViewportView(inputTextArea);
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
        jScrollPane2.setViewportView(keyTextArea);
        panel.add(jScrollPane);
        panel.add(jScrollPane2);
        panel.add(executeLabel);
        JLabel outputLabel = new JLabel();
        outputLabel.setText("输出：");
        outputLabel.setBounds(10,270,60,30);
        panel.add(outputLabel);
        JScrollPane jScrollPane3 = new JScrollPane();
        jScrollPane3.setBounds(10,300,350,90);//输出text
        jScrollPane3.setViewportView(outPutTextArea);
        panel.add(jScrollPane3);
        encryptMode.setBounds(10,230,60,30);//select
        type.setBounds(80,230,60,30);//select type
        panel.add(type);
        panel.add(encryptMode);
        JButton execute = new JButton("执行");
        JButton clear = new JButton("清空");
        execute.setBounds(300,230,60,30);
        clear.setBounds(230,230,60,30);
        panel.add(clear);
        panel.add(execute);
        clear.addActionListener(e->{
            outPutTextArea.setText("");
            inputTextArea.setText("");
            keyTextArea.setText("");
        });
        encryptMode.addActionListener(e -> {
            if(e.getActionCommand().equals("comboBoxChanged")){
                if(Objects.equals(encryptMode.getSelectedItem(),"MD5")){
                    keyTextArea.setEnabled(false);
                }else{
                    keyTextArea.setEnabled(true);
                }
            }
        });
        execute.addActionListener(e -> {
            String text = inputTextArea.getText();
            String result = null;
            if(StrUtil.isEmpty(text)){
                outPutTextArea.setText("");
                return;
            }
            Object selectItem = encryptMode.getSelectedItem();
            if(Objects.equals(selectItem,"MD5")){
                result = MD5.md5(text);
                outPutTextArea.setText(result);
                return;
            }
            if(Objects.equals(type.getSelectedItem(),"加密")){
                result = ENC_MAP.get(selectItem).encrypt(text,keyTextArea.getText());
            }else{
                result = ENC_MAP.get(selectItem).decrypt(text,keyTextArea.getText());
            }
            outPutTextArea.setText(result);
        });
        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
