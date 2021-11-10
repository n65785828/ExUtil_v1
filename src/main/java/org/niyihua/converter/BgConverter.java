package org.niyihua.converter;



import cn.hutool.core.util.StrUtil;
import org.niyihua.entity.ExData;
import org.niyihua.entity.ExDataCalculate;

import java.math.BigDecimal;

public class BgConverter {
    private static final BigDecimal opOne = new BigDecimal("1.1");
    private static final BigDecimal opTwo = new BigDecimal("1.2");
    public static ExDataCalculate convert(ExData exData){
        ExDataCalculate exDataCalculate = new ExDataCalculate();
        exDataCalculate.setCode(exData.getCode());
        exDataCalculate.setName(exData.getName());
        if(StrUtil.isBlank(exData.getUpV())||(StrUtil.isNotBlank(exData.getUpV())&&exData.getUpV().contains("--"))){
            exDataCalculate.setUpV(null);
        }else{
            exDataCalculate.setUpV(new BigDecimal(exData.getUpV()));
        }
        if(StrUtil.isBlank(exData.getUpSpeed())||(StrUtil.isNotBlank(exData.getUpSpeed())&&exData.getUpSpeed().contains("--"))){
            exDataCalculate.setUpSpeed(null);
        }else{
            exDataCalculate.setUpSpeed(new BigDecimal(exData.getUpSpeed()));
        }

        if(StrUtil.isBlank(exData.getOpenP())||(StrUtil.isNotBlank(exData.getOpenP())&&exData.getOpenP().contains("--"))){
            exDataCalculate.setOpenP(null);
        }else{
            exDataCalculate.setOpenP(new BigDecimal(exData.getOpenP()));
        }

        if(StrUtil.isBlank(exData.getNowVolume())||(StrUtil.isNotBlank(exData.getNowVolume())&&exData.getNowVolume().contains("--"))){
            exDataCalculate.setNowVolume(null);
        }else{
            exDataCalculate.setNowVolume(new BigDecimal(exData.getNowVolume()));
        }

        if(StrUtil.isBlank(exData.getLiuTongZ())||(StrUtil.isNotBlank(exData.getLiuTongZ())&&exData.getLiuTongZ().contains("--"))){
            exDataCalculate.setLiuTongZ(null);
        }else{
            StringBuffer stringBuffer = new StringBuffer(exData.getLiuTongZ().trim());
            stringBuffer.setLength(stringBuffer.length()-1);
            exDataCalculate.setLiuTongZ(new BigDecimal(stringBuffer.toString()));
        }

        if(StrUtil.isBlank(exData.getTotalMoney())||(StrUtil.isNotBlank(exData.getTotalMoney())&&exData.getTotalMoney().contains("--"))){
            exDataCalculate.setTotalMoney(null);
        }else{
            exDataCalculate.setTotalMoney(new BigDecimal(exData.getTotalMoney()));
        }

        if(StrUtil.isBlank(exData.getOpenMoney())||(StrUtil.isNotBlank(exData.getOpenMoney())&&exData.getOpenMoney().contains("--"))){
            exDataCalculate.setOpenMoney(null);
        }else{
            exDataCalculate.setOpenMoney(new BigDecimal(exData.getOpenMoney()));
        }

        if(StrUtil.isBlank(exData.getCloseVar())||(StrUtil.isNotBlank(exData.getCloseVar())&&exData.getCloseVar().contains("--"))){
            exDataCalculate.setCloseVar(null);
        }else{
            exDataCalculate.setCloseVar(new BigDecimal(exData.getCloseVar()));
        }

        if(StrUtil.isBlank(exData.getFlowMarketVaR())||(StrUtil.isNotBlank(exData.getFlowMarketVaR())&&exData.getFlowMarketVaR().contains("--"))){
            exDataCalculate.setFlowMarketVaR(null);
        }else{
            StringBuffer stringBuffer = new StringBuffer(exData.getFlowMarketVaR().trim());
            stringBuffer.setLength(stringBuffer.length()-1);
            exDataCalculate.setFlowMarketVaR(new BigDecimal(stringBuffer.toString()));
        }

        if(StrUtil.isBlank(exData.getChangeHand())||(StrUtil.isNotBlank(exData.getChangeHand())&&exData.getChangeHand().contains("--"))){
            exDataCalculate.setChangeHand(null);
        }else{
            exDataCalculate.setChangeHand(new BigDecimal(exData.getChangeHand()));
        }
        if(StrUtil.isBlank(exData.getChangeHand())||(StrUtil.isNotBlank(exData.getChangeHand())&&exData.getChangeHand().contains("--"))){
            exDataCalculate.setChangeHand(null);
        }else{
            exDataCalculate.setChangeHand(new BigDecimal(exData.getChangeHand()));
        }

        if(StrUtil.isBlank(exData.getNowPrice())||(StrUtil.isNotBlank(exData.getNowPrice())&&exData.getNowPrice().contains("--"))){
            exDataCalculate.setNowPrice(null);
        }else{
            exDataCalculate.setNowPrice(new BigDecimal(exData.getNowPrice()));
        }

        //量比
        if(StrUtil.isBlank(exData.getLiangBi())||(StrUtil.isNotBlank(exData.getLiangBi())&&exData.getLiangBi().contains("--"))){
            exDataCalculate.setLiangBi(null);
        }else{
            exDataCalculate.setLiangBi(new BigDecimal(exData.getLiangBi()));
        }


        //最高%
        if(StrUtil.isBlank(exData.getBestHighPer())||(StrUtil.isNotBlank(exData.getBestHighPer())&&exData.getBestHighPer().contains("--"))){
            exDataCalculate.setBestHighPer(null);
        }else{
            exDataCalculate.setBestHighPer(new BigDecimal(exData.getBestHighPer()));
        }

        //最高
        if(StrUtil.isBlank(exData.getBestHigh())||(StrUtil.isNotBlank(exData.getBestHigh())&&exData.getBestHigh().contains("--"))){
            exDataCalculate.setBestHigh(null);
        }else{
            exDataCalculate.setBestHigh(new BigDecimal(exData.getBestHigh()));
        }

        //最低%
        if(StrUtil.isBlank(exData.getBestLowPer())||(StrUtil.isNotBlank(exData.getBestLowPer())&&exData.getBestLowPer().contains("--"))){
            exDataCalculate.setBestLowPer(null);
        }else{
            exDataCalculate.setBestLowPer(new BigDecimal(exData.getBestLowPer()));
        }

        //最低
        if(StrUtil.isBlank(exData.getBestLow())||(StrUtil.isNotBlank(exData.getBestLow())&&exData.getBestLow().contains("--"))){
            exDataCalculate.setBestLow(null);
        }else{
            exDataCalculate.setBestLow(new BigDecimal(exData.getBestLow()));
        }

        //今开
        if(StrUtil.isBlank(exData.getNowOpen())||(StrUtil.isNotBlank(exData.getNowOpen())&&exData.getNowOpen().contains("--"))){
            exDataCalculate.setNowOpen(null);
        }else{
            exDataCalculate.setNowOpen(new BigDecimal(exData.getNowOpen()));
        }

        //总量
        if(StrUtil.isBlank(exData.getAllVolume())||(StrUtil.isNotBlank(exData.getAllVolume())&&exData.getAllVolume().contains("--"))){
            exDataCalculate.setAllVolume(null);
        }else{
            exDataCalculate.setAllVolume(new BigDecimal(exData.getAllVolume()));
        }

        //卖价
        if(StrUtil.isBlank(exData.getSalePrice())||(StrUtil.isNotBlank(exData.getSalePrice())&&exData.getSalePrice().contains("--"))){
            exDataCalculate.setSalePrice(null);
        }else{
            exDataCalculate.setSalePrice(new BigDecimal(exData.getSalePrice()));
        }

        //昨收
        if(StrUtil.isBlank(exData.getYesterdayEnd())||(StrUtil.isNotBlank(exData.getYesterdayEnd())&&exData.getYesterdayEnd().contains("--"))){
            exDataCalculate.setYesterdayEnd(null);
        }else{
            exDataCalculate.setYesterdayEnd(new BigDecimal(exData.getYesterdayEnd()));
        }

        //1.1  1.2
        if(exDataCalculate.getNowPrice()!=null){
            if(StrUtil.isNotEmpty(exDataCalculate.getCode())&&exDataCalculate.getCode().startsWith("30")){
                exDataCalculate.setOnePointTwoTime(exDataCalculate.getNowPrice().multiply(opTwo).setScale(2,BigDecimal.ROUND_HALF_UP));
            }else{
                exDataCalculate.setOnePointOneTime(exDataCalculate.getNowPrice().multiply(opOne).setScale(2,BigDecimal.ROUND_HALF_UP));
            }
        }

        return exDataCalculate;
    }

    public static ExData revert(ExDataCalculate exDataCalculate){
        ExData exData = new ExData();
        exData.setCode(exDataCalculate.getCode());
        exData.setName(exDataCalculate.getName());

        if(exDataCalculate.getUpV()==null){
            exData.setUpV("--");
        }else{
            exData.setUpV(exDataCalculate.getUpV().toString());
        }

        if(exDataCalculate.getUpSpeed()==null){
            exData.setUpSpeed("--");
        }else{
            exData.setUpSpeed(exDataCalculate.getUpSpeed().toString());
        }

        if(exDataCalculate.getOpenP()==null){
            exData.setOpenP("--");
        }else{
            exData.setOpenP(exDataCalculate.getOpenP().toString());
        }

        if(exDataCalculate.getNowVolume()==null){
            exData.setNowVolume("--");
        }else{
            exData.setNowVolume(exDataCalculate.getNowVolume().toString());
        }

        if(exDataCalculate.getLiuTongZ()==null){
            exData.setLiuTongZ("--");
        }else{
            exData.setLiuTongZ(exDataCalculate.getLiuTongZ().toString()+"亿");
        }

        if(exDataCalculate.getTotalMoney()==null){
            exData.setTotalMoney("--");
        }else{
            exData.setTotalMoney(exDataCalculate.getTotalMoney().toString());
        }

        if(exDataCalculate.getOpenMoney()==null){
            exData.setOpenMoney("--");
        }else{
            exData.setOpenMoney(exDataCalculate.getOpenMoney().toString());
        }

        if(exDataCalculate.getCloseVar()==null){
            exData.setCloseVar("--");
        }else{
            exData.setCloseVar(exDataCalculate.getCloseVar().toString());
        }

        if(exDataCalculate.getFlowMarketVaR()==null){
            exData.setFlowMarketVaR("--");
        }else{
            exData.setFlowMarketVaR(exDataCalculate.getFlowMarketVaR().toString()+"亿");
        }

        if(exDataCalculate.getChangeHand()==null){
            exData.setChangeHand("--");
        }else{
            exData.setChangeHand(exDataCalculate.getChangeHand().toString());
        }

        if(exDataCalculate.getNowPrice()==null){
            exData.setNowPrice("--");
        }else{
            exData.setNowPrice(exDataCalculate.getNowPrice().toString());
        }
        return exData;
    }
}
