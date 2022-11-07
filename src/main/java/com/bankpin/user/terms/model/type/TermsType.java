package com.bankpin.user.terms.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TermsType {

    TOS001("TOS001","뱅크핀 회원약관"),
    TOS002("TOS002","개인정보 제3자 제공 동의"),
    TOS003("TOS003","개인정보 수집 및 이용 동의"),
    TOS004("TOS004","뱅크핀 개인정보 처리방침"),
    TOS005("TOS005","KCB 올크레딧 이용약관"),
    TOS006("TOS006","개인(신용)정보 수집,이용 동의(KCB)"),
    TOS007("TOS007","개인(신용)정보 제3자 제공 동의(KCB)"),
    TOS008("TOS008","통신사 이용 약관 sk,lgu+,kt"),
    TOS009("TOS009","고유식별정보처리 동의"),
    TOS010("TOS010","개인정보 제3자 제공 동의 sk,kt,lgu+,알뜰폰"),
    TOS011("TOS011","개인정보 수집,이용,취급 위탁동의 sk, lgu+, kt"),
    TOS012("TOS012","본인확인서비스 이용약관"),
    TOS013("TOS013","민감정보 수집,이용,제공 동의"),
    TOS014("TOS014","광고,마케팅 수신 동의"),
    TOS015("TOS015","뱅크핀 회원약관(신용)"),
    TOS016("TOS016","뱅크핀 개인정보 처리방침(신용)"),
    TOS017("TOS017","개인신용정보 수집,이용,제공 동의(신용)"),
    TOS018("TOS018","개인신용정보 수집,이용,제공 동의 금융기관용(신용)"),
    TOS019("TOS019","개인신용정보 수집,이용,제공 동의 서민금융진흥원(신용)"),
    TOS020("TOS020","개인신용정보 수집·이용 제공 조회 동의 금융기관용(신용)"),
    TOS021("TOS021","개인신용정보 수집·이용 제공 조회 동의 서민금융진흥원(신용)"),
    TOS022("TOS022","개인정보 수집 및 이용동의(비로그인)"),
    TOS023("TOS023","민원 및 분쟁처리 절차"),
    TOS024("TOS024","금융소비자보호(자료열람요구권)"),
    TOS025("TOS025","금융소비자보호(고지사항)"),
    ;

    private final String key;
    private final String description;

    public static TermsType of(String key) {
        return Arrays.stream(TermsType.values())
                .filter(r -> r.getKey().equals(key))
                .findAny()
                .orElse(null);
    }


    @JsonCreator
    public static TermsType from(String s) {
        return TermsType.valueOf(s.toUpperCase());
    }

}