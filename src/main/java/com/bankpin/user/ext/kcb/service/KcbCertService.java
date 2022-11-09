package com.bankpin.user.ext.kcb.service;


import com.bankpin.user.cust.mapper.CustAuthDtlMapper;
import com.bankpin.user.ext.kcb.config.LocalPropertyConfig;
import com.bankpin.user.ext.kcb.config.PropertyConfig;
import com.bankpin.user.ext.kcb.model.dto.PassAppCertDTO;
import com.bankpin.user.ext.kcb.model.dto.PassAppDTO;
import com.bankpin.user.ext.kcb.model.dto.SmsCertDTO;
import com.bankpin.user.ext.kcb.model.dto.SmsDTO;
import kcb.module.v3.exception.OkCertException;
import kcb.org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class KcbCertService {

    private final CustAuthDtlMapper custAuthDtlMapper;
    private final PropertyConfig propertyConfig;


    public SmsDTO.ReturnData sendSms(SmsDTO.Param param, HttpServletRequest request) throws OkCertException {

        param.setCpCd(propertyConfig.getCpCd());
        String target = propertyConfig.getTarget();
        String license = getLicensePath();
        String svcNAME = "IDS_HS_EMBED_SMS_REQ";

        String USER_IP = request.getRemoteAddr();
        System.out.println(license);
        String SITE_URL = "bankpin.co.kr";
        String SITE_NAME = "뱅크핀";

        JSONObject reqJson = new JSONObject();

        if (StringUtils.hasText(param.getTxSeqNo())) reqJson.put("TX_SEQ_NO", param.getTxSeqNo()); //SMS 재전송의 경우처리

        reqJson.put("NAME", param.getName());
        reqJson.put("BIRTHDAY", param.getBirthday());
        reqJson.put("SEX_CD", param.getSexCd());
        reqJson.put("NTV_FRNR_CD", param.getNtvFrnrCd());
        reqJson.put("TEL_COM_CD", param.getTelComCd());
        reqJson.put("TEL_NO", param.getTelNo());
        reqJson.put("USER_IP", USER_IP);
        reqJson.put("SITE_URL", SITE_URL);
        reqJson.put("SITE_NAME", SITE_NAME);
        reqJson.put("RQST_CAUS_CD", param.getRqstCausCd());
        reqJson.put("CHNL_CD", "0000"); // 필수값아님. 채널구분이 필요한 회원만 사용
        reqJson.put("APP_HASH_STR", param.getAppHashStr()); // 필수값아님. 구글 SMS Retriever API 사용 시 이용됨
        reqJson.put("SMS_RESEND_YN", param.getSmsResendYn()); // SMS 재전송건 여부 Y:재전송, N:첫요청

        reqJson.put("AGREE1", param.getAgree1());
        reqJson.put("AGREE2", param.getAgree2());
        reqJson.put("AGREE3", param.getAgree3());
        reqJson.put("AGREE4", param.getAgree4());

        kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
        String resultStr = okcert.callOkCert(target, param.getCpCd(), svcNAME, license,  reqJson.toString());

        JSONObject resJson = new JSONObject(resultStr);
        resJson.put("TEL_NO", param.getTelNo());

        return SmsDTO.ReturnData.toReturnData(resJson);

    }


    public SmsCertDTO.ReturnData certificationSMS(SmsCertDTO.Param param) throws OkCertException {

        param.setCpCd(propertyConfig.getCpCd());
        String target = propertyConfig.getTarget();
        String license = getLicensePath();

        String svcNAME = "IDS_HS_EMBED_SMS_CIDI";

        JSONObject reqJson = new JSONObject();
        reqJson.put("TX_SEQ_NO", param.getTxSeqNo());
        reqJson.put("TEL_NO", param.getTelNo());
        reqJson.put("OTP_NO", param.getOtpNo());


        kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();

        String resultStr = okcert.callOkCert(target, param.getCpCd(), svcNAME, license,  reqJson.toString());

        JSONObject resJson = new JSONObject(resultStr);

        return SmsCertDTO.ReturnData.toReturnData(resJson);

    }

    public PassAppDTO.ReturnData sendPass(PassAppDTO.Param param, HttpServletRequest request) throws OkCertException {


        param.setCpCd(propertyConfig.getCpCd());

        String target = propertyConfig.getTarget();
        String license = getLicensePath();
        String svcNAME = "IDS_HS_EMBED_PASS_REQ";

        String USER_IP = request.getRemoteAddr();
        String SITE_URL = "210.106.106.113";
        String SITE_NAME = "뱅크핀";

        JSONObject reqJson = new JSONObject();

        reqJson.put("TEL_COM_CD", param.getTelComCd());
        reqJson.put("USER_IP", USER_IP);
        reqJson.put("SITE_URL", SITE_URL);
        reqJson.put("SITE_NAME", SITE_NAME);
        reqJson.put("RQST_CAUS_CD", param.getRqstCausCd());

        reqJson.put("AGREE1", param.getAgree1());
        reqJson.put("AGREE2", param.getAgree2());
        reqJson.put("AGREE3", param.getAgree3());
        reqJson.put("AGREE4", param.getAgree4());

        kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();

        String resultStr = okcert.callOkCert(target, param.getCpCd(), svcNAME, license,  reqJson.toString());

        JSONObject resJson = new JSONObject(resultStr);
        return PassAppDTO.ReturnData.toReturnData(resJson);

    }

    public PassAppCertDTO.ReturnData certificationPass(PassAppCertDTO.Param param) throws OkCertException {

        param.setCpCd(propertyConfig.getCpCd());
        String target = propertyConfig.getTarget();
        String license = getLicensePath();
        String svcNAME = "IDS_HS_EMBED_PASS_CIDI";

        JSONObject reqJson = new JSONObject();
        reqJson.put("TX_SEQ_NO", param.getTxSeqNo());

        kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
        String resultStr = okcert.callOkCert(target, param.getCpCd(), svcNAME, license,  reqJson.toString());

        JSONObject resJson = new JSONObject(resultStr);
        return PassAppCertDTO.ReturnData.toReturnData(resJson);
    }


    private String getLicensePath() {
        return propertyConfig.getPath();
    }


}