//隔行变色
function tbInterlaced(tbId) {
    if (tbId) {} else {
        tbId = 'tbList';
    }
    var obj = document.getElementById(tbId); //添加数据的表格
    if (obj) {
        for (var i = 1; i < obj.rows.length; i++) {
            if (i % 2 == 1) {
                obj.rows[i].className = "";
            } else {
                obj.rows[i].className = "tr_color";
            }
            
        }
    }
}