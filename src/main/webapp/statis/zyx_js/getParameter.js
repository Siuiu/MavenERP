//根据传递过来的参数name获取对应的值
function getParameter(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return null;
}

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}

function getParams(key) {
	var keyList = [];
	var valueList = [];
	var hrefStr = decodeURIComponent(window.location.href);
	var strArray = hrefStr.split("?");
	var paramStr = strArray[1];
	if ((paramStr != null) && (paramStr != null)) {
		var paramArray = paramStr.split("&");
		for (var i = 0; i < paramArray.length; i++) {
			var param = paramArray[i];
			valueList.push(param.substr(param.indexOf("=") + 1));
			keyList.push(param.substr(0, param.indexOf("=")));
		}
		for (var j = 0; j < keyList.length; j++) {
			if (key == keyList[j]) {
				return valueList[j];
			}
		}
	}
}
