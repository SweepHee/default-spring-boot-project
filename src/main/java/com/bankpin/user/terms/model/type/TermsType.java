package com.bankpin.user.terms.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TermsType {

    TOS001("TOS001","뱅크핀 회원약관", "01"),
    TOS002("TOS002","개인정보 제3자 제공 동의", "01"),
    TOS003("TOS003","개인정보 수집 및 이용 동의", "01"),
    TOS004("TOS004","뱅크핀 개인정보 처리방침", "01"),
    TOS005("TOS005","KCB 올크레딧 이용약관", "01"),
    TOS006("TOS006","개인(신용)정보 수집,이용 동의(KCB)", "01"),
    TOS007("TOS007","개인(신용)정보 제3자 제공 동의(KCB)", "01"),
    TOS008("TOS008","통신사 이용 약관 sk,lgu+,kt", "01"),
    TOS009("TOS009","고유식별정보처리 동의", "01"),
    TOS010("TOS010","개인정보 제3자 제공 동의 sk,kt,lgu+,알뜰폰", "01"),
    TOS011("TOS011","개인정보 수집,이용,취급 위탁동의 sk, lgu+, kt", "01"),
    TOS012("TOS012","본인확인서비스 이용약관", "01"),
    TOS013("TOS013","민감정보 수집,이용,제공 동의", "01"),
    TOS014("TOS014","광고,마케팅 수신 동의", "01"),
    TOS015("TOS015","뱅크핀 회원약관(신용)", "01"),
    TOS016("TOS016","뱅크핀 개인정보 처리방침(신용)", "01"),
    TOS017("TOS017","개인신용정보 수집,이용,제공 동의(신용)", "01"),
    TOS018("TOS018","개인신용정보 수집,이용,제공 동의 금융기관용(신용)", "01"),
    TOS019("TOS019","개인신용정보 수집,이용,제공 동의 서민금융진흥원(신용)", "01"),
    TOS020("TOS020","개인신용정보 수집·이용 제공 조회 동의 금융기관용(신용)", "01"),
    TOS021("TOS021","개인신용정보 수집·이용 제공 조회 동의 서민금융진흥원(신용)", "01"),
    TOS022("TOS022","개인정보 수집 및 이용동의(비로그인)", "01"),
    TOS023("TOS023","민원 및 분쟁처리 절차", "01"),
    TOS024("TOS024","금융소비자보호(자료열람요구권)", "01"),
    TOS025("TOS025","금융소비자보호(고지사항)", "01"),
    ;

    private final String key;
    private final String description;
    private final String apiCode; // Todo coocon 101 inlist1에서 동의코드로 사용 -> 물어보기

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