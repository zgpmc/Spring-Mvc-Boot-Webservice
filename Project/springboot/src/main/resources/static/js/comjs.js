var comjs = {};
$.extend(comjs, {
    //取得参数
    getparam: function (param) {
        var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) {
            return decodeURI(r[2]);
        }
        return null;
    },
    //得到站点下page路径
    getpage: function (page) {
        page = !!page ? page : "page";
        var url = comjs.gethome() + page + "/";
        return url;
    },
    //获取网站虚拟路径
    gethome: function () {
        var strFullPath = window.document.location.href;
        var strPath = window.document.location.pathname;
        var pos = strFullPath.indexOf(strPath);
        var prePath = strFullPath.substring(0, pos);
        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 2);
        var url = prePath + postPath;
        return url;
    },
    //生成guid
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
    toupper: function (obj) {
        if (obj) {
            return obj.toLocaleUpperCase();
        } else {
            return "";
        }
    },
    tolower: function (obj) {
        if (obj) {
            return obj.toLocaleLowerCase();
        } else {
            return "";
        }
    },
    //是否字母
    isletter: function (str) {
        return /^[A-Za-z]+$/.test(str);
    },
    //是否中文
    ischinese: function (str) {
        return /^[\u0391-\uFFE5]+$/.test(str);
    },
    //是否邮编
    iszipcode: function (str) {
        return /^[1-9]\d{5}$/.test(str);
    },
    //是否Email
    isemail: function (str) {
        return /^[A-Z_a-z0-9-\.]+@([A-Z_a-z0-9-]+\.)+[a-z0-9A-Z]{2,4}$/.test(str);
    },
    //是否电话
    ismobile: function (str) {
        return /^((\(\d{2,3}\))|(\d{3}\-))?((1[35]\d{9})|(18[89]\d{8}))$/.test(str);
    },
    //判断是否有效的URL
    isurl: function (str) {
        return /^(http:|ftp:)\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"])*$/.test(str);
    },
    //判断是否IP地址
    isip: function (str) {
        return /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/.test(str);
    },
    //URL转码
    encode: function (str) {
        try {
            return encodeURIComponent(str)
        } catch (e) {
            return str
        }
    },
    //URL解码
    decode: function (str) {
        try {
            return decodeURIComponent(str)
        } catch (e) {
            return str
        }
    },
    jsontoobj: function (obj) {
        return JSON.parse(obj);
    },
    jsontostr: function (obj) {
        return JSON.stringify(obj);
    },
    //通用ajax结果反馈
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