Handlebars.registerHelper("toCurrency", function (value) {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
});

Handlebars.registerHelper("toFixed", function (value) {
    return parseFloat(value).toFixed(2);
});
