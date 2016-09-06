package com.study.mongo.demo;

import org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  车辆查询DTO，包含缓存的车辆信息和相关网点、公司、价格、配置等信息
 */
public class CarQueryDTO implements Serializable {

    private static final long serialVersionUID = -49055301750586495L;
    private String carId;// 车辆ID
    private String carName;// 车辆名称
    private String pickCarDate;// 取车日期
    private String returnCarDate;// 还车日期
    private String brandId;// 品牌号
    private String brandName;// 品牌
    private String carType;// 车辆类型(轿车，越野车，商务车)
    private String minPrice;// 最低价格
    private String maxPrice;// 最高价格
    private String orderBy;// 排序类型 1：距离，2：价格
    private String cityId;// 城市编号
    private String storeId;// 门店ID
    private String storeName; // 门店名称
    private String companyId;// 公司ID
    private String companyName;// 公司名称
    private String contactPhone;// 联系电话
    private String storeCartypeId;// 门店车型ID
    private String stationId;// 网点Id
    private String stationName;// 网点名称
    private String stationAddress;// 网点地址
    private Double longitude;// 经度
    private Double latitude;// 纬度
    private Location location;//位置
    private Double baiduLongitude;// 百度经度
    private Double baiduLatitude;// 百度纬度
    private String status;//状态
    private String transmission;// 手动/自动
    private Double distance;// 距离
    private String carPic;// 图片地址
    private String carBigPic;// 车辆详细信息图片地址
    private String type;// 租赁类型：1：长租2：短租 3：分时4：代驾
    private String comboId;// 套餐ID
    private String addressGetCar;// 取车位置
    private String capacity;// 容积，可容纳多少人坐
    private String fuel;// 燃油型号
    private String gps;// 导航仪
    private String reverseImage;// 倒车影像
    private BigDecimal dayPrice;// 日租价格
    private BigDecimal hourPrice;// 时租租金
    private BigDecimal packageLeasePrice;// 套餐租金
    private String earnestPrice;
    private String license;// 车牌号
    private String comCartypeId;// 公司车型
    private String comCartypeStyle;// 公司车型年款
    private String comCartypeName;// 公司车型名称
    private String color;// 颜色
    private String electrombile;// 是否是电动车
    private String mileLeft;// 续航里程
    private String soc;// 电量百分比
    private String fuelPercentage;//燃油剩余百分比
    private String priceTemplateId;// 价格模板id
    private Integer priceIdFlag;// 价格模板标示 1：门店；2：车辆


    public CarQueryDTO(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = new Location("Point", new Double[]{longitude, latitude});
        this.carId = RandomStringUtils.randomNumeric(10);
        this.carName = "测试用";
        this.brandId = RandomStringUtils.randomNumeric(10);
        this.brandName = "测试用";
        this.brandId = RandomStringUtils.randomNumeric(10);
        this.brandName = "测试用";
        this.carType = "测试用";
        this.cityId = RandomStringUtils.randomNumeric(10);
        this.storeId = "测试用";
        this.storeName = "测试用";
        this.companyId = RandomStringUtils.randomNumeric(10);
        this.companyName = "测试用";
        this.contactPhone = "测试用";
        this.storeCartypeId = RandomStringUtils.randomNumeric(10);
        this.stationId = RandomStringUtils.randomNumeric(10);
        this.stationName = "测试用";
        this.stationAddress = "测试用";
        this.transmission = "1";
        this.carPic = "测试用";
        this.carBigPic = "测试用";
        this.capacity = "5";
        this.fuel = "93";
        this.gps = "1";
        this.reverseImage = "1";
        this.dayPrice = BigDecimal.ONE;// 日租价格
        this.hourPrice = BigDecimal.ONE;// 时租租金
        this.packageLeasePrice = BigDecimal.ONE;// 套餐租金
        this.earnestPrice = "测试用";
        this.license = "测试用";
        this.comCartypeId = "测试用";
        this.comCartypeStyle = "测试用";
        this.comCartypeName = "测试用";
        this.color = "测试用";
        this.electrombile = "测试用";
        this.mileLeft = "测试用";
        this.soc = "测试用";
        this.fuelPercentage = "测试用";
        this.priceTemplateId = "测试用";
        this.priceIdFlag = 0;// 价格模板标示 1：门店；2：车辆
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getPickCarDate() {
        return pickCarDate;
    }

    public void setPickCarDate(String pickCarDate) {
        this.pickCarDate = pickCarDate;
    }

    public String getReturnCarDate() {
        return returnCarDate;
    }

    public void setReturnCarDate(String returnCarDate) {
        this.returnCarDate = returnCarDate;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStoreCartypeId() {
        return storeCartypeId;
    }

    public void setStoreCartypeId(String storeCartypeId) {
        this.storeCartypeId = storeCartypeId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getBaiduLongitude() {
        return baiduLongitude;
    }

    public void setBaiduLongitude(Double baiduLongitude) {
        this.baiduLongitude = baiduLongitude;
    }

    public Double getBaiduLatitude() {
        return baiduLatitude;
    }

    public void setBaiduLatitude(Double baiduLatitude) {
        this.baiduLatitude = baiduLatitude;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getCarPic() {
        return carPic;
    }

    public void setCarPic(String carPic) {
        this.carPic = carPic;
    }

    public String getCarBigPic() {
        return carBigPic;
    }

    public void setCarBigPic(String carBigPic) {
        this.carBigPic = carBigPic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getAddressGetCar() {
        return addressGetCar;
    }

    public void setAddressGetCar(String addressGetCar) {
        this.addressGetCar = addressGetCar;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getReverseImage() {
        return reverseImage;
    }

    public void setReverseImage(String reverseImage) {
        this.reverseImage = reverseImage;
    }

    public BigDecimal getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }

    public BigDecimal getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(BigDecimal hourPrice) {
        this.hourPrice = hourPrice;
    }

    public BigDecimal getPackageLeasePrice() {
        return packageLeasePrice;
    }

    public void setPackageLeasePrice(BigDecimal packageLeasePrice) {
        this.packageLeasePrice = packageLeasePrice;
    }

    public String getEarnestPrice() {
        return earnestPrice;
    }

    public void setEarnestPrice(String earnestPrice) {
        this.earnestPrice = earnestPrice;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getComCartypeId() {
        return comCartypeId;
    }

    public void setComCartypeId(String comCartypeId) {
        this.comCartypeId = comCartypeId;
    }

    public String getComCartypeStyle() {
        return comCartypeStyle;
    }

    public void setComCartypeStyle(String comCartypeStyle) {
        this.comCartypeStyle = comCartypeStyle;
    }

    public String getComCartypeName() {
        return comCartypeName;
    }

    public void setComCartypeName(String comCartypeName) {
        this.comCartypeName = comCartypeName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getElectrombile() {
        return electrombile;
    }

    public void setElectrombile(String electrombile) {
        this.electrombile = electrombile;
    }

    public String getMileLeft() {
        return mileLeft;
    }

    public void setMileLeft(String mileLeft) {
        this.mileLeft = mileLeft;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getFuelPercentage() {
        return fuelPercentage;
    }

    public void setFuelPercentage(String fuelPercentage) {
        this.fuelPercentage = fuelPercentage;
    }

    public String getPriceTemplateId() {
        return priceTemplateId;
    }

    public void setPriceTemplateId(String priceTemplateId) {
        this.priceTemplateId = priceTemplateId;
    }

    public Integer getPriceIdFlag() {
        return priceIdFlag;
    }

    public void setPriceIdFlag(Integer priceIdFlag) {
        this.priceIdFlag = priceIdFlag;
    }
}
