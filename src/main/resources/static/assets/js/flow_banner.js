jQuery(function() {

    $("#banner ul").carouFredSel({
        align : "left",
        width : 896, // 가로길이
        height : 51, // 세로길이
        items : {
        visible : 6 // 보여지는 갯수 (5개가 보여진다면 +1을 하여 버블링 효과를 막는다.)
        },
        scroll : {
        items : 1, // 롤링넘어가는 갯수
        duration : 800, //롤링 속도
        pauseOnHover : false // 마우스 오버시 롤링멈춤 true, 롤링작동 false
        },
        next : "#banner_right", // 오른쪽으로 이동 버튼
        prev : "#banner_left", // 왼쪽으로 이동 버튼
        direction : "left" // 롤링 방향
    });

    $("#banner_pause").click(function(){
        $("#banner ul").trigger("pause");
        $("#banner_stop").hide();
        $("#banner_start").show();
        return true;
    });

    $("#banner_play").click(function(){
        $("#banner ul").trigger("play", 1);
        $("#banner_stop").show();
        $("#banner_start").hide();
        return true;
    });

});