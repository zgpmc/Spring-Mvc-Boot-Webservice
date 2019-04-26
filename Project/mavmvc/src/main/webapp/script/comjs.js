/**
 * 获取url里面的参数(name)
 *
 **/
var comjs = {};
$.extend(comjs, {
    getparam: function (param) {
        var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) {
            return decodeURI(r[2]);
        }
        return null;
    },
    getpage: function (page) {
        page = !!page ? page : "page";
        var url = comjs.gethome() + page + "/";
        return url;
    },
    //获取网站路径以及虚拟目录
    gethome: function () {
        var strFullPath = window.document.location.href;
        var strPath = window.document.location.pathname;
        var pos = strFullPath.indexOf(strPath);
        var prePath = strFullPath.substring(0, pos);
        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 2);
        return (prePath + postPath);
    },
    getguid: function () {
        var guid = 'xxxxxxxa-xxxn-xxxt-xxxu-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        }).toUpperCase();
        return guid;
    },
    //Cookie操作
    getcookie: function (name) {
        var r = new RegExp('(^|;|\\s+)' + name + '=([^;]*)(;|$)');
        var m = (document.cookie ? document.cookie : top.document.cookie).match(r);
        return (!m ? '' : decodeURI(m[2]));
    },
    setcookie: function (name, value, expire, domain, path) {
        if (!expire) {
            expire = 24;
        }
        var s = name + '=' + encodeURI(value);
        if (!!path) s = s + '; path=' + path; else s = s + '; path=/';
        if (expire > 0) {
            var d = new Date();
            d.setTime(d.getTime() + expire * 3 * 3600);
            if (!!domain) s = s + '; domain=' + domain;
            s = s + '; expires=' + d.toGMTString();
        }
        document.cookie = s;
    },
    removecookie: function (name, domain, path) {
        var s = name + '=';
        if (!!domain) s = s + '; domain=' + domain;
        if (!!path) s = s + '; path=' + path;
        s = s + '; expires=Fri, 02-Jan-1970 00:00:00 GMT';
        document.cookie = s;
    },
    msg: function (resdata) {
        if (resdata == null) {
            alert("未获取到数据!");
        } else {
            if (resdata.code == "href") {
                if (resdata.popout) {
                    alert("页面即将跳转！");
                }
                windows.location = comjs.gethome() + resdata.msg;
            }
            resdata.popout ? alert(resdata.msg) : "";
        }
    }
});