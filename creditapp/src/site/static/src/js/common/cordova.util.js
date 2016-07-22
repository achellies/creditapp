var CordovaExt = {
    /**
     * 打印信息至模拟器控制台。仅debug模式适用
     * @param msg 需要打印的消息
     */
    log: function(msg) {
        if (__DEBUG__) {
            console.log("DEBUG MSG: " + msg);
            try {
                cordova.exec(null, null, "WDPLog", "log", [{ "info": msg }]);
            } catch (ex) { /* nothing to do */ }
        }
    },

    /**
     * 打印错误信息至控制台,并弹框。仅debug模式适用。
     * @param msg 需要打印的消息
     */
    errlog: function(msg) {
        if (__DEBUG__) {
            console.log("DEBUG ERROR: " + msg);
            alert(msg);
            try {
                cordova.exec(null, null, "WDPLog", "log", [{ "info": msg }]);
            } catch (ex) { /* nothing to do */ }
        }
    },

    /**
     * 调用摄像头
     * @param on_success 成功时回调
     * @param on_error   失败时回调
     */
    camera: function(on_success, on_error) {
        var options = {
            quality: 75,
            destinationType: Camera.DestinationType.DATA_URL,
            sourceType: Camera.PictureSourceType.CAMERA,
            allowEdit: false,
            encodingType: Camera.EncodingType.JPEG,
            targetWidth: 300,
            targetHeight: 300,
            popoverOptions: CameraPopoverOptions,
            saveToPhotoAlbum: false
        };

        navigator.camera.getPicture(on_success, on_error, options);
    },

    /**
     * 根据配置初始化标题栏。默认:左-后退; 右-无。
     * @param options {navbar:{}}
     */
    init: function(options, onSuccessCallback) {
        //CordovaExt.log('nav bar init');
        var navbarOpt = {
            leftTitle: "<",
            leftAction: "back",
            leftUrl: "",
            rightTitle: "",
            rightAction: "null",
            rightUrl: "",
            navigatorTitle: ""
        };
        navbarOpt = $.extend(navbarOpt, options.navbar);

        try {
            cordova.exec(function(data) {
                if (onSuccessCallback) {
                    onSuccessCallback(data);
                }
            }, function(data) {}, "WDPNavigationBar", "updateBar", [navbarOpt]);
        } catch (ex) {
            // cordova_errlog(JSON.stringify(ex));
        }
    },

    /**
     * 跳转至地址
     * @param url 地址
     */
    navto: function(url) {
        url = encodeURIComponent(url);
        url = "wdsd://web?url=" + url;

        try {
            cordova.exec(function(data) {
                // cordova_log(JSON.stringify(data));
            }, function(data) {
                // cordova_errlog(JSON.stringify(data));
            }, "WDPUrlScheme", "openUrl", [{ "url": url }]);
        } catch (ex) { /* do nothing */ }
    },

    /**
     * 返回app首页
     */
    apphome: function() {
        try {
            cordova.exec(function(data) {
                // cordova_log(JSON.stringify(data));
            }, function(data) {
                // cordova_errlog(JSON.stringify(data));
            }, "WDPUrlScheme", "openUrl", [{ "url": "wdsd://home?page=1" }]);
        } catch (ex) {
            // cordova_errlog(JSON.stringify(ex));
        }
    },

    /**
     * 关闭web view
     */
    closeWV: function(onsuccess, onerror) {
        try {
            cordova.exec(function(data) {
                // cordova_log(JSON.stringify(data));
                onsuccess();
            }, function(data) {
                // cordova_errlog(JSON.stringify(data));
                onerror();
            }, "WDPPageControl", "pageControl", [{ action: "close" }]);
        } catch (ex) {
            cordova_errlog(JSON.stringify(ex));
        }
    },

    /**
     * 选择万达门店
     * @param cityCode
     * @param onsuccess
     * @param onerror
     */
    selWdSite: function(cityCode, onsuccess, onerror) {
        //            var url = encodeURIComponent("selectCity?currentcity=33333");
        var url = "selectCity?currentcity=" + cityCode;
        url = "wdsd://" + url;

        cordova.exec(function(data) {
            // alert(JSON.stringify(data));
            onsuccess(data);
        }, function(data) {
            // alert(JSON.stringify(data));
            onerror(data);
        }, "WDPUrlScheme", "openUrl", [{ url: url }]);
    },

    /**
     * 在三栏全国城市列表里选择
     * @param cityCode
     * @param onsuccess
     * @param onerror
     */
    selCity: function(cityCode, onsuccess, onerror) {
        cordova.exec(function(data) {
            onsuccess(JSON.parse(data));
        }, function(data) {
            onsuccess(JSON.parse(data));
        }, "WDPSelectCity", "getCity", [{ currentCity: cityCode }]);
    }
};


var CordovaDebug = {
    /*
     * 模拟摄像头成功
     */
    dummy_img: function(on_success, on_error) {
        on_success("/9j/4AAQSkZJRgABAQEASABIAAD/4QAWRXhpZgAATU0AKgAAAAgAAAAAAAD/2wBDAAwICAgJCAwJCQwRCwoLERUPDAwPFRgTExUTExgRDAwMDAwMEQwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/2wBDAQ0LCw0ODRAODhAUDg4OFBQODg4OFBEMDAwMDBERDAwMDAwMEQwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAAxADADASIAAhEBAxEB/8QAGwAAAwEBAAMAAAAAAAAAAAAAAAUGAQQCAwf/xAAxEAACAQIEBAQFAwUAAAAAAAABAgMEEQAFEiEGEyIxBzJBQhRRUmFxFSOBFjQ1ktH/xAAYAQEBAQEBAAAAAAAAAAAAAAAAAQMCBP/EACkRAAIBAwMCBAcAAAAAAAAAAAECAwARIRIxUQRhEyJBcRQzgZKxwtH/2gAMAwEAAhEDEQA/APqf4xM8Uca0uSk0tOgqK4i+j2JfymYjck9xGpDEbkqCpLLiLOEyfLZKxjd1Fo4/V2PQqg+2zsrMbNZVawPpN8JcIRVajPM6HxE9UedFE26gN1LLJ9bt5kU3VFIJBbyq9fTRRBTPPcxqdKoN5ZN9PZQMsfp2pPTLx3xG5qYppYolPSdZgj+YVFTSZQPmQ4H1XwyGVeI2X/vQ1IqdO5iDhr+pGiVUB/hgx7A9hi5qaqno4HnqXEUMYuztsAOwH5JIAUXJJAG5tjlp86pJyLJPGjeWSWGSNDft1yKoUH0LaQbi3cXWrVuudh5YIhEMadAYW4LYJPcWqVyXxDk+L+Cz6EU0hbTzgCoQnYJPE92QA931WF+pQLkXIIIBBuDvf/mIvxFyGCeh/V4VC1FPZZSPfGSFGq3ujYghvoLKbgLZzwa8r5BTCWXn6V/bkIsSh3QMCSbx9UV778v53xM1x1KQPCnURDQSSkkeSFcC91J2BGbfw0s8QojPDldL7Z6xUZfQ3BUX/wBtsVsaJHGsaDSqAKoHoBsAPsBtiV8QC8NLl1avakrY5Df+WBP2uu+KpHV0DqbqwBB+x3FsWspSfh4OLyfdqH6270oqOXWcSRUk/VHR0/xUcZ7GR3MSylexMCxtoJvZprgAgHDdrWN7WHe/a3rf7YQcQQ5hS18GdUcfMNKnLljHvhY6pY37lSjKkkUguqnUJNKXfHiOMsgljEss0sZZbGlaJiW1dulUbWbbApIUIO98KhiZ1QoCwAsQuSrXN7gZF97n2viujix1/pivZtNjEdNjsbkCMgm1yRpNh67A9jjeDoZYeHKFJjeTl3/AJZ0U+t1RgD972tjglp63iiWKOemeiySF9ZSYFJZytwi8tSDFALhuqzGy2Gw006KqKFUWA7DCrIdEIhvdi/iNax040qpIuNW5NtsA5vXDnmVx5tlVRQPtzV6G+Tr1Rt/DqLj1Fx2JxP8Ah7WyLS1WT1ZZauhkN43JuEO1lDEnSrhu3SAykeYYr8T+fcOzzVkec5O6wZrB9XkmW1uXNa29tlb5ABuyMirDIpjeBzZXIZGOySDAvwrjysfTB9KoMetaWnV+Ysaq++4Ftz3O3rhXlHEMVYwo62NqLM1H7tLKCLkbFoHPRNGe4KEm3faxLkYVgysh0nH4I5BGCOCMUWwY3GYVzWY09sGDClIOKf7aP84YZH/j48GDDit3+SvvTDGYMGFYV//Z");
    },

    /*
     * 模拟图片获取成功
     */
    dummy_exec: function(on_success, on_error, arg0, arg1, arg2) {
        on_success({
            "status": "success",
            "image": "/9j/4AAQSkZJRgABAQEASABIAAD/4QAWRXhpZgAATU0AKgAAAAgAAAAAAAD/2wBDAAwICAgJCAwJCQwRCwoLERUPDAwPFRgTExUTExgRDAwMDAwMEQwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/2wBDAQ0LCw0ODRAODhAUDg4OFBQODg4OFBEMDAwMDBERDAwMDAwMEQwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAAxADADASIAAhEBAxEB/8QAGwAAAwEBAAMAAAAAAAAAAAAAAAUGAQQCAwf/xAAxEAACAQIEBAQFAwUAAAAAAAABAgMEEQAFEiEGEyIxBzJBQhRRUmFxFSOBFjQ1ktH/xAAYAQEBAQEBAAAAAAAAAAAAAAAAAQMCBP/EACkRAAIBAwMCBAcAAAAAAAAAAAECAwARIRIxUQRhEyJBcRQzgZKxwtH/2gAMAwEAAhEDEQA/APqf4xM8Uca0uSk0tOgqK4i+j2JfymYjck9xGpDEbkqCpLLiLOEyfLZKxjd1Fo4/V2PQqg+2zsrMbNZVawPpN8JcIRVajPM6HxE9UedFE26gN1LLJ9bt5kU3VFIJBbyq9fTRRBTPPcxqdKoN5ZN9PZQMsfp2pPTLx3xG5qYppYolPSdZgj+YVFTSZQPmQ4H1XwyGVeI2X/vQ1IqdO5iDhr+pGiVUB/hgx7A9hi5qaqno4HnqXEUMYuztsAOwH5JIAUXJJAG5tjlp86pJyLJPGjeWSWGSNDft1yKoUH0LaQbi3cXWrVuudh5YIhEMadAYW4LYJPcWqVyXxDk+L+Cz6EU0hbTzgCoQnYJPE92QA931WF+pQLkXIIIBBuDvf/mIvxFyGCeh/V4VC1FPZZSPfGSFGq3ujYghvoLKbgLZzwa8r5BTCWXn6V/bkIsSh3QMCSbx9UV778v53xM1x1KQPCnURDQSSkkeSFcC91J2BGbfw0s8QojPDldL7Z6xUZfQ3BUX/wBtsVsaJHGsaDSqAKoHoBsAPsBtiV8QC8NLl1avakrY5Df+WBP2uu+KpHV0DqbqwBB+x3FsWspSfh4OLyfdqH6270oqOXWcSRUk/VHR0/xUcZ7GR3MSylexMCxtoJvZprgAgHDdrWN7WHe/a3rf7YQcQQ5hS18GdUcfMNKnLljHvhY6pY37lSjKkkUguqnUJNKXfHiOMsgljEss0sZZbGlaJiW1dulUbWbbApIUIO98KhiZ1QoCwAsQuSrXN7gZF97n2viujix1/pivZtNjEdNjsbkCMgm1yRpNh67A9jjeDoZYeHKFJjeTl3/AJZ0U+t1RgD972tjglp63iiWKOemeiySF9ZSYFJZytwi8tSDFALhuqzGy2Gw006KqKFUWA7DCrIdEIhvdi/iNax040qpIuNW5NtsA5vXDnmVx5tlVRQPtzV6G+Tr1Rt/DqLj1Fx2JxP8Ah7WyLS1WT1ZZauhkN43JuEO1lDEnSrhu3SAykeYYr8T+fcOzzVkec5O6wZrB9XkmW1uXNa29tlb5ABuyMirDIpjeBzZXIZGOySDAvwrjysfTB9KoMetaWnV+Ysaq++4Ftz3O3rhXlHEMVYwo62NqLM1H7tLKCLkbFoHPRNGe4KEm3faxLkYVgysh0nH4I5BGCOCMUWwY3GYVzWY09sGDClIOKf7aP84YZH/j48GDDit3+SvvTDGYMGFYV//Z"
        });
    },

    /*
     * 模拟图片返回失败
     */
    dummy_exec_err: function(on_success, on_error, arg0, arg1, arg2) {
        on_error({
            "status": "error",
            "message": "dummy error"
        });
    }
};
