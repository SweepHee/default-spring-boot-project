class Bankpin
{
    static isMobilePlatform =
    {
        aOS: function() {
            return window.navigator.userAgent.match(/Android/i) == null ? false : true;
        },
        iOS: function() {
            return window.navigator.userAgent.match(/iPhone|iPad|iPod/i) == null ? false : true;
        },
        other: function() {
            return window.navigator.userAgent.match(/webOS|BlackBerry|IEMobile|Opera Mini|Mobi/i) == null ? false : true;
        }
    };

    static isMobile() {
        const { userAgent, maxTouchPoints } = window.navigator;
        const isMac = /Macintosh/i.test(userAgent);
        if (isMac && maxTouchPoints > 0) { return true; }
        return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini|Mobi/i.test(userAgent);
    }


    static pageLink(link) {
        try {
            if (this.isMobile()) {
                if (this.isMobilePlatform.aOS()) {
                    window.android.pageLink(link);
                } else if (this.isMobilePlatform.iOS()) {
                    window.webkit.messageHandlers.pageLink.postMessage(link);
                }
            } else {
                window.location.href = window.location.protocol +'//'+ window.location.host + link;
            }
        } catch(error) {
            console.error(error);
        }
    }

    static outLink(link) {
        try {
            if (this.isMobile()) {
                if (this.isMobilePlatform.aOS()) {
                    window.android.outLink(link);
                } else if (this.isMobilePlatform.iOS()) {
                    window.webkit.messageHandlers.outLink.postMessage(link);
                }
            } else {
                window.location.href = window.location.protocol +'//'+ window.location.host + link;
            }
        } catch(error) {
            console.error(error);
        }
    }

}
