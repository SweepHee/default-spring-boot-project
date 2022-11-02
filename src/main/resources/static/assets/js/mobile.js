const isMobile = (function() {
    const {userAgent, maxTouchPoints} = window.navigator;
    const isMac = /Macintosh/i.test(userAgent);
    if (isMac && maxTouchPoints > 0) { return true; }
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini|Mobi/i.test(userAgent);
})();

const isPlatform = {
    aOS: function() {
        return navigator.userAgent.match(/Android/i) == null ? false : true;
    },
    iOS: function() {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i) == null ? false : true;
    },
    other: function() {
        return navigator.userAgent.match(/webOS|BlackBerry|IEMobile|Opera Mini|Mobi/i) == null ? false : true;
    }
};
