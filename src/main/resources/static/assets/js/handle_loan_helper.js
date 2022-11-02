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


const BANK_CODE_IMAGES = [
    {"id": "bnk01", "type": 1, "name": "sc제일은행", "image": "bank01.svg"},
    {"id": "bnk02", "type": 1, "name": "sh수협은행", "image": "bank02.svg"},
    {"id": "bnk03", "type": 1, "name": "대구은행", "image": "bank03.svg"},
    {"id": "bnk04", "type": 1, "name": "부산은행", "image": "bank04.svg"},
    {"id": "bnk05", "type": 1, "name": "광주은행", "image": "bank05.svg"},
    {"id": "bnk06", "type": 2, "name": "부산저축은행", "image": "bank06.svg"},
    {"id": "bnk07", "type": 2, "name": "ibk저축은행", "image": "bank07.svg"},
    {"id": "bnk08", "type": 2, "name": "ok저축은행", "image": "bank08.svg"},
    {"id": "bnk09", "type": 2, "name": "osb저축은행", "image": "bank09.svg"},
    {"id": "bnk10", "type": 2, "name": "sbi저축은행", "image": "bank10.svg"},
    {"id": "bnk11", "type": 2, "name": "다올저축은행", "image": "bank11.svg"},
    {"id": "bnk12", "type": 2, "name": "웰컴저축은행", "image": "bank12.svg"},
    {"id": "bnk13", "type": 2, "name": "키움저축은행", "image": "bank13.svg"},
    {"id": "bnk14", "type": 2, "name": "하나저축은행", "image": "bank14.svg"},
    {"id": "bnk15", "type": 2, "name": "고려저축은행", "image": "bank15.svg"},
    {"id": "bnk16", "type": 2, "name": "동원제일저축은행", "image": "bank16.svg"},
    {"id": "bnk17", "type": 2, "name": "모아저축은행", "image": "bank17.svg"},
    {"id": "bnk18", "type": 2, "name": "상상인저축은행", "image": "bank18.svg"},
    {"id": "bnk19", "type": 2, "name": "새마을금고", "image": "bank19.svg"},
    {"id": "bnk20", "type": 2, "name": "수협", "image": "bank20.svg"},
    {"id": "bnk21", "type": 2, "name": "신협", "image": "bank21.svg"},
    {"id": "bnk22", "type": 2, "name": "한화생명", "image": "bank22.svg"},
    {"id": "bnk23", "type": 2, "name": "삼성생명", "image": "bank23.svg"},
    {"id": "bnk24", "type": 2, "name": "흥국생명", "image": "bank24.svg"},
    {"id": "bnk25", "type": 2, "name": "교보생명", "image": "bank25.svg"},
    {"id": "bnk26", "type": 2, "name": "우리카드", "image": "bank26.svg"},
    {"id": "bnk27", "type": 2, "name": "BNK캐피탈", "image": "bank27.svg"},
    {"id": "bnk28", "type": 2, "name": "JB우리캐피탈", "image": "bank28.svg"},
    {"id": "bnk29", "type": 2, "name": "롯데캐피탈", "image": "bank29.svg"},
    {"id": "bnk30", "type": 2, "name": "OK캐피탈", "image": "bank30.svg"},
    {"id": "bnk31", "type": 2, "name": "하나캐피탈", "image": "bank31.svg"}
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
