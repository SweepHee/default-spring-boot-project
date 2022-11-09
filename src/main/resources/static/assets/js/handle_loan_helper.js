Handlebars.registerHelper("isWoman", function (value) {
    return value % 2 == 0 ? true : false;
});

Handlebars.registerHelper("lnReqGbcdType", function (value) {
    let result = "신규";
    if (value == "1") {
        result = "신규";
    } else if (value == "2") {
        result = "대환";
    } else if (value == "3") {
        result = "연장";
    } else if (value == "4") {
        result = "증액";
    } else if (value == "5") {
        result = "조건변경";
    } else if (value == "6") {
        result = "채무인수";
    }
    return result;
});

Handlebars.registerHelper("lnUseGbcdType", function (value) {
    let result = "생활비";
    if (value == "01") {
        result = "생활비";
    } else if (value == "02") {
        result = "대환대출";
    } else if (value == "03") {
        result = "전월세보증금";
    } else if (value == "04") {
        result = "주택구입";
    } else if (value == "05") {
        result = "투자";
    } else if (value == "06") {
        result = "사업자금";
    } else if (value == "07") {
        result = "기타";
    } else if (value == "08") {
        result = "자동차 구입";
    }
    return result;
});

Handlebars.registerHelper("custHousTypecdType", function (value) {
    let result = "아파트";
    if (value == "1") {
        result = "아파트";
    } else if (value == "2") {
        result = "단독";
    } else if (value == "3") {
        result = "빌라/연립";
    } else if (value == "4") {
        result = "기타";
    }
    return result;
});

Handlebars.registerHelper("lnUseGbcdType", function (value) {
    let result = "생활비";
    if (value == "01") {
        result = "생활비";
    } else if (value == "02") {
        result = "대환대출";
    } else if (value == "03") {
        result = "전월세보증금";
    } else if (value == "04") {
        result = "주택구입";
    } else if (value == "05") {
        result = "투자";
    } else if (value == "06") {
        result = "사업자금";
    } else if (value == "07") {
        result = "기타";
    } else if (value == "08") {
        result = "자동차 구입";
    }
    return result;
});


// TODO storage
const BANK_CODE_IMAGES = [
    {"id": "230000001", "code": "00023", "type": 1, "name": "SC제일은행", "image": "bank01.svg"},
    {"id": "700000001", "code": "00007", "type": 1, "name": "SH수협은행", "image": "bank02.svg"},
    {"id": "031000001", "code": "00031", "type": 1, "name": "대구은행", "image": "bank03.svg"},
    {"id": "039000001", "code": "00039", "type": 1, "name": "경남은행", "image": "bank04.svg"},
    {"id": "032000001", "code": "00032", "type": 1, "name": "부산은행", "image": "bank04.svg"},
    {"id": "034000001", "code": "00034", "type": 1, "name": "광주은행", "image": "bank05.svg"},
    {"id": "501000001", "code": "00501", "type": 1, "name": "BNK저축은행", "image": "bank06.svg"},
    {"id": "502005001", "code": "00502", "type": 2, "name": "IBK저축은행", "image": "bank07.svg"},
    {"id": "503005001", "code": "00503", "type": 2, "name": "OK저축은행", "image": "bank08.svg"},
    {"id": "504005001", "code": "00504", "type": 2, "name": "OSB저축은행", "image": "bank09.svg"},
    {"id": "050000001", "code": "00050", "type": 2, "name": "sbi저축은행", "image": "bank10.svg"},
    {"id": "504005008", "code": "00504", "type": 2, "name": "다올저축은행", "image": "bank11.svg"},
    {"id": "050000001", "code": "00050", "type": 2, "name": "웰컴저축은행", "image": "bank12.svg"},
    {"id": "050000002", "code": "00050", "type": 2, "name": "키움저축은행", "image": "bank13.svg"},
    {"id": "050000003", "code": "00050", "type": 2, "name": "하나저축은행", "image": "bank14.svg"},
    {"id": "050000004", "code": "00050", "type": 2, "name": "고려저축은행", "image": "bank15.svg"},
    {"id": "050000005", "code": "00050", "type": 2, "name": "동원제일저축은행", "image": "bank16.svg"},
    {"id": "050000006", "code": "00050", "type": 2, "name": "모아저축은행", "image": "bank17.svg"},
    {"id": "050000007", "code": "00050", "type": 2, "name": "상상인저축은행", "image": "bank18.svg"},
    {"id": "045000001", "code": "00045", "type": 2, "name": "새마을금고", "image": "bank19.svg"},
    {"id": "bnk20", "code": "bnk20", "type": 2, "name": "수협", "image": "bank20.svg"},
    {"id": "048000001", "code": "00048", "type": 2, "name": "신협", "image": "bank21.svg"},
    {"id": "432000001", "code": "00432", "type": 2, "name": "한화생명", "image": "bank22.svg"},
    {"id": "452000001", "code": "00452", "type": 2, "name": "삼성생명", "image": "bank23.svg"},
    {"id": "403000001", "code": "00403", "type": 2, "name": "흥국생명", "image": "bank24.svg"},
    {"id": "436000001", "code": "00436", "type": 2, "name": "교보생명", "image": "bank25.svg"},
    {"id": "401000001", "code": "00401", "type": 2, "name": "우리카드", "image": "bank26.svg"},
    {"id": "302000001", "code": "00302", "type": 2, "name": "BNK캐피탈", "image": "bank27.svg"},
    {"id": "305000001", "code": "00305", "type": 2, "name": "JB우리캐피탈", "image": "bank28.svg"},
    {"id": "303000001", "code": "00303", "type": 2, "name": "롯데캐피탈", "image": "bank29.svg"},
    {"id": "901000001", "code": "00901", "type": 2, "name": "OK캐피탈", "image": "bank30.svg"},
    {"id": "310000001", "code": "00310", "type": 2, "name": "하나캐피탈", "image": "bank31.svg"}
];
Handlebars.registerHelper("bankImage", function (value) {
    const image = "/user/static/assets/images/common";
    if (value === '' || value == null || value == undefined) {
        return image +"/blank.svg";
    }
    const image_url = BANK_CODE_IMAGES.find(o => o.id == value) || {"image": "blank.svg"};
    return image +"/"+ image_url.image;
});
Handlebars.registerHelper("bankName", function (value) {
    if (value === '' || value == null || value == undefined) {
        return "";
    }
    const bank = BANK_CODE_IMAGES.find(o => o.id == value) || {"name": "은행"};
    return bank.name;
});
Handlebars.registerHelper("bankType", function (value) {
    if (value === '' || value == null || value == undefined) {
        return 1;
    }
    const bank = BANK_CODE_IMAGES.find(o => o.id == value) || {"type": 1};
    return bank.type;
});
