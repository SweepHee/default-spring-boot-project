Handlebars.registerHelper("isWoman", function (value) {
    return value % 2 == 0 ? true : false;
});

Handlebars.registerHelper("toCurrency", function (value) {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
});

Handlebars.registerHelper("toFixed", function (value) {
    return parseFloat(value).toFixed(2);
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
    {"id": "bank01", "name": "sc제일은행", "image": "/bank01.svg"},
    {"id": "bank02", "name": "sh수협은행", "image": "/bank02.svg"},
    {"id": "bank03", "name": "대구은행", "image": "/bank03.svg"},
    {"id": "bank04", "name": "부산은행", "image": "/bank04.svg"},
    {"id": "bank05", "name": "광주은행", "image": "/bank05.svg"},
    {"id": "bank06", "name": "부산저축은행", "image": "/bank06.svg"},
    {"id": "bank07", "name": "ibk저축은행", "image": "/bank07.svg"},
    {"id": "bank08", "name": "ok저축은행", "image": "/bank08.svg"},
    {"id": "bank09", "name": "osb저축은행", "image": "/bank09.svg"},
    {"id": "bank10", "name": "sbi저축은행", "image": "/bank10.svg"},
    {"id": "bank11", "name": "다올저축은행", "image": "/bank11.svg"},
    {"id": "bank12", "name": "웰컴저축은행", "image": "/bank12.svg"},
    {"id": "bank13", "name": "키움저축은행", "image": "/bank13.svg"},
    {"id": "bank14", "name": "하나저축은행", "image": "/bank14.svg"},
    {"id": "bank15", "name": "고려저축은행", "image": "/bank15.svg"},
    {"id": "bank16", "name": "동원제일저축은행", "image": "/bank16.svg"},
    {"id": "bank17", "name": "모아저축은행", "image": "/bank17.svg"},
    {"id": "bank18", "name": "상상인저축은행", "image": "/bank18.svg"},
    {"id": "bank19", "name": "새마을금고", "image": "/bank19.svg"},
    {"id": "bank20", "name": "수협", "image": "/bank20.svg"},
    {"id": "bank21", "name": "신협", "image": "/bank21.svg"},
    {"id": "bank22", "name": "한화생명", "image": "/bank22.svg"},
    {"id": "bank23", "name": "삼성생명", "image": "/bank23.svg"},
    {"id": "bank24", "name": "흥국생명", "image": "/bank24.svg"},
    {"id": "bank25", "name": "교보생명", "image": "/bank25.svg"},
    {"id": "bank26", "name": "우리카드", "image": "/bank26.svg"},
    {"id": "bank27", "name": "BNK캐피탈", "image": "/bank27.svg"},
    {"id": "bank28", "name": "JB우리캐피탈", "image": "/bank28.svg"},
    {"id": "bank29", "name": "롯데캐피탈", "image": "/bank29.svg"},
    {"id": "bank30", "name": "OK캐피탈", "image": "/bank30.svg"},
    {"id": "bank31", "name": "하나캐피탈", "image": "/bank31.svg"}
];
Handlebars.registerHelper("bankImage", function (value) {
    const image = "/user/static/assets/images/common";
    const image_url = BANK_CODE_IMAGES.find(o => o.id == value) || {"image": "/blank.svg"};
    return image +"/"+ image_url.image;
});