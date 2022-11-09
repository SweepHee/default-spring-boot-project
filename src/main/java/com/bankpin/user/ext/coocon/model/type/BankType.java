package com.bankpin.user.ext.coocon.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BankType {

    SCJaeil("SC제일은행", "00023", "230000001"),
    SHSuHyup("SH수협은행", "00007", "700000001"),
    Daegu("대구은행", "00031", "031000001"),
    KyungNam("경남은행", "00039", "039000001"),
    Busan("부산은행", "00032", "032000001"),
    Gwangju("광주은행", "00034", "034000001"),
    IBKJeoChook("IBK저축은행", "00502", "502005001"),
    OKJeoChook("OK저축은행", "00503", "503005001"),
    OSBJeoChook("OSB저축은행", "00504", "504005001"),
    SBIJeoChook("sbi저축은행", "00050", "050000001"),
    DaolJeoChook("다올저축은행", "00504", "504005008"),
    WelcomeJeoChook("웰컴저축은행", "00050", "050000001"),
    KiumJeoChook("키움저축은행", "00050", "050000002"),
    HanaJeoChook("하나저축은행", "00050", "050000003"),
    GoryeoJeoChook("고려저축은행", "00050", "050000004"),
    DongwonJaeilJeoChook("동원제일저축은행", "00050", "050000005"),
    MoaJeoChook("모아저축은행", "00050", "050000006"),
    SangSangInJeoChook("상상인저축은행", "00050", "050000007"),
    SaeMaulGeumgo("새마음금고", "00045", "045000001"),
    SinHyup("신협", "00048", "048000001"),
    HanwhaSaengMyung("한화생명", "00432", "432000001"),
    SamsungSaengMyung("삼성생명", "00452", "452000001"),
    HeungkukSaengMyung("흥국생명", "00403", "403000001"),
    KyoboSaengMyung("교보생명", "00436", "436000001"),
    UriCard("우리카드", "00401", "401000001"),
    BNKCapital("BNK캐피탈", "00302", "302000001"),
    JBUriCapital("JB우리캐피탈", "00305", "305000001"),
    LotteCapital("롯데캐피탈", "00303", "303000001"),
    OKCapital("OK캐피탈", "00901", "901000001"),
    HanaCapital("하나캐피탈", "00310", "310000001"),
    BnkJeoChook("BNK저축은행", "00501", "501000001"),
    ;

    private final String name;
    private final String bankCd;
    private final String bankBrchCd;

}