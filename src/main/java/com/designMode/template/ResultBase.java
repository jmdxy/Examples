package com.designMode.template;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ResultBase<T> implements Serializable {

    private static final long   serialVersionUID = 2057948781441813066L;
    private boolean             isSuccess        = false;
    private String              errorMsg         = "";
    private String              errorCode;
    private T                   value;
    private String              trackId;
    private Map<String, Object> additionalInfo   = new HashMap<String, Object>();

    public ResultBase() {
        super();
    }

    public ResultBase(T value) {
        super();
        this.isSuccess = true;
        this.value = value;
    }


    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void appendAdditionalInfo(String key, Object value) {
        this.additionalInfo.put(key, value);
    }

    public <P> P getAdditionalInfo(String key) {
        return (P) additionalInfo.get(key);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMsg() {
        if(StringUtils.isEmpty(this.errorMsg)) {
            return this.errorMsg;
        }
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public static <T> ResultBase<T> success(T responseDTo) {
        return new ResultBase<>(responseDTo);
    }

    public static <T> ResultBase<T> failOfCodeWithMsg(String errorCode, String msg) {
        ResultBase<T> rt = new ResultBase<>();
        rt.setErrorCode(errorCode);
        rt.setErrorMsg(msg);
        return rt;
    }


}
