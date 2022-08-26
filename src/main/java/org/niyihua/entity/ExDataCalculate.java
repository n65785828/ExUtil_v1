package org.niyihua.entity;



import java.math.BigDecimal;


public class ExDataCalculate {
    private String code;
    private String name;
    private BigDecimal upV;//涨幅
    private BigDecimal upSpeed;//涨速
    private BigDecimal openP;//开盘
    private BigDecimal nowVolume;//现量
    private BigDecimal liuTongZ;//流通市值Z
    private BigDecimal totalMoney;//总金额
    private BigDecimal openMoney;//开盘金额
    private BigDecimal closeVar;//封单额
    private BigDecimal flowMarketVaR;//流通市值
    private BigDecimal changeHand;// 换手%
    private BigDecimal nowPrice;//现价
    private BigDecimal liangBi;//量比
    private BigDecimal bestHighPer;//最高%
    private BigDecimal bestHigh;//最高
    private BigDecimal bestLowPer;//最低%
    private BigDecimal bestLow;//最低
    private BigDecimal nowOpen;//今开
    private BigDecimal allVolume;//总量
    private BigDecimal salePrice;//卖价
    private BigDecimal yesterdayEnd;//昨收
    private BigDecimal onePointOneTime;//1.1倍
    private BigDecimal onePoint21Time;//1.1倍*1.1
    private BigDecimal onePointTwoTime;//1.2倍
    private BigDecimal onePoint44Time;//1.2倍*1.2
    private String remark = "";//删选备注

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUpV() {
        return upV;
    }

    public void setUpV(BigDecimal upV) {
        this.upV = upV;
    }

    public BigDecimal getUpSpeed() {
        return upSpeed;
    }

    public void setUpSpeed(BigDecimal upSpeed) {
        this.upSpeed = upSpeed;
    }

    public BigDecimal getOpenP() {
        return openP;
    }

    public void setOpenP(BigDecimal openP) {
        this.openP = openP;
    }

    public BigDecimal getNowVolume() {
        return nowVolume;
    }

    public void setNowVolume(BigDecimal nowVolume) {
        this.nowVolume = nowVolume;
    }

    public BigDecimal getLiuTongZ() {
        return liuTongZ;
    }

    public void setLiuTongZ(BigDecimal liuTongZ) {
        this.liuTongZ = liuTongZ;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getOpenMoney() {
        return openMoney;
    }

    public void setOpenMoney(BigDecimal openMoney) {
        this.openMoney = openMoney;
    }

    public BigDecimal getCloseVar() {
        return closeVar;
    }

    public void setCloseVar(BigDecimal closeVar) {
        this.closeVar = closeVar;
    }

    public BigDecimal getFlowMarketVaR() {
        return flowMarketVaR;
    }

    public void setFlowMarketVaR(BigDecimal flowMarketVaR) {
        this.flowMarketVaR = flowMarketVaR;
    }

    public BigDecimal getChangeHand() {
        return changeHand;
    }

    public void setChangeHand(BigDecimal changeHand) {
        this.changeHand = changeHand;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public BigDecimal getLiangBi() {
        return liangBi;
    }

    public void setLiangBi(BigDecimal liangBi) {
        this.liangBi = liangBi;
    }

    public BigDecimal getBestHighPer() {
        return bestHighPer;
    }

    public void setBestHighPer(BigDecimal bestHighPer) {
        this.bestHighPer = bestHighPer;
    }

    public BigDecimal getBestHigh() {
        return bestHigh;
    }

    public void setBestHigh(BigDecimal bestHigh) {
        this.bestHigh = bestHigh;
    }

    public BigDecimal getBestLowPer() {
        return bestLowPer;
    }

    public void setBestLowPer(BigDecimal bestLowPer) {
        this.bestLowPer = bestLowPer;
    }

    public BigDecimal getBestLow() {
        return bestLow;
    }

    public void setBestLow(BigDecimal bestLow) {
        this.bestLow = bestLow;
    }

    public BigDecimal getNowOpen() {
        return nowOpen;
    }

    public void setNowOpen(BigDecimal nowOpen) {
        this.nowOpen = nowOpen;
    }

    public BigDecimal getAllVolume() {
        return allVolume;
    }

    public void setAllVolume(BigDecimal allVolume) {
        this.allVolume = allVolume;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getYesterdayEnd() {
        return yesterdayEnd;
    }

    public void setYesterdayEnd(BigDecimal yesterdayEnd) {
        this.yesterdayEnd = yesterdayEnd;
    }

    public BigDecimal getOnePointOneTime() {
        return onePointOneTime;
    }

    public void setOnePointOneTime(BigDecimal onePointOneTime) {
        this.onePointOneTime = onePointOneTime;
    }

    public BigDecimal getOnePointTwoTime() {
        return onePointTwoTime;
    }

    public void setOnePointTwoTime(BigDecimal onePointTwoTime) {
        this.onePointTwoTime = onePointTwoTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public BigDecimal getOnePoint21Time() {
        return onePoint21Time;
    }

    public void setOnePoint21Time(BigDecimal onePoint21Time) {
        this.onePoint21Time = onePoint21Time;
    }

    public BigDecimal getOnePoint44Time() {
        return onePoint44Time;
    }

    public void setOnePoint44Time(BigDecimal onePoint44Time) {
        this.onePoint44Time = onePoint44Time;
    }
}
