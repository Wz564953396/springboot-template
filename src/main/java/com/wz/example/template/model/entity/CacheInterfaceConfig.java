package com.wz.example.template.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zz_Wang
 * @since 2022-12-10
 */
@TableName("cache_interface_config")
@ApiModel(value = "CacheInterfaceConfig对象", description = "")
public class CacheInterfaceConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("业务主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("缓存接口路径")
    private String requestPath;

    @ApiModelProperty("缓存接口参数")
    private String requestParam;

    @ApiModelProperty("缓存接口请求方式")
    private String requestMethod;

    @ApiModelProperty("缓存key")
    private String cacheKey;

    @ApiModelProperty("有效时间（单位：秒）")
    private Long cacheTimeout;

    @ApiModelProperty("最新一次刷新缓存时间")
    private LocalDateTime cacheLatestTime;

    @ApiModelProperty("状态：1-有效，0-无效")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
    public Long getCacheTimeout() {
        return cacheTimeout;
    }

    public void setCacheTimeout(Long cacheTimeout) {
        this.cacheTimeout = cacheTimeout;
    }
    public LocalDateTime getCacheLatestTime() {
        return cacheLatestTime;
    }

    public void setCacheLatestTime(LocalDateTime cacheLatestTime) {
        this.cacheLatestTime = cacheLatestTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CacheInterfaceConfig{" +
            "id=" + id +
            ", requestPath=" + requestPath +
            ", requestParam=" + requestParam +
            ", requestMethod=" + requestMethod +
            ", cacheKey=" + cacheKey +
            ", cacheTimeout=" + cacheTimeout +
            ", cacheLatestTime=" + cacheLatestTime +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
