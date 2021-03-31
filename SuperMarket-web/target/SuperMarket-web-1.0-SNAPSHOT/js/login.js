// 验证码
$(function () {
    // 定义一个用来显示验证码的集合
    var show_num = [];
    draw(show_num);

    $("#canvas").on('click', function () {
        draw(show_num);
    });

    $('#submit').click(function () {
        var number = "";
        // 获取输入的验证码
        var codes = $('.code').val();
        // 遍历验证码
        $.each(show_num, function (i, num) {
            number = number.concat(num);
        });
        if (codes === number){
            // alert(number);
            $('#login').attr("action", "/sys/login/login.json");
        }else {
            $('#codeMessage').html("验证码错误！");
        }
    });
});

// 渲染验证码
function draw(show_num) {
    var canvas_width = $('#canvas').width(); // 获取canvas的宽
    var canvas_height = $('#canvas').height(); // 获取canvas的高
    var canvas = document.getElementById("canvas"); // 获取到canvas的对象
    var context = canvas.getContext("2d"); // 获取到canvas画图的环境
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    // 设置验证码中的字符
    var sCode = "A,B,C,D,E,F,G,H,L,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9";
    var aCode = sCode.split(",");
    var aLength = aCode.length; // 获取数组的长度

    // 4位随机数
    for (var i = 0; i < 4; i++) {
        var j = Math.floor(Math.random() * aLength); // 获取随机索引值
        var deg = Math.random() * 30 * Math.PI / 180; // 产生0-30度之间的随机弧度
        var txt = aCode[j]; // 得到随机内容
        show_num[i] = txt.toLowerCase(); // 忽略大小写，全部变成小写
        var x = 10 + i * 20; //文字在canvas上的横坐标坐标
        var y = 20 + Math.random() * 8;  // 纵坐标
        context.font = "bold 23px 微软雅黑"; // 文字样式

        context.translate(x, y); // 在面板上的坐标
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    // 随机线条
    for (var i = 0; i <= 5; i++) {
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    // 验证码上的点
    for (var i = 0; i < 30; i++) {
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}
// 随机颜色
function randomColor() {
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}

