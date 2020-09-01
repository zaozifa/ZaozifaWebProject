function loadSuccessThen() {
    var url = window.location.href;
    url = decodeURI(url);
    var pIdx = url.indexOf("hanyuyufa");
    if (pIdx > 0) {
        var lastIdx = url.indexOf(".html");
        var k = url.substring(pIdx + 10, lastIdx);
        var content = document.getElementById("content");
        hLight(content.getElementsByTagName("p")[0], k);
    }else {
        var gswy = document.getElementById("gswy");
        if (gswy) {
            var h1 = document.getElementsByTagName("h1")[0];
            var zis = h1.innerHTML.split('');
            hLightKs(gswy.getElementsByTagName("p")[0], zis);
        }
    }
}

function hLight(content, k) {
    var contents = content.innerHTML;
    var values = contents.split(k);
    content.innerHTML = values.join('<span style="background:rgb(208, 102, 81);">' + k + '</span>');
}

function hLightKs(content, ks) {
    var contents = content.innerHTML;
    for (var k in ks) {
        var values = contents.split(ks[k]);
        if (values.length > 1) {
            content.innerHTML = values.join('<span style="background:rgb(208, 102, 81);">' + ks[k] + '</span>');
            break;
        }
    }

}